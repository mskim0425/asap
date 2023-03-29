import { useNavigate } from "react-router-dom";
import "./Sidebar.css";

export default function Sidebar() {
  const navigate = useNavigate();

  const link = (e) => {
    if (e.target.innerText === "Dashboard") {
      navigate("/");
    } else {
      navigate(e.target.innerText);
    }
  };

  const tab = ["Dashboard", "Stuff"];

  return (
    <section className="Sidebar">
      {tab.map((el) => {
        return (
          <div key={el} onClick={link} className="menu-button">
            {el}
          </div>
        );
      })}
    </section>
  );
}
