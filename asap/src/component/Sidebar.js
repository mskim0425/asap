import { useNavigate } from "react-router-dom";
import { useState } from "react";
import "./Sidebar.css";

export default function Sidebar() {
  const [tabIndex, setTabIndex] = useState(1);
  const navigate = useNavigate();

  const handleClick = (index, event) => {
    setTabIndex(index);
    if (event.target.innerText === "Dashboard") {
      navigate("/");
    } else {
      navigate(event.target.innerText);
    }
  };

  const tab = [
    { id: 1, category: "Dashboard" },
    { id: 2, category: "Stuff" },
  ];

  return (
    <section className="sidebar">
      {tab.map((el, index) => {
        return (
          <div
            key={index}
            onClick={(e) => handleClick(index, e)}
            className={
              tabIndex === index ? "menu-button active" : "menu-button"
            }
          >
            {el.category}
          </div>
        );
      })}
    </section>
  );
}
