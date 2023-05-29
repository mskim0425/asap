import { useEffect, useState } from "react";
import Chart from "react-google-charts";
import { getAllProductNames } from "../../apis/GetAllProductNames";
import { getDailyCheckByProductName } from "../../apis/GetDailyCheckByProductName";

export default function DailyCheckById() {
  const [productNames, setProductNames] = useState([]);
  const [checkData, setCheckData] = useState([]);
  const [selectName, setSelectName] = useState();

  // 모든 제품명 가져오기
  useEffect(() => {
    const getProductNames = async () => {
      const getProductNamesData = await getAllProductNames();
      setProductNames(getProductNamesData);
      setSelectName(getProductNamesData[0]);
      const getData = await getDailyCheckByProductName(getProductNamesData[0]);
      setCheckData(getData);
    };
    getProductNames();
  }, []);

  
  // 제품명별 데이터 가져오기
  const getCheckDataByProduct = async () => {
    const getData = await getDailyCheckByProductName(selectName);
    console.log("getData", selectName, getData);
    setCheckData(getData);
  };

  // 제품명 선택
  const productNameHandler = (e) => {
    setSelectName(e.target.value);
    getCheckDataByProduct();
  };

  useEffect(()=>{
    getCheckDataByProduct()
  }, [selectName])

  const options = {
    series: {
      0: { type: "bars", targetAxisIndex: 0, color: "#E14F61" },
      1: { type: "bars", targetAxisIndex: 0, color: "#32B2CF" },
    },
    title: "Month",
    backgroundColor: "#0C320C",
    pointSize: 7,
    titleTextStyle: {
      color: "white", // 타이틀 색
    },
    hAxis: {
      // 가로
      textStyle: {
        fontSize: "20px",
        color: "white",
      },
      baselineColor: "white", // 수직 왼쪽 선
    },
    vAxis: {
      // 세로
      minValue: 0,
      textStyle: {
        fontSize: "20px",
        color: "white",
      },
      gridlines: {
        // 중간의 선
        color: "#8898A1",
      },
      baselineColor: "8898A1", // 하단 선
    },
    legend: {
      // 항목
      textStyle: {
        fontSize: "20px",
        color: "white",
      },
    },
    width: "100%",
    height: "100%",
    animation: { duration: 700, easing: "inAndOut", startup: true },
  };

  return (
    <div>
      <h2>{`최근 21일간 ${selectName ?? "제품별"} 입출고 현황`}</h2>
      <select onChange={(e) => productNameHandler(e)}>
        {productNames.map((el, index) => {
          return (
            <option value={el} key={index}>
              {el}
            </option>
          );
        })}
      </select>
      <Chart
        className="chartCSS"
        chartType="ColumnChart"
        data={checkData}
        options={options}
      />
    </div>
  );
}
