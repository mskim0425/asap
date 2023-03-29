import { Chart } from "react-google-charts";

export default function Chart1() {
  const data = [
    ["Year", "Many"],
    ["2017년", 100],
    ["2018년", 150],
    ["2019년", 278],
    ["2020년", 330],
    ["2021년", 250],
    ["2022년", 300],
  ];
  const options = {
    title: "연간 생산량",
    animation: { duration: 1000, easing: "inAndOut", startup: true },
  };

  return (
    <section className="chart1">
      <Chart chartType="ColumnChart" data={data} options={options} />
      <div>Hi I`m Google Chart</div>
    </section>
  );
}
