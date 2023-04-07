import { useEffect, useState } from "react"
import { GetTodayRecord } from "../../apis/GetTodayRecord"

export default function TodayRecord () {

    useEffect(()=>{
        const todayData = async () => {
            const getData = await GetTodayRecord()
            console.log(getData)
        }

        todayData()
    }, [])

    return (
        <div>
            hh
        </div>
    )
}