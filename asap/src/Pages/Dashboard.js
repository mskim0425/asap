import CalendarChart from "../Component/Chart/CalendarChart";
import GeoChart from "../Component/Chart/GeoChart";
import MonthlyChart from "../Component/Chart/MonthlyChart";
import PieChart from "../Component/Chart/PieChart";
import TodayRecord from "../Component/Chart/TodayRecord";
import TotalProduct from "../Component/Chart/TotalProduct";
import SSE from "../Component/SSE";
import Top10 from "../Component/Top10";

import "./Dashboard.css";

export default function Dashboard() {

  return (
    <section className="Dashboard">
      <div className="leftBox">
        <div className="lb-left">
          <div className="lb-left-top chartSection">
            <h2>TODAY</h2>
            <TodayRecord />
          </div>
          <div className="lb-left-middle chartSection">
            <Top10/>
          </div>
          <div className="lb-left-bottom chartSection">
            <h2>Our World Storage</h2>
            {/* <GeoChart /> */}
          </div>
        </div>
        <div className="lb-right">
          <div className="lb-right-top chartSection">
            <MonthlyChart />
          </div>
          <div className="lb-right-middle chartSection">
            <h2>Daily Stock Flow by Product</h2>
          </div>
          <div className="lb-right-bottom chartSection">
            <TotalProduct/>
          </div>
        </div>
      </div>

      <div className="rightBox">
        <SSE />
      </div>
    </section>
  );
}
