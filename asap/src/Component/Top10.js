import axios from "axios";
import { useState } from "react";

import { commaChecker } from "../Function/commaChecker";

export default function Top10() {
  const [data, setData] = useState([]);
  const dataFetch = async () => {
    try {
      const response = await axios.get("/product-rank", {
        headers: {
          "ngrok-skip-browser-warning": "none",
        },
      });
      const insertRankData = response.data.insertRankDto;
      const releaseRankData = response.data.releaseRankDto;

      const newData = [];

      for (let i = 0; i < insertRankData.length; i++) {
        newData.push([
          insertRankData[i].pName,
          insertRankData[i].insertCnt,
          releaseRankData[i].pName,
          releaseRankData[i].releaseCnt,
        ]);
      }

      setData(newData);
    } catch (error) {
      console.error(error);
    }
  };

  useState(() => {
    dataFetch();
  }, []);

  return (
    <div className="top10Section">
      <div className="top10half">
      <h2>일일 입고량 TOP 10</h2>
      <table className="top10">
        <thead>
          <tr>
            <th>No.</th>
            <th>상품명</th>
            <th>입고량</th>
          </tr>
        </thead>
        <tbody>
          {data.map((el, index) => {
            return (
              <tr key={el} className={index%2===0 ? "even" : ""}>
                <td>{index + 1}</td>
                <td>{el[0]}</td>
                <td>{commaChecker(el[1])}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
      </div>
      <div className="top10half">
      <h2>일일 출고량 TOP 10</h2>
      <table className="top10">
        <thead>
          <tr>
            <th>No.</th>
            <th>상품명</th>
            <th>출고량</th>
          </tr>
        </thead>
        <tbody>
          {data.map((el, index) => {
            return (
              <tr key={el} className={index%2===0 ? "even" : ""}>
                <td>{index + 1}</td>
                <td>{el[2]}</td>
                <td>{commaChecker(el[3])}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
      </div>
    </div>
  );
}
