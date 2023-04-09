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
            setMessage([...message, data])
        })
    }, [])

    return (
        <div>
            {message.length > 0 ? message.map((el,index) => {
                return (
                    <div key={index}>
                        <div>{el}</div>
                    </div>
                )
            }) : null}
        </div>
    )
}
