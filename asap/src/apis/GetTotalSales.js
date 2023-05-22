import axios from "axios"

export const GetTotalSales = async (startDate, endDate) => {

    try{
        const response = await axios.get(`/total-product-amount?startDate=${startDate}&endDate=${endDate}`)
        const data = [["Year", "Money"]]
        const totalData = response.data
        totalData.forEach(el => data.push([el.releaseat, el.money]))

        return data
    }
    catch(error){
        console.error(error)
    }
}
