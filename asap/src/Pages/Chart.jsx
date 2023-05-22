import "../style/Chart.css";
import axios from "axios";
import MonthlyChart from "../Component/Chart/MonthlyChart";
import ReactApexChart from "react-apexcharts";
import DailyCheckById from "../Component/Chart/DailyCheckbyItem";
import { useEffect, useState } from "react";
import { GetDailyRecord } from "../apis/GetDailyRecord";
import { commaChecker } from "../Function/commaChecker";

const Chart = () => {
  const [dailyData, setDailyData] = useState(new Array(8).fill("Loding..."));
  const [topTenData, setTopTenData] = useState([]);
  const [totalStacked, setTotalStacked] = useState();

  useEffect(() => {
    const dailyData = async () => {
      const getData = await GetDailyRecord();
      setDailyData(getData);
      setTotalStacked(commaChecker(getData[6]));
    };

    dailyData();
  }, []);

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

      setTopTenData(newData);
    } catch (error) {
      console.error(error);
    }
  };

  useState(() => {
    dataFetch();
  }, []);

  //도넛 차트 데이터 및 옵션
  const donutData = {
    series: [dailyData[6], dailyData[7]],
    options: {
      chart: {
        type: "donut",
        foreColor: "#ffffff",
      },
      colors: ["#2FFB6A", "#E14F61"],
      // fill: {
      //데이터색
      // colors: ["#2FFB6A", "#E91E63"],
      // },
      dataLabels: {
        //데이터 글씨색
        // style: {
        //   colors: ["#F44336", "#E91E63", "#9C27B0"],
        // },
      },

      legend: {
        position: "left",
        fontSize: "15px",
      },
      responsive: [
        {
          breakpoint: 480,
        },
      ],
      plotOptions: {
        pie: {
          donut: {
            labels: {
              show: true,
              // total: {
              //   showAlways: true,
              //   show: true,
              //   label: "ALARM",
              //   fontSize: "12px",
              //   color: "red",
              // },
              value: {
                fontSize: "22px",
                show: true,
                color: "white",
              },
            },
          },
        },
      },
      labels: ["총 입고량", "총 출고량"],
      // title: {
      // text: "이벤트별 통계",
      // align: "center",
      // },
    },
  };

  return (
    <div id="chartContainer">
      <div id="wrapper">
        <div className="widthSetting">
          <div id="monthlyChart">
            <MonthlyChart />
          </div>
          <div id="quantityChart">
            {/* 입고량 */}
            <div className="stockInfo">
              <h2>입고</h2>
              <div>
                <div className="infoTitle color">총 입고량</div>
                <div>{`${commaChecker(dailyData[6])}`}</div>
              </div>
              <div>
                <div className="infoTitle color">입고량 많은 상품</div>
                <div>{`${dailyData[0]} in ${dailyData[1]}`}</div>
              </div>
              <div>
                <div className="infoTitle color">입고량 많은 창고</div>
                <div>{`${dailyData[4]}`}</div>
              </div>
            </div>
            <div className="donutChart">
              <ReactApexChart
                options={donutData.options}
                series={donutData.series}
                type="donut"
              />
            </div>
            {/* 출고량 */}
            <div className="stockInfo">
              <h2>출고</h2>
              <div>
                <div className="infoTitle">총 출고량</div>
                <div>{`${commaChecker(dailyData[7])}`}</div>
              </div>
              <div>
                <div className="infoTitle">출고량 많은 상품</div>
                <div>{`${dailyData[2]} in ${dailyData[3]}`}</div>
              </div>
              <div>
                <div className="infoTitle">출고량 많은 창고</div>
                <div>{`${dailyData[5]}`}</div>
              </div>
            </div>
          </div>
          {/* 21일간 입출고현황 */}
          <div id="currentSituation">
            <DailyCheckById />
          </div>
          {/* 출고금액 */}
          {/* <div id="releasePrice">

        </div> */}
          <div id="top10">
            <div>
              <h2>일일 입고량 TOP 10</h2>
              <table>
                <tr>
                  <th>No.</th>
                  <th>상품명</th>
                  <th>입고량</th>
                </tr>
                <tbody>
                  {topTenData.map((el, index) => {
                    return (
                      <tr key={el} className={index % 2 === 0 ? "even" : ""}>
                        <td>{index + 1}</td>
                        <td>{el[0]}</td>
                        <td>{commaChecker(el[1])}</td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
            <div>
              <h2>일일 출고량 TOP 10</h2>
              <table>
                <tr>
                  <th>No.</th>
                  <th>상품명</th>
                  <th>출고량</th>
                </tr>
                <tbody>
                  {topTenData.map((el, index) => {
                    return (
                      <tr key={el} className={index % 2 === 0 ? "even" : ""}>
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
        </div>
      </div>
    </div>
  );
};

export default Chart;
