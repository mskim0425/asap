import { useEffect, useState } from "react"
import Chart from "react-google-charts"
import { getAllProductNames } from "../../apis/GetAllProductNames"
import { getDailyCheckByProductName } from "../../apis/GetDailyCheckByProductName"

export default function DailyCheckById(){
    const [productNames, setProductNames] = useState([])
    const [checkData, setCheckData] = useState([])
    const [selectName, setSelectName] = useState()

    
    // 모든 제품명 가져오기
    useEffect(()=>{
        const getProductNames = async () => {
            const getProductNamesData = await getAllProductNames()
            setProductNames(getProductNamesData)            
        }
        getProductNames()
    }, [])

    // 제품명 선택
    const productNameHandler = (e)=> {
        setSelectName(e.target.value)
    }

    // 제품명별 데이터 가져오기
    useEffect(()=>{
        const getCheckDataByProduct = async () => {
            const getData = await getDailyCheckByProductName(selectName)
            setCheckData(getData)
        }
        
        getCheckDataByProduct()
    }, [selectName])

    const options = {
        animation: { duration: 700, easing: "inAndOut", startup: true },
        width: "99%",
        height: "99%",
        vAxis: {minValue: 0},
        legend: {position: 'top', maxLines: 3},
        colors: ['#F0453C', '#697EFF']
    }

    return (
        <div style={{height: "80%"}}>
            <h2>{`최근 21일간 ${selectName ?? "제품별"} 입출고 현황`}</h2>
            <select onChange={(e) => productNameHandler(e)}>
                <option value="제품을 선택하세요">{"제품을 선택하세요"}</option>
                {productNames.map((el, index) => {
                    return <option value={el} key={el}>{el}</option>
                })}
            </select>
            {selectName === undefined ? <div className="nonSelected" style={{color: "red"}}>👆 선택하세요 !</div> :
            <Chart className="chartCSS" chartType="ColumnChart" data={checkData} options={options} />}
        </div>
    )
}