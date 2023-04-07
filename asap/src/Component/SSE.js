import { useEffect } from "react"

export default function SSE () {
    useEffect(() => {
        const eventSourse = new EventSource(`${process.env.REACT_APP_SERVER_URL}/api/connect`, {withCredentials: true})
        
        eventSourse.onmessage = function (event) {
            console.log(event)
        }
        
    }, [])

    return (
        <div>hh</div>
    )
}