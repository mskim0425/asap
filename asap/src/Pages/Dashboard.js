import CalendarChart from "../Component/Chart/CalendarChart";
import ComboChart from "../Component/Chart/ComboChart";
import GeoChart from "../Component/Chart/GeoChart";
import LineChart from "../Component/Chart/LineChart";
import PieChart from "../Component/Chart/PieChart";
import SSE from "../Component/SSE";
import Top10 from "../Component/Top10";

import "./Dashboard.css";

function Dashboard() {

  return (
    <section className="Dashboard">
      <div className="leftBox">
        <div className="lb-left">
          <div className="lb-left-top chartSection">
            <h2>TODAY</h2>
          </div>
          <div className="lb-left-middle chartSection">
            <h2>Daily TOP 10</h2>
            <Top10/>
          </div>
          <div className="lb-left-bottom chartSection">
            <h2>Our World Storage</h2>
            <GeoChart />
          </div>
        </div>
        <div className="lb-right">
          <div className="lb-right-top chartSection">
            <h2>Monthly Stock Flow</h2>
          </div>
          <div className="lb-right-middle chartSection">
            <h2>Daily Stock Flow by Product</h2>
          </div>
          <div className="lb-right-bottom chartSection">
            <h2>Daily Total Sales</h2>
          </div>
        </div>
      </div>

      <div className="rightBox">
        {/* <SSE /> */}
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
  ['2023/3/15',  16,      98,         52],
  ['2023/3/16',  15,      120,        59],
  ['2023/3/17',  17,      167,        57],
  ['2023/3/18',  16,      98,         52],
  ['2023/3/19',  15,      120,        59],
  ['2023/3/20',  17,      167,        57],
  ['2023/3/21',  16,      98,         52],
  ['2023/3/22',  15,      120,        59],
  ['2023/3/23',  17,      167,        57],
  ['2023/3/24',  16,      98,         52],
  ['2023/3/25',  15,      120,        59],
  ['2023/3/26',  17,      167,        57],
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