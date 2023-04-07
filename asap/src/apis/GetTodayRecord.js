import axios from "axios"

export const GetTodayRecord = async (date) => {
    try{
        const response = await axios.get(`/six-value?date=2023-04-07`,{
            headers: {
                'ngrok-skip-browser-warning': 'none'
            }
        })

        console.log(response)
    }
    catch(error){
        console.error(error)
    }
}
