import { useEffect, useState } from "react"

import "../style/SSE.css"

export default function SSE () {
    const [message, setMessage] = useState([])
    
    useEffect(() => {
        const eventSourse = new EventSource(`${process.env.REACT_APP_SERVER_URL}/connect`, {
            "withCredentials": true,
            'Content-Type': 'text/event-stream',
            'Cache-Control': 'no-cache',
            'Connection': 'keep-alive',
            "Accept-Encoding": "gzip,deflate, br",
            "Accept" : "text/event-stream"
        })

        eventSourse.addEventListener('sse', async (e) => {
            let newdata = e.data
            if(e.data.includes("{")){
                const test = [...newdata.split(`"`)]
                newdata = `${test[7]}\n${test[11]}`
            }
            setMessage([...message, newdata])
        })
    }, [])

    return (
        <div className="sse">
            {message.length > 0 ? message.map((el,index) => {
                return (
                    <label key={index}>
                        <input type="checkbox" className="alertCheckbox" autoComplete="off" />
                        <div className={el.includes("알림") ? (el.includes("입고") ? "alert in" : "alert out") : "alert error"}>
                            <span className="alertClose">X</span>
                            <span className="alertText">{el}
                                <br className="clear"/>
                            </span>
                        </div>
                    </label>
                )
            }) : null}
        </div>
    )
}