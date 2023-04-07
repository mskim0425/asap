import axios from "axios"

export const GetTodayRecord = async (date) => {
    try{
        const response = await axios.get(`/six-value`,{
            headers: {
                'ngrok-skip-browser-warning': 'none'
            }
        })

        // console.log(response.data)
    }
    catch(error){
        console.error(error)
    }
}
