import { Chart } from "react-google-charts";

export default function PieChart() {
  const data = [
    ["종류", "판매량"],
    ["셔츠", 300],
    ["바지", 250],
    ["모자", 330],
    ["시계", 278],
    ["양말", 150],
    ["속옷", 100],
  ];

  const options = {
    legend: 'none',
    title: "상품별 입고 현황 TOP 10",
    pieSliceText: "label",
    pieHole: 0.3,
    width: "99%",
    height: "99%"
  };

  const data2 = [
    ["종류", "판매량"],
    ["셔츠", 300],
    ["바지", 250],
    ["모자", 330],
    ["시계", 278],
    ["양말", 150],
    ["속옷", 100],
  ];

  const options2 = {
    legend: 'none',
    title: "상품별 출고 현황 TOP 10",
    pieSliceText: "label",
    pieHole: 0.3,
    width: "99%",
    height: "99%"
  };

  return (
    <div className="chartbox">
      <Chart className="chartCSS" chartType="PieChart" data={data} options={options} />
      <Chart className="chartCSS" chartType="PieChart" data={data2} options={options2} />
    </div>
  );
}
