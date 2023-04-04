import Chart from "react-google-charts";

export default function ComboChart ({data, title}) {


    const options = {
        title,
        animation: { duration: 1000, easing: "inAndOut", startup: true },
        vAxis: {title: "개수"},
        hAxis: {title: "Month"},
        seriesType: 'bars',
        series: {0: {type: "line"}},
        width: "99%",
        height: "99%",
    }

    return (
        <div className="chartbox">
            <Chart className="chartCSS" chartType="ComboChart" data={data} options={options}/>
        </div>
    )
}