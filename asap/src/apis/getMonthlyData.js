import axios from "axios"

export const getMonthlyData = async () => {
    try{
        const response = await axios.get('/monthly-stock-summary?year=2022',{
            headers: {
                'ngrok-skip-browser-warning': 'none'
            }
        })
        const data = [['Month', '재고', '입고', '출고']]
        const monthlyData = response.data
        monthlyData.forEach((el) => data.push([el.month, el.allQuantity, el.allReleaseAt, el.allInsert]))
        return data
    }
    catch(error){
        console.error(error)
    }
}
