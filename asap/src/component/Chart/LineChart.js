import React from "react";
import { useEffect, useState } from "react";
import { Chart } from "react-google-charts";
import { getTime } from "../../function/getTime";
import { testdata } from "./data";
import "../../Pages/Dashboard.css"

export default function LineChart() {
  const [data, setData] = useState([["Year", "$"]]);
  const [start, setStart] = useState()
  const [end, setEnd] = useState()

  const options = {
    title: "기간 내 전체 출고 금액",
    animation: { duration: 500, easing: "inAndOut", startup: true },
    width: "99%",
    height: "99%"
  };

  const dateHandler = (e) => {
    const dateValue = getTime(e.target.value)
    const inputName = e.target.name

    if(inputName === 'startDate'){
      setStart(dateValue)
    }else{
      setEnd(dateValue)
    }
  }

  const clickHandler = (e) => {
    // console.log(data.slice(1).filter(el => getTime(el[0]) >= start && getTime(el[0]) >= end))
    setData([["Year", "Many"], ...data.slice(1).filter(el => getTime(el[0]) >= start && getTime(el[0]) >= end)])
  }

  useEffect(() => {
    const dataTest = [];
    testdata.sort((a,b) => getTime(a.date) - getTime(b.date))
    testdata.forEach(el => dataTest.push([el.date, el.count]))
    setData([...data, ...dataTest]);
  }, []);

  return (
    <div className="chartbox">
      <Chart className="chartCSS" chartType="LineChart" data={data} options={options} />
      {/* <input type="date" name="startDate" onChange={dateHandler}/>
      <input type="date" name="endDate" onChange={dateHandler}/>
      <button onClick={clickHandler}>btn</button> */}
    </div>
  );
}
