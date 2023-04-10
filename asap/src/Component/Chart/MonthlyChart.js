import { useEffect, useState } from "react";
import Chart from "react-google-charts";

import { GetMonthlyData } from "../../apis/GetMonthlyData";

export default function MonthlyChart () {
    const [data, setData] = useState([['Month', '재고', '입고', '출고'],[0,0,0,0]])
    const [year, setYear] = useState(new Date().getFullYear())

    const options = {
        hAxis: {title: "Month"},
        series: {0: {type: "line", targetAxisIndex: 1}, 1: {type: "bars", targetAxisIndex: 0}, 2: {type:"bars", targetAxisIndex: 0}},
        width: "99%",
        height: "90%",
        colors: ['#000000', '#F0453C', '#697EFF'],
        animation: { duration: 700, easing: "inAndOut", startup: true }
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
            <h2>{`${year}년 월간 재고 흐름`}</h2>
            <input className="monthlyYearHandleInput" type="number" value={year} onChange={(e) => yearHandler(e)}/>
            <Chart className="chartCSS" chartType="ComboChart" data={data} options={options}/>
        </div>
    )
}
