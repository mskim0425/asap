import { useEffect, useState } from "react";
import Chart from "react-google-charts";
import { GetMonthlyData } from "../../apis/GetMonthlyData";

export default function MonthlyChart () {
    const [data, setData] = useState([['Month', '재고', '입고', '출고'],[0,0,0,0]])
    const [year, setYear] = useState(new Date().getFullYear())

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

    const yearHandler = (e) => {
        setYear(e.target.value)
    }

    useEffect(()=>{
        const MonthlyData = async () => {
            const getData = await GetMonthlyData(year)
            setData(getData)
        }
        MonthlyData()
    },[year])

    return (
        <div className="monthlyChartSection">
            <h2>{`${year} Monthly Stock Flow`}</h2>
            <input type="number" value={year} onChange={(e) => yearHandler(e)}/>
            <Chart className="chartCSS" chartType="ComboChart" data={data} options={options}/>
        </div>
    )
}
