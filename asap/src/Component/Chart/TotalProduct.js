import { useEffect, useState } from "react";
import { Chart } from "react-google-charts";
import { GetTotalSales } from "../../apis/GetTotalSales";
import {format} from 'date-fns'

export default function TotalProduct() {
    const [data, setData] = useState([["Year", "Money"], [0,0]])
    const [startDate, setStartDate] = useState(format(new Date(Date.now() - 7 * 24 * 60 * 60 * 1000), "yyyy-MM-dd"))
    const [endDate, setEndDate] = useState(format(Date.now(), "yyyy-MM-dd"))

    const options = {
        title: "기간 내 전체 출고 금액",
        animation: { duration: 500, easing: "inAndOut", startup: true },
        width: "99%",
        height: "99%"
    };

    const yearHandler = (e) => {
        e.target.name === "startDate" ? setStartDate(e.target.value) : setEndDate(e.target.value)
    }

    useEffect(()=>{
        const TotalProductData = async () => {
            const getData = await GetTotalSales(startDate, endDate)
            setData(getData)
        }   
        TotalProductData()
    }, [startDate, endDate])

    return (
        <div className="totalSalesChartSection">
            <h2>Daily Total Sales</h2>
            <label>
                Start Date
                <input type={"date"} name="startDate" onChange={(e) => yearHandler(e)}/>
            </label>
            <label>
                End Date
                <input type={"date"} name="endDate" onChange={(e) => yearHandler(e)}/>
            </label>
            <Chart className="chartCSS" chartType="LineChart" data={data} options={options} />
        </div>
    );
}