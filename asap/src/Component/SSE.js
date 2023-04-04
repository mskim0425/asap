import { useEffect } from "react"

export default function SSE () {
    useEffect(() => {
        const eventSourse = new EventSource("https://a61c-183-98-54-250.jp.ngrok.io/api/connect", {withCredentials: true})
        
        eventSourse.onmessage = function (event) {
            console.log(event)
        }
        
    }, [])

    return (
        <div>hh</div>
    )
}