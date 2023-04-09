import { useEffect, useState } from "react"

import { GetDailyRecord } from "../apis/GetDailyRecord"

import { commaChecker } from "../Function/commaChecker"

export default function DailyRecord () {
    const [data, setData] = useState(new Array(8).fill("Loding..."))

    useEffect(()=>{
        const dailyData = async () => {
            const getData = await GetDailyRecord()
            setData(getData)
        }
        
        dailyData()
    }, [])
    
    return (
        <div className="dailyRecordSection">
            <h2>{`2023-04-09 Daily Record`}</h2>
            <div className="dailyRecords">
                <div className="recordLine">
                    <div className="recordBox">
                        <h3>총 입고량</h3>
                        <p>{`${commaChecker(data[6])}`}</p>
                    </div>
                    <div className="recordBox">
                        <h3>총 출고량</h3>
                        <p>{`${commaChecker(data[7])}`}</p>
                    </div>
                </div>
                <div className="recordLine">
                    <div className="recordBox">
                        <h3>최대 입고 상품</h3>
                        <p>{`${data[0]}`}</p>
                        <span>{`in ${data[1]}`}</span>
                    </div>
                    <div className="recordBox">
                        <h3>최대 출고 상품</h3>
                        <p>{`${data[2]}`}</p>
                        <span>{`in ${data[3]}`}</span>
                    </div>
                </div>
                <div className="recordLine">
                    <div className="recordBox">
                        <h3>입고량 최대 창고</h3>
                        <p>{`${data[4]}`}</p>
                    </div>
                    <div className="recordBox">
                        <h3>출고량 최대 창고</h3>
                        <p>{`${data[5]}`}</p>
                    </div>
                </div>
            </div>
        </div>
    )
}