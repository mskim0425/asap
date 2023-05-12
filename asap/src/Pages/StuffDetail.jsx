import { useParams } from "react-router-dom";
import axios from "axios";
import React, { useState, useEffect } from "react";

const StuffDetail = () => {
  const param = useParams();
  const id = param.id;

  const [stores, setStores] = useState([]);
  const [release, setRelease] = useState([]);
  const [product, setProduct] = useState([]);

  useEffect(() => {
    const getDetail = async () => {
      try {
        const response = await axios.get(`/find-one?pId=${id}`);
        console.log(response.data);
        setStores(response.data.insertLogs);
        setProduct(response.data.product);
        setRelease(response.data.releaseLogs);
      } catch (e) {
        console.error(e.message);
      }
    };

    getDetail();
  }, []);

  return (
    <div
      style={{ backgroundColor: "black", height: "100vh", paddingTop: "70px" }}
    >
      <div className="detail">
        <div className="detail_wrapper">
          <div className="detail_row">
            <div className="detail_cell color">상품명</div>
            <div className="detail_cell color">가격</div>
            <div className="detail_cell color">바코드</div>
            <div className="detail_cell color">재고</div>
          </div>
          <div className="detail_row">
            <div className="detail_cell height">{product.pname}</div>
            <div className="detail_cell height">${product.price}</div>
            <div className="detail_cell height">{product.pcode}</div>
            <div className="detail_cell height">{product.cnt}</div>
          </div>
        </div>
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
  );
};

export default StuffDetail;
