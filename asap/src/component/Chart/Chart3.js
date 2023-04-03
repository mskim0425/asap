import Chart from "react-google-charts";

export default function Chart3 () {
    const data = [
        [{ type: 'date', id: 'Date' }, { type: 'number', id: 'Won/Loss' }],
        [new Date(2020, 1, 23), 38345],
        [new Date(2021, 1, 24), 38436],
        [new Date(2022, 2, 10), 38447],
      ];
      const options = {
        title: "연간 기록",
        width: 1000,
        height: `1700`
      };
    
      return (
        <section className="chart3">
          <Chart chartType="Calendar" data={data} options={options} />
        </section>
      );
}