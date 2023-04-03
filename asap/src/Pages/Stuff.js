import "../style/stuff.css";
import axios from "axios";
import { useEffect, useState } from "react";

import Error from "../component/Error/Error";
import Loading from "../component/loading/Loading";

export default function Stuff() {
  const [loading, setloading] = useState(false);
  const [error, setError] = useState(null);
  const [list, setList] = useState(null);

  const stuffList = async () => {
    try {
      setloading(true);
      const response = await axios.get("http://localhost:3001/stuff");
      setList(response.data);
    } catch (e) {
      setError(e);
    }
    setloading(false);
  };

  useEffect(() => {
    stuffList();
  }, []);

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
  if (!list) return null;

  return (
    <div className="container">
      <div className="visualization"></div>
      <div className="data">
        <div className="search">
          <input
            type="text"
            placeholder="상품을 검색을 해주세요."
            id="search"
          />
          <div>검색</div>
        </div>
        <div className="list-table">
          <div className="table-heading">
            <div className="table-row">
              <span class="head">상품명</span>
              <span class="head">수량</span>
              <span class="head">가격</span>
              <span class="head">총 가격</span>
              <span class="head">창고명</span>
              <span class="head">창고 위치</span>
            </div>
          </div>
          <div className="table-content">
            {list.map((el, index) => {
              console.log(index % 2);
              return (
                <div
                  className={index % 2 === 0 ? "info" : "info active"}
                  key={el.id}
                >
                  <span class="cell">{el.pName}</span>
                  <span class="cell">{el.quantity}개</span>
                  <span class="cell">{el.price}원</span>
                  <span class="cell">총 {el.total}원</span>
                  <span class="cell">{el.wName}</span>
                  <span class="cell">{el.wLoc}</span>
                </div>
              );
            })}
          </div>
        </div>
      </div>
      {/* <div className="add-data">
        <div>
          <h4>수량코드</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>상품명</h4>
          <input type="text" id="name"></input>
        </div>
        <div>
          <h4>수량</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>가격</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>총 가격</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>창고명</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>창고 위치</h4>
          <input type="text" id="code"></input>
        </div>
        <div className="button">
          <button>추가</button>
        </div>
      </div>
      <section></section> */}
    </div>
  );
}
