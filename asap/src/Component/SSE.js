import axios from "axios"
import { useEffect, useState } from "react"

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
            const data = e.data
            console.log("data", data)
            setMessage([e.data.title, e.data.content])
            console.log(message)
        })
    }, [])

    return (
        <div>
            {/* {message.length > 0 ? message.map(el => {
                return (
                    <div key={el}>
                        <div>{el[0]}</div>
                        <div>{el[1]}</div>
                    </div>
                )
            }) : null} */}
        </div>
    )
}
