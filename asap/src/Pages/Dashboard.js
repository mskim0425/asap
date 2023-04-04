import Chart1 from "../component/Chart/Chart1";
import ComboChart from "../component/Chart/ComboChart";
import GeoChart from "../component/Chart/GeoChart";
import PieChart from "../component/Chart/PieChart";

import "./Dashboard.css";

function Dashboard() {

  return (
    <section className="Dashboard">
      <div className="leftBox">
        <div className="left-up">
          <div className="left-up-chart">
            <Chart1 />
          </div>
          <div className="left-up-chart">
            <ComboChart data={ComboData} title={"월별 전체 현황"}/>
          </div>
        </div>

        <div className="left-down">
          <div className="left-down-chart">
            <GeoChart/>
          </div>
          <div className="left-down-chart">
            <input />
            <ComboChart data={ComboData2} title={"아이템별 입출고량"} />
          </div>
          <div className="left-down-chart">
            <PieChart/>
          </div>
        </div>
      </div>

      <div className="rightBox">
        <div className="test">hello</div>
        {todo.map(el => {return <div className="test2">{el}</div>})}
      </div>
    </section>
  );
}

export default Dashboard;

const ComboData = [
  ['Month', '재고', '입고', '출고'],
  ['2022/11',  165,      938,         522],
  ['2022/12',  135,      1120,        599],
  ['2023/01',  157,      1167,        587],
  ['2023/02',  139,      1110,        615],
  ['2023/03',  136,      691,         629]
]

const ComboData2 = [
  ['Date', '재고', '입고', '출고'],
  ['2023/3/27',  16,      98,         52],
  ['2023/3/28',  15,      120,        59],
  ['2023/3/29',  17,      167,        57],
  ['2023/3/30',  19,      110,        65],
  ['2023/3/31',  16,      61,         69],
  ['2023/4/1',  18,      60,         81],
  ['2023/4/2',  17,      68,         69],
  ['2023/4/3',  20,      120,         50]
]

const todo = ["차트 추가", "추가할 부분 레이아웃"]