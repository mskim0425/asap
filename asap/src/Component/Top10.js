import axios from "axios"
import { useState } from "react"

export default function Top10 (){
    const [data, setData] = useState([])
    const dataFetch = async () => {
        try{
            const response = await axios.get('/product-rank', {
                headers: {
                    'ngrok-skip-browser-warning': 'none'
                }
            })
            const insertRankData = response.data.insertRankDto
            const releaseRankData = response.data.releaseRankDto
            
            const newData = []

            for(let i = 0; i < insertRankData.length; i++){
                newData.push([insertRankData[i].pName, insertRankData[i].insertCnt, releaseRankData[i].pName, releaseRankData[i].releaseCnt])
            }

            setData(newData)
        }catch(error){
            console.error(error)
        }
    }

    useState(()=>{
        dataFetch()
    },[])

    return (
        <table className="top10Section">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>상품명</th>
                    <th>입고량</th>
                </tr>
            </thead>
            <tbody>
                    {data.map((el, index)=>{
                        return (
                        <tr key={el}>
                            <td>{index+1}</td>
                            <td>{el[0]}</td>
                            <td>{el[1]}</td>
                        </tr>)
                    })}
            </tbody>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>상품명</th>
                    <th>출고량</th>
                </tr>
            </thead>
            <tbody>
                    {data.map((el, index)=>{
                        return (
                        <tr key={el}>
                            <td>{index+1}</td>
                            <td>{el[2]}</td>
                            <td>{el[3]}</td>
                        </tr>)
                    })}
            </tbody>
        </table>
    )
}