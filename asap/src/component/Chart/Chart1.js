import { Chart } from "react-google-charts";

export default function Chart1() {
  const data = [
    ["Year", "Many"],
    [2022, 300],
    [2021, 250],
    [2020, 330],
    [2019, 278],
    [2018, 150],
    [2017, 100],
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
