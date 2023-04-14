import axios from "axios"

export const GetDailyRecord = async (date) => {
    try{
        const response = await axios.get(`/six-value`)
        const data = response.data
        const todayRecordData  = [
                data.max_receive_item.item,
                data.max_receive_item.warehouse,
                data.max_release_item.item,
                data.max_release_item.warehouse,
                data.max_receive_warehouse,
                data.max_release_warehouse,
                data.total_pRelease,
                data.total_pinsert
            ]

        return todayRecordData
    }
    catch(error){
        console.error(error)
    }
}
