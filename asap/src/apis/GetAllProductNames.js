import axios from "axios"

export const getAllProductNames = async () => {
    try{
        const response = await axios.get(`/product-names`)
        const data = response.data

        return data
    }
    catch(error){
        console.error(error)
    }
}