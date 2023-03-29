import Chart1 from "../component/Chart/Chart1";
import Chart2 from "../component/Chart/Chart2";
import "./Dashboard.css";

function Dashboard() {
  const graph = [];
  for(let i = 0; i < 60; i++){
    graph.push(i)
  }

  return (
    <section className="Dashboard">
      <div className="Chart">
        <Chart1 />
      </div>
      <div className="Chart">
        <Chart2 />
      </div>
      {graph.map((el) => {
        return (
          <div key={el} className="Chart">
            {el}
          </div>
        );
      })}
    </section>
  );
}

export default Dashboard;
