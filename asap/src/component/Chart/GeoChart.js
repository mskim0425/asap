import Chart from "react-google-charts";


export default function GeoChart() {
    const data = [
        ['Country', 'Popularity'],
        ['Germany', 200],
        ['United States', 300],
        ['Brazil', 400],
        ['Canada', 500],
        ['France', 600],
        ['RU', 700]
    ]

    const options = {
        width: "99%",
        height: "99%",
    }

    return (
        <div className="chartbox">
            <Chart className="chartCSS" chartType="GeoChart" data={data} options={options} />
        </div>
    )
}