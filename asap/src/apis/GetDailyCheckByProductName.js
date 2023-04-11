import axios from "axios"

export const getDailyCheckByProductName = async (pName) =>{
    try{
        const response = await axios.get(`/cnt-product-by-date?pName=${pName}`,{
            headers: {
                'ngrok-skip-browser-warning': 'none'
            }
        })
        const data = response.data
        const checkData = [['Date', '입고', '출고']]
        data.forEach(el => checkData.push([el.date, el.insertCnt, el.releaseCnt]))
        return checkData
    }
    catch(error){
        console.error(error)
    }
}