import Chart from "react-google-charts";
import axios from "axios"
import { useEffect, useState } from "react";

export default function GeoChart() {
    const [data, setData] = useState()
    
    const dataFetch = async () => {
        try{
            const response = await axios.get(`/country-product-status`, {
                headers: {
                    'ngrok-skip-browser-warning': 'none'
                }
            })

            const newData = [['Country', 'Popularity']]
            response.data.forEach(el => newData.push([el.countryName, el.productCnt]))
            setData(newData)
        }
        catch(error){
            console.error(error)
        }
    }
    
    useEffect(()=>{
        dataFetch()
    }, [])

    const options = {
        width: "90%",
        height: "90%",
    }

    return (
        <div className="chartbox">
            <Chart className="chartCSS" chartType="GeoChart" data={data} options={options} />
        </div>
    )
}