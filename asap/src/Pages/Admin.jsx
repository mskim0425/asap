import axios from "axios";
import { React, useEffect, useState, useRef } from "react";

import "../style/Admin.css";

import Error from "../Component/Error/Error";
import Loading from "../Component/loading/Loading";
import Chart from "../Component/Chart/MonthlyChart";

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

  const [releaseQuantity, setReleaseQuantity] = useState("");
  const [warehouses, setWarehouses] = useState("");
  const [wid, setWid] = useState("");

  let newData = {
    pName: name,
    price: price,
    pCode: code,
    wId: warehouseId,
    pInsert: stock,
  };

  //리스트 가져오기
  useEffect(() => {
    const stuffList = async () => {
      try {
        setloading(true);
        const response = await axios.get("/find-all?lastId=20");
        // console.log("s리스트dssds", response.data);
        setLists(response.data);
      } catch (error) {
        setError(error);
      }
      setloading(false);
    };

    stuffList();
  }, []);

  //리스트 클릭시 상세 내용보여주기
  const toggleComment = (id) => {
    setShownComments((prevShownComments) => ({
      // ...prevShownComments,
      [id]: !prevShownComments[id],
    }));
    //물품 상세내용 가져오기
    const getDetail = async () => {
      try {
        const response = await axios.get(`/find-one?pId=${id}`);
        console.log("re", response.data);
        // console.log("re", response.data.product.warehouses);
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
      await axios.post("/prod", newData);
      setloading(true);
    } catch (error) {
      setError(error);
    }
    setloading(false);
  };

  const pName = product.pname;
  const productPrice = product.price;
  const pcode = product.pcode;
  const wId = Number(wid);
  let quantityData = {
    pName: pName,
    price: productPrice,
    pCode: pcode,
    wId: wId,
    pInsert: parseInt(stock),
  };

  //입고량 수정
  const stockQuantity = async () => {
    try {
      await axios.post("/prod", quantityData);
      setloading(true);
    } catch (error) {
      setError(error);
    }
    setloading(false);
  };

  var count = {
    pName: pName,
    price: productPrice,
    pCode: pcode,
    wId: wId,
    quantity: parseInt(releaseQuantity),
  };
  // console.log("count", count);
  //출고량 수정
  const releaseCount = async () => {
    try {
      await axios.post("/prod", count);
      setloading(true);
    } catch (error) {
      setError(error);
    }
    setloading(false);
  };

  //검색
  const onSearch = async () => {
    if (search === null || search === "") {
      alert("검색어 없음");
    } else {
      const response = await axios.get("/product-names");
      //상품명 목록을 가져와 검색단어 추출
      const filteredName = response.data.filter((list) => list === search);
      //리스트에서 검색한 상품정보가져오기
      const searchProductInfo = lists.filter(
        (list) => String(list.pname) === String(filteredName)
      );
      setLists(searchProductInfo);
    }
  };

  const showAddProduct = () => {
    const content = document.querySelector(".add_stuff");
    content.classList.toggle("active");
  };

  let page = 10;
  const [height, setHeight] = useState(0);
  const ref = useRef(null);

  function paging() {
    // getList(page);
    page = page + 10;
  }
  useEffect(() => {
    setHeight(ref);
  }, [height]);
  // console.log("높이", height.current);

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
      <div id="chart">
        <Chart />
      </div>
      <div id="search">
        <div className="searchWrapper">
          <input
            type="text"
            placeholder="상품을 검색을 해주세요."
            id="search"
            onChange={(e) => {
              setSearch(e.target.value);
            }}
          />
          <div onClick={onSearch}>검색</div>
          <button className="addButton" onClick={showAddProduct}>
            +
          </button>
        </div>
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
          ></input>
        </div>
        <div className="info_wrapper">
          <div className="stuff_title">
            <span>바코드</span>
          </div>
          <input
            type="text"
            onChange={(e) => {
              setCode(e.target.value);
            }}
          ></input>
        </div>
        <div className="info_wrapper">
          <div className="stuff_title">
            <span>창고 ID</span>
          </div>
          <input
            type="text"
            onChange={(e) => {
              setWarehouseId(e.target.value);
            }}
          ></input>
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
          ></input>
        </div>
        <div className="button_wrapper">
          <button onClick={addStuff}>추가</button>
        </div>
      </div>
      <div id="list">
        <div className="content active">
          {/* 상품리스트 */}
          <div className="table2">
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
                            <div className="detail_cell">{product.pname}</div>
                            <div className="detail_cell">${product.price}</div>
                            <div className="detail_cell">{product.pcode}</div>
                            <div className="detail_cell">{product.cnt}</div>
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
                                      {el.wid}
                                    </option>
                                  </>
                                );
                              })}
                            </select>
                          </div>
                          <div className="stock">
                            <input
                              type="text"
                              onChange={(e) => setStock(e.target.value)}
                            />
                            <button onClick={stockQuantity}>입고량</button>
                          </div>
                          <div className="release">
                            <input
                              type="text"
                              onChange={(e) =>
                                setReleaseQuantity(e.target.value)
                              }
                            />
                            <button onClick={releaseCount}>출고량</button>
                          </div>
                        </div>
                        <div>
                          <div className="store_row">
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
                          <div className="release_row">
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
                  </>
                );
              })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminContent;