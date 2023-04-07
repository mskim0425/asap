import { useEffect, useState } from "react";
import Chart from "react-google-charts";
import { getMonthlyData } from "../../apis/getMonthlyData";

export default function ComboChart () {
    const [data, setData] = useState()

    const options = {
        title: "월간데이터",
        animation: { duration: 700, easing: "inAndOut", startup: true },
        vAxis: {title: "개수"},
        hAxis: {title: "Month"},
        seriesType: 'bars',
        series: {0: {type: "line"}},
        width: "99%",
        height: "99%",
    }

    useEffect(()=>{
        const monthlyData = async () => {
            const getData = await getMonthlyData()
            setData(getData)
        }
        monthlyData()
    },[])

    return (
        <div className="chartbox">
            <Chart className="chartCSS" chartType="ComboChart" data={data} options={options}/>
        </div>
    )
}

