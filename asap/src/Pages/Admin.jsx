import axios from "axios";
import React, { useEffect, useState, useCallback } from "react";
import { v4 as uuidv4 } from "uuid";

import "../style/Admin.css";
import warehouseData from "../warehouseData/warehouseData";

import Error from "../Component/Error/Error";
import Loading from "../Component/loading/Loading";
import SSE from "../Component/SSE";
import { useInView } from "react-intersection-observer";

const AdminContent = () => {
  const [loading, setloading] = useState(false);
  const [error, setError] = useState(null);
  const [lists, setLists] = useState([]);
  const [stores, setStores] = useState([]);
  const [release, setRelease] = useState([]);
  const [product, setProduct] = useState([]);
  const [shownComments, setShownComments] = useState({});
  //물품 추가
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [code, setCode] = useState("");
  const [warehouseId, setWarehouseId] = useState("");
  const [stock, setStock] = useState("");
  //검색
  const [search, setSearch] = useState("");
  const [searchControl, setSearchControl] = useState("");
  const [wordLists, setWordLists] = useState([]);
  const [searchWordLists, setSearchWordLists] = useState([]);
  const [isHaveSearchValue, setIsHaveSearchValue] = useState(false);

  const [releaseQuantity, setReleaseQuantity] = useState("");
  const [warehouses, setWarehouses] = useState("");
  const [wid, setWid] = useState("");
  //페이징
  const [pageNumber, setPageNumber] = useState(30);
  const [ref, inView] = useInView({
    threshold: 1,
    triggerOnce: true,
    initialInView: true,
  });

  const [listId, setListId] = useState("");
  // 무한스크롤
  const stuffList = async (data, search) => {
    try {
      const response = await axios.get(`/find-all?lastId=${pageNumber}`);
      let sortList = response.data.sort((a, b) => a.pid - b.pid);
      if (search === "search") {
        setLists(data);
      } else if (search !== "search") {
        setLists((prevChallenges) => [...prevChallenges, ...sortList]);
      }
      // else if (pageNumber !== 10 && search !== "search") {
      //   setLists((prevChallenges) => [...prevChallenges, ...sortList]);
      // }
      // else {
      //   setLists(sortList);
      // }
    } catch (error) {
      setError(error);
    }
  };
  useEffect(() => {
    stuffList();
  }, [pageNumber]);

  useEffect(() => {
    // 사용자가 마지막 요소를 보고 있고, 로딩 중이 아니라면
    if (inView && !loading) {
      setPageNumber((prevState) => prevState + 30);
    }
  }, [inView, loading]);

  //자동완성
  useEffect(() => {
    const wordLists = async () => {
      const responseNames = await axios.get("/product-names");
      // console.log(responseNames.data);
      setWordLists(responseNames.data);
    };
    wordLists();
  }, []);

  const autoComplete = (word) => {
    setSearch(word);
    if (word === "") {
      setIsHaveSearchValue(false);
    } else {
      // const filteredWord = wordLists.filter((list) => list === word);
      const filteredWord = wordLists.filter((list) => list.includes(word));
      setSearchWordLists(filteredWord);
      setIsHaveSearchValue(true);
    }
  };

  //드롭다운 검색어 클릭시
  const clickDropDownWord = (clickedItem) => {
    // console.log("fdsf", clickedItem);
    setSearch(clickedItem);
    // console.log("22222222", search);
    setIsHaveSearchValue(false);
    // onSearch();
  };

  // Enter 입력이 되면 이벤트 실행
  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      onSearch();
    }
  };

  //검색
  const onSearch = async () => {
    console.log("search", search);

    if (search === null || search === "") {
      alert("검색어 없음");
    } else {
      // const responseNames = await axios.get("/product-names");
      // console.log(responseNames.data);
      // const filteredName = responseNames.data.filter((list) => list === search);
      const response = await axios.get(`/search?order=asc&pName=${search}`);
      stuffList(response.data, "search");
      setSearchControl("search");
    }
  };

  //리스트 클릭시 상세 내용보여주기
  const toggleComment = (id, modifiedQuantity) => {
    setListId(id);
    if (modifiedQuantity === "modifiedQuantity") {
      setShownComments(() => ({
        [id]: true,
      }));
    } else {
      setShownComments((prevShownComments) => ({
        // ...prevShownComments,
        [id]: !prevShownComments[id],
      }));
    }
    //물품 상세내용 가져오기
    const getDetail = async () => {
      try {
        const response = await axios.get(`/find-one?pId=${id}`);
        setStores(response.data.insertLogs);
        setProduct(response.data.product);
        setRelease(response.data.releaseLogs);
        setWarehouses(response.data.product.warehouses);
      } catch (e) {
        setError(e);
      }
    };

    getDetail();
  };

  //새로운 물품 추가
  const addStuff = async () => {
    try {
      let newData = {
        pName: name,
        price: Number(price),
        pCode: code,
        wId: warehouseId,
        pInsert: Number(stock),
      };
      await axios.post("/prod", newData);
      setloading(true);
      setName("");
      setPrice("");
      let uuid = uuidv4();
      setCode(uuid);
      let id = (document.querySelector("#selectWareHouse").value = "none");
      setWarehouseId(id);
      setStock("");
    } catch (error) {
      setError(error);
    }
    setloading(false);
  };

  const pName = product.pname;
  const productPrice = product.price;
  const pcode = product.pcode;
  const wId = Number(wid);

  //입고량 수정
  const stockQuantity = async () => {
    try {
      let quantityData = {
        pName: pName,
        price: productPrice,
        pCode: pcode,
        wId: wId,
        pInsert: parseInt(stock),
      };

      if (wId === 0) {
        alert("창고를 선택해주세요.");
      } else {
        await axios.post("/prod", quantityData);
        document.querySelector("#stockQuantity").value = "";
        toggleComment(listId, "modifiedQuantity");
      }
    } catch (error) {
      console.error(error);
    }
  };

  //출고량 수정
  const releaseCount = async () => {
    try {
      var count = {
        pName: pName,
        price: productPrice,
        pCode: pcode,
        wId: wId,
        quantity: parseInt(releaseQuantity),
      };
      if (wId === 0) {
        alert("창고를 선택해주세요.");
      } else if (parseInt(releaseQuantity) > product.cnt) {
        alert("출고량이 재고보다 많습니다.");
      } else {
        await axios.post("/prod", count);
        document.querySelector("#releaseQuantity").value = "";
        toggleComment(listId, "modifiedQuantity");
      }
    } catch (error) {
      console.error(error);
    }
  };

  const showAddProduct = () => {
    const content = document.querySelector(".add_stuff");
    content.classList.toggle("active");

    let uuid = uuidv4();
    setCode(uuid);
  };

  const wareHouseId = (e) => {
    let id = Number(e.target.value) + 1;
    setWarehouseId(id);
  };

  if (loading)
    return (
      <div className="container">
        <Loading></Loading>
      </div>
    );
  if (error)
    return (
      <div className="container">
        <Error></Error>
      </div>
    );
  if (!lists) return null;

  return (
    <div id="main">
      <div style={{ width: "1200px" }}>
        <div id="search">
          <div className="searchWrapper">
            <input
              type="text"
              placeholder="상품을 검색을 해주세요."
              id="search"
              value={search}
              onKeyPress={handleOnKeyPress}
              onChange={(e) => {
                autoComplete(e.target.value);
              }}
            />
            <div onClick={onSearch}>검색</div>
            <button className="addButton" onClick={showAddProduct}>
              +
            </button>
          </div>
          {isHaveSearchValue && (
            <div id="autoComplete">
              {searchWordLists.length === 0 && (
                <div class="no_searched_word">해당하는 단어가 없습니다</div>
              )}
              {searchWordLists.map((dropDownItem, dropDownIndex) => {
                return (
                  <div
                    key={dropDownIndex}
                    class="searchedword"
                    onClick={() => clickDropDownWord(dropDownItem)}
                  >
                    {dropDownItem}
                  </div>
                );
              })}
            </div>
          )}
        </div>

        <div className="add_stuff">
          <div className="info_wrapper">
            <div className="stuff_title">
              <span>상품명</span>
            </div>
            <input
              type="text"
              onChange={(e) => {
                setName(e.target.value);
              }}
              value={name}
            ></input>
          </div>
          <div className="info_wrapper">
            <div className="stuff_title">
              <span>단가</span>
            </div>
            <input
              type="text"
              onChange={(e) => {
                setPrice(e.target.value);
              }}
              value={price}
            ></input>
          </div>
          <div className="info_wrapper">
            <div className="stuff_title">
              <span>바코드</span>
            </div>
            <input
              type="text"
              value={code}
              onChange={(e) => {
                setCode(e.target.value);
              }}
              readOnly
            ></input>
          </div>
          <div className="info_wrapper">
            <div className="stuff_title">
              <span>창고 ID</span>
            </div>
            <select
              id="selectWareHouse"
              onChange={(e) => {
                wareHouseId(e);
              }}
            >
              <option value="none">선택</option>
              {warehouseData.map((data, index) => {
                return (
                  <option key={index} value={index}>
                    {Object.values(data)}
                  </option>
                );
              })}
            </select>
          </div>

          <div className="info_wrapper">
            <div className="stuff_title">
              <span>입고량</span>
            </div>
            <input
              type="text"
              onChange={(e) => {
                setStock(e.target.value);
              }}
              value={stock}
            ></input>
          </div>
          <div className="button_wrapper">
            <button onClick={addStuff}>추가</button>
          </div>
        </div>
        <div id="list">
          <div className="content active">
            {/* 상품리스트 */}
            <div className="table">
              <div className="header">
                <div className="cell">상품 코드</div>
                <div className="cell">상품명</div>
                <div className="cell">단가</div>
                <div className="cell">바코드</div>
              </div>

              {lists &&
                Array.from(lists).map((list, index) => {
                  return (
                    <>
                      <div
                        key={index}
                        className="row"
                        onClick={() => toggleComment(list.pid)}
                      >
                        <div className="cell">{list.pid}</div>
                        <div className="cell">{list.pname}</div>
                        <div className="cell">${list.price}</div>
                        <div className="cell">{list.pcode}</div>
                      </div>
                      {shownComments[list.pid] ? (
                        <div className="detail">
                          <div className="detail_wrapper">
                            <div className="detail_row">
                              <div className="detail_cell color">상품명</div>
                              <div className="detail_cell color">가격</div>
                              <div className="detail_cell color">바코드</div>
                              <div className="detail_cell color">재고</div>
                            </div>
                            <div className="detail_row">
                              <div className="detail_cell height">
                                {product.pname}
                              </div>
                              <div className="detail_cell height">
                                ${product.price}
                              </div>
                              <div className="detail_cell height">
                                <img src={product.pqr} alt="" />
                              </div>
                              <div className="detail_cell height">
                                {product.cnt}
                              </div>
                            </div>
                          </div>

                          <div className="product_quantity">
                            <div>
                              <h3>창고 선택</h3>
                              <select
                                onChange={(e) => {
                                  setWid(e.target.value);
                                }}
                              >
                                <option value="none">선택</option>
                                {Array.from(warehouses).map((el, index) => {
                                  return (
                                    <>
                                      <option key={index} value={el.wid}>
                                        {el.wname}
                                      </option>
                                    </>
                                  );
                                })}
                              </select>
                            </div>
                            <div className="stock">
                              <input
                                id="stockQuantity"
                                type="text"
                                onChange={(e) => setStock(e.target.value)}
                              />
                              <button onClick={stockQuantity}>입고량</button>
                            </div>
                            <div className="release">
                              <input
                                id="releaseQuantity"
                                type="text"
                                onChange={(e) =>
                                  setReleaseQuantity(e.target.value)
                                }
                              />
                              <button onClick={releaseCount}>출고량</button>
                            </div>
                          </div>
                          <div>
                            <div className="store_row header">
                              <div>입고일</div>
                              <div>입고량</div>
                              <div>창고 이름</div>
                              <div>창고 위치</div>
                            </div>
                            {stores &&
                              stores.map((store, index) => {
                                return (
                                  <div key={index}>
                                    <div className="store_row">
                                      <div>{store.receiveIn}</div>
                                      <div>{store.pinsert}</div>
                                      <div>{store.wname}</div>
                                      <div>{store.wloc}</div>
                                    </div>
                                  </div>
                                );
                              })}
                          </div>
                          <div>
                            <div className="release_row header">
                              <div>출고일</div>
                              <div>출고량</div>
                              <div>창고 이름</div>
                              <div>창고 위치</div>
                            </div>
                            {release &&
                              release.map((release, index) => {
                                return (
                                  <div key={index}>
                                    <div className="release_row">
                                      <div>{release.releaseAt}</div>
                                      <div>{release.quantity}</div>
                                      <div>{release.wname}</div>
                                      <div>{release.wloc}</div>
                                    </div>
                                  </div>
                                );
                              })}
                          </div>
                        </div>
                      ) : null}
                      {lists.length - 1 === index &&
                      searchControl !== "search" ? (
                        <div ref={ref}></div>
                      ) : null}
                    </>
                  );
                })}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminContent;
