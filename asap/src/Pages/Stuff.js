import "../style/stuff.css";
import axios from "axios";
import { React, useEffect, useState } from "react";

import Error from "../Component/Error/Error";
import Loading from "../Component/loading/Loading";

export default function Stuff() {
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

  let data = {
    pName: name,
    price: price,
    pCode: code,
    wId: warehouseId,
    pInsert: stock,
  };

  const addStuff = async () => {
    try {
      const response = await axios.post("/prod", data);
      console.log("postresponse", response);
      setloading(true);
    } catch (error) {
      setError(error);
    }
    setloading(false);
  };

  //리스트 가져오기
  useEffect(() => {
    const stuffList = async () => {
      try {
        setloading(true);
        const response = await axios.get("/find-all?lastId=10");
        console.log("s리스트dssds", response.data);
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
        // console.log("re", response.data);
        setStores(response.data.insertLogs);
        setProduct(response.data.product);
        setRelease(response.data.releaseLogs);
      } catch (e) {
        setError(e);
      }
    };

    getDetail();
  };
  // console.log("show", shownComments[9]);

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
    <div className="container">
      <div className="visualization"></div>

      <div className="data">
        <div className="search">
          <input
            type="text"
            placeholder="상품을 검색을 해주세요."
            id="search"
            onChange={(e) => {
              setSearch(e.target.value);
            }}
          />
          <div onClick={onSearch}>검색</div>
        </div>
        <div className="content active">
          <button className="addButton" onClick={showAddProduct}>
            +
          </button>
          <div className="add_stuff">
            <div className="info_wrapper">
              <div className="title">
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
              <div className="title">
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
              <div className="title">
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
              <div className="title">
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
              <div className="title">
                <span>입고량</span>
              </div>
              <input
                type="text"
                onChange={(e) => {
                  setStock(e.target.value);
                }}
              ></input>
            </div>
            <button onClick={addStuff}>추가</button>
          </div>
          <div className="table">
            <div className="header">
              <div className="cell">상품 코드</div>
              <div className="cell">상품명</div>
              <div className="cell">단가</div>
              <div className="cell">바코드</div>
            </div>

            {lists.map((list) => (
              <>
                <div
                  key={list.pid}
                  className="row"
                  onClick={() => toggleComment(list.pid)}
                >
                  <div className="cell">{list.pid}</div>
                  <div className="cell">{list.pname}</div>
                  <div className="cell">${list.price}</div>
                  <div className="cell">{list.pcode}</div>
                </div>
                {shownComments[list.pid] ? (
                  <div className="row">
                    <td className="colsapn" colSpan={4}>
                      {stores.map((store) => (
                        <>
                          <div className="detail_wrapper">
                            <div className="detail_info">{store.receiveIn}</div>
                            <div className="detail_info">{store.pinsert}</div>
                            <div className="detail_info">{store.wname}</div>
                            <div className="detail_info">{store.wloc}</div>
                          </div>
                        </>
                      ))}
                    </td>
                  </div>
                ) : null}
              </>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
