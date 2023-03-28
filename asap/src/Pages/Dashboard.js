import "./Dashboard.css"

function Dashboard() {
  const graph = [1,2,3,4,5,6]

  return (
    <section className="Dashboard">
      {graph.map(el => {return <div key={el} className="graph">{el}</div>})}
    </section>
  );
}

export default Dashboard;
