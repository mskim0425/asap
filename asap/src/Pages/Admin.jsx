import axios from "axios";
import React, {
  useLayoutEffect,
  useEffect,
  useState,
  useCallback,
} from "react";
import { v4 as uuidv4 } from "uuid";

import "../style/Admin.css";
import warehouseData from "../warehouseData/warehouseData";
import Masonry, { ResponsiveMasonry } from "react-responsive-masonry";

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

  const [releaseQuantity, setReleaseQuantity] = useState("");
  const [warehouses, setWarehouses] = useState("");
  const [wid, setWid] = useState("");

  //페이징
  // const [pageParam, setPageParam] = useState(10);
  const [pageNumber, setPageNumber] = useState(10);
  const [ref, inView] = useInView();
  const [target, setTarget] = useState(null);

  let newData = {
    pName: name,
    price: Number(price),
    pCode: code,
    wId: warehouseId,
    pInsert: Number(stock),
  };

  //리스트 가져오기
  // const stuffList = async () => {
  //   try {
  //     setloading(true);
  //     const response = await axios.get(`/find-all?lastId=${pageParam}`);
  //     let sortList = response.data.sort((a, b) => a.pid - b.pid);
  //     setLists((prev) => [...prev, ...sortList]);
  //     setPageParam(pageParam + 10);
  //     console.log("page", pageParam);
  //   } catch (error) {
  //     setError(error);
  //   }
  //   setloading(false);
  // };

  // useEffect(() => {
  //   stuffList();
  // }, []);

  // 무한스크롤
  const stuffList = useCallback(async () => {
    setloading(true);
    try {
      const response = await axios.get(`/find-all?lastId=${pageNumber}`);
      let sortList = response.data.sort((a, b) => a.pid - b.pid);
      // setLists(sortList);
      // if (pageNumber !== 10) {
      setLists((prevChallenges) => [...prevChallenges, ...sortList]);
      // } else {
      // setLists(sortList);
      // }
      console.log("s리스트dssds", lists);
      setloading(false);
    } catch (error) {
      setError(error);
    }
  }, [pageNumber]);

  useLayoutEffect(() => {
    stuffList();
  }, [stuffList]);

  useLayoutEffect(() => {
    // 사용자가 마지막 요소를 보고 있고, 로딩 중이 아니라면
    if (inView && !loading) {
      setPageNumber((prevState) => prevState + 10);
    }
  }, [inView, loading]);

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
        // console.log("re", response.data);
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
  // console.log(newData);
  const addStuff = async () => {
    try {
      await axios.post("/prod", newData);
      window.location.replace("/admin");
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
      window.location.replace("/admin");
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
  //출고량 수정
  const releaseCount = async () => {
    try {
      await axios.post("/prod", count);
      window.location.replace("/admin");
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

    let uuid = uuidv4();
    // console.log(uuid);
    setCode(uuid);
  };

  //무한스크롤
  // useEffect(() => {
  //   let observer;
  //   if (target) {
  //스크롤끝부분
  //     const onIntersect = async ([entry], observer, event) => {
  //       if (entry.isIntersecting) {
  //         observer.unobserve(entry.target);
  //         await stuffList();
  //         observer.observe(entry.target);
  //       }
  //     };
  //     observer = new IntersectionObserver(onIntersect, { threshold: 1 });
  //     observer.observe(target);
  //   }
  //   return () => observer && observer.disconnect();
  // }, [target]);

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
              // onChange={(e, index) => {
              //   setWarehouseId(index + 1);
              // }}
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
            {/* <input
            type="text"
            onChange={(e) => {
              setWarehouseId(e.target.value);
            }}
          ></input> */}
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
            <div className="table">
              <div className="header">
                <div className="cell">상품 코드</div>
                <div className="cell">상품명</div>
                <div className="cell">단가</div>
                <div className="cell">바코드</div>
              </div>

              {/* {lists.map((list, idx) => (
                <React.Fragment key={idx}>
                  {lists.length - 1 == idx ? (
                    <div className="list-item" ref={ref}>
                      {list.content}
                    </div>
                  ) : (
                    <div className="list-item">{list.content}</div>
                  )}
                </React.Fragment>
              ))} */}

              {lists.map((list, index) => (
                <React.Fragment key={index}>
                  {lists.length - 1 === index ? (
                    <>
                      <div
                        // ref={ref}
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

                          <div className="store_table">
                            <div className="store_row">
                              <div className="store_cell">입고일</div>
                              <div className="store_cell">입고량</div>
                              <div className="store_cell">창고 이름</div>
                              <div className="store_cell">창고 위치</div>
                            </div>
                            {stores &&
                              stores.map((store, index) => {
                                return (
                                  <div key={index}>
                                    <div className="store_row">
                                      <div className="store_cell">
                                        {store.receiveIn}
                                      </div>
                                      <div className="store_cell">
                                        {store.pinsert}
                                      </div>
                                      <div className="store_cell">
                                        {store.wname}
                                      </div>
                                      <div className="store_cell">
                                        {store.wloc}
                                      </div>
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
                      <div ref={ref}></div>
                    </>
                  ) : (
                    <>
                      <div
                        ref={setTarget}
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
                    </>
                  )}
                </React.Fragment>
              ))}

              {/* {lists &&
                Array.from(lists).map((list, index) => {
                  return (
                    <>
                      <div
                        ref={setTarget}
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
                })} */}
            </div>
          </div>
        </div>
      </div>
      <div id="sse">
        <SSE />
      </div>
    </div>
  );
};

export default AdminContent;
