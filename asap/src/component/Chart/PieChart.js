import axios from "axios";
import { useEffect, useState } from "react";
import { Chart } from "react-google-charts";

export default function PieChart() {
  const [storeData, setStoreData] = useState()
  const [releaseData, setReleaseData] = useState()

  const dataFetch = async () => {
    try{
      const response = await axios.get('https://a61c-183-98-54-250.jp.ngrok.io/api/product-rank', {headers: {'ngrok-skip-browser-warning': 'none'}})

      const newStoreData = [["상품명", "입고량"]]
      const newReleaseData = [["상품명", "출고량"]]

      const insertFetchData = response.data.insertRankDto
      const releaseFetchData = response.data.releaseRankDto

      for(let i = 0; i < insertFetchData.length; i++){
        newStoreData.push([insertFetchData[i].pName, insertFetchData[i].insertCnt])
        newReleaseData.push([releaseFetchData[i].pName, releaseFetchData[i].releaseCnt])
      }

      setStoreData(newStoreData)
      setReleaseData(newReleaseData)
    }
    catch(error){
      console.error(error)
    }
  } 

  useEffect(()=>{
    dataFetch()
  }, [])

  const options = {
    legend: 'none',
    title: "일별 입고량 TOP 10",
    pieSliceText: "label",
    pieHole: 0.3,
    width: "99%",
    height: "99%"
  };

  const options2 = {
    legend: 'none',
    title: "일별 출고량 TOP 10",
    pieSliceText: "label",
    pieHole: 0.3,
    width: "99%",
    height: "99%"
  };

  return (
    <div className="chartbox">
      <Chart className="chartCSS" chartType="PieChart" data={storeData} options={options} />
      <Chart className="chartCSS" chartType="PieChart" data={releaseData} options={options2} />
    </div>
  );
}
