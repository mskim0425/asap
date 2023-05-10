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
            setMessage(["SSE Connect Success !"])
        })

        eventSourse.addEventListener('sse', async (e) => {
            let newdata = JSON.parse(e.data)
            if(!newdata.title.includes("SSE")){
                setMessage((old) => [...old, newdata.content])
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
                            el.includes("SSE") ? "alert start" : (el.includes("입고") ? "alert in" : "alert out")
                        }>
                        <span className="alertText">{el}</span>
                    </div>
                )}) : null}
        </div>
    )
}