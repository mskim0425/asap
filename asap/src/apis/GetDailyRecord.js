import axios from "axios"

export const GetDailyRecord = async (date) => {
    try{
        const response = await axios.get(`/six-value`)
        const data = response.data
        const todayRecordData  = new Array(8).fill("Loding...!")
        todayRecordData[0] = data?.max_receive_item.item
        todayRecordData[1] = data?.max_receive_item.warehouse
        todayRecordData[2] = data?.max_release_item.item
        todayRecordData[3] = data?.max_release_item.warehouse
        todayRecordData[4] = data?.max_receive_warehouse
        todayRecordData[5] = data?.max_release_warehouse
        todayRecordData[6] = data?.total_pRelease
        todayRecordData[7] = data?.total_pinsert
        // [
        //         data?.max_receive_item.item,
        //         data?.max_receive_item.warehouse,
        //         data?.max_release_item.item,
        //         data?.max_release_item.warehouse,
        //         data?.max_receive_warehouse,
        //         data?.max_release_warehouse,
        //         data?.total_pRelease,
        //         data?.total_pinsert
        //     ]

        return todayRecordData
    }
    catch(error){
        console.error(error)
    }
}
