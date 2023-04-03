import Chart1 from "../component/Chart/Chart1";
import Chart2 from "../component/Chart/Chart2";
import Chart3 from "../component/Chart/Chart3";
import "./Dashboard.css";

function Dashboard() {
  const graph = [];
  for(let i = 0; i < 60; i++){
    graph.push(i)
  }

  return (
    <section className="Dashboard">
      {/* <div className="Chart">
        <Chart1 />
      </div>
      <div className="Chart">
        <Chart2 />
      </div>
      <div className="Chart">
        <Chart3 />
      </div> */}
      {/* {graph.map((el) => {
        return (
          <div key={el} className="Chart">
            {el}
          </div>
        );
      })} */}
      <div className="leftBox"></div>
      <div className="rightBox"></div>
    </section>
  );
}

export default Dashboard;
