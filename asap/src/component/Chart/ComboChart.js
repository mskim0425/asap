import Chart from "react-google-charts";

export default function ComboChart ({data, title, width="99%", height="99%"}) {


    const options = {
        title,
        animation: { duration: 700, easing: "inAndOut", startup: true },
        vAxis: {title: "개수"},
        hAxis: {title: "Month"},
        seriesType: 'bars',
        series: {0: {type: "line"}},
        width,
        height,
    }

    return (
        <div className="chartbox">
            <Chart className="chartCSS" chartType="ComboChart" data={data} options={options}/>
        </div>
    )
}