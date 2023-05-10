import { useEffect, useState } from "react"

import "../style/SSE.css"

export default function SSE () {
    const [message, setMessage] = useState([])
    
    useEffect(() => {
        const eventSourse = new EventSource(`${process.env.REACT_APP_SERVER_URL}/connect`, {
            "withCredentials": true,
            'Content-Type': 'text/event-stream',
            'Connection': 'keep-alive',
            'Cache-Control': 'no-cache',
            "Accept-Encoding": "gzip,deflate, br",
            "Accept" : "text/event-stream"
        })

        eventSourse.addEventListener('open', async (e) =>{
            setMessage(["Connect Success !"])
        })

        eventSourse.addEventListener('sse', async (e) => {
            let newdata = e.data

            if(e.data.includes("연결")){
                setMessage([newdata])

            }else if(e.data.includes("알림")){
                newdata = JSON.parse(newdata)
                setMessage([...message, newdata])

            }else if(e.data.includes("time")){
                newdata = "SSE 연결이 종료되었습니다"
                setMessage([...message, newdata])
            }
        })

        eventSourse.onerror = (error) => {
            eventSourse.close()
        }

        return () => {
            eventSourse.close()
        }

    }, [])

    return (
        <div className="sse">
            {message.length > 0 ? message.map((el,index) => {
                return (
                    <div key={index}
                        className={
                            el.includes("알림") ? (el.includes("입고") ? "alert in" : "alert out")
                            : (el.includes("종료") ? "alert end": "alert error")
                        }>
                        <span className="alertText">{el}</span>
                    </div>
                )}) : null}
        </div>
    )
}