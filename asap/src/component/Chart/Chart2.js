import { Chart } from "react-google-charts";

export default function Chart2() {
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
    title: "카테고리별 판매량",
    pieHole: 0.3,
  };

  return (
    <section className="chart2">
      <Chart chartType="PieChart" data={data} options={options} />
      <div>Hi I`m PieChart</div>
    </section>
  );
}
