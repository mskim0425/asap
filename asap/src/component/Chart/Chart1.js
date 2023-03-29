import React from "react";
import { useEffect, useState } from "react";
import { Chart } from "react-google-charts";
import { getTime } from "../../function/getTime";
import { testdata } from "./data";

export default function Chart1() {
  const [data, setData] = useState([["Year", "Many"]]);
  const [start, setStart] = useState()
  const [end, setEnd] = useState()

  const options = {
    title: "연간 생산량",
    animation: { duration: 1000, easing: "inAndOut", startup: true },
    width: 1000,
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
    setData([["Year", "Many"], ...data.slice(1).filter(el => getTime(el[0]) >= start && getTime(el[0]) >= end)])
  }

  useEffect(() => {
    const dataTest = [];
    testdata.sort((a,b) => getTime(a.date) - getTime(b.date))
    testdata.forEach(el => dataTest.push([el.date, el.count]))
    setData([...data, ...dataTest]);
  }, []);

  return (
    <section className="chart1">
      <Chart chartType="ColumnChart" data={data} options={options} />
      <input type="date" name="startDate" onChange={dateHandler}/>
      <input type="date" name="endDate" onChange={dateHandler}/>
      <button onClick={clickHandler}>btn</button>
    </section>
  );
}
