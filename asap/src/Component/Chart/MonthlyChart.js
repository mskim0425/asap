import { useEffect, useState } from "react";
import Chart from "react-google-charts";

import { GetMonthlyData } from "../../apis/GetMonthlyData";

export default function MonthlyChart() {
  const [data, setData] = useState([
    ["Month", "재고", "입고", "출고"],
    [0, 0, 0, 0],
  ]);

  const [year, setYear] = useState(new Date().getFullYear());

  const options = {
    series: {
      0: { type: "line", targetAxisIndex: 1, color: "#2FFB6A" },
      1: { type: "bars", targetAxisIndex: 0, color: "#E14F61" },
      2: { type: "bars", targetAxisIndex: 0, color: "#32B2CF" },
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

  const yearHandler = (e) => {
    setYear(e.target.value);
  };

  useEffect(() => {
    const MonthlyData = async () => {
      const getData = await GetMonthlyData(year);
      setData(getData);
    };

    MonthlyData();
  }, [year]);

  return (
    <div className="monthlyChartSection">
      <h2>{`${year}년 월간 재고 흐름`}</h2>
      <input
        className="monthlyYearHandleInput"
        type="number"
        value={year}
        onChange={(e) => yearHandler(e)}
      />
      <Chart
        className="chartCSS"
        chartType="ComboChart"
        data={data}
        options={options}
      />
    </div>
  );
}
