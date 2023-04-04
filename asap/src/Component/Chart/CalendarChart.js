import Chart from "react-google-charts";

export default function CalendarChart () {
    const data = [
        [{ type: 'date', id: 'Date' }, { type: 'number', id: 'Won/Loss' }],
        [new Date(2023, 3, 31), 38447],
      ];
      const options = {
        title: "연간 기록",
        width: 1000,
        height: `50%`
      };
    
      return (
        <section className="chart3">
          <Chart chartType="Calendar" data={data} options={options} />
        </section>
      );
}