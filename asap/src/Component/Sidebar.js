import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import "./Sidebar.css";

export default function Sidebar() {
  const navigate = useNavigate();
  const tabId = localStorage.getItem("tabId");

  const [tabIndex, setTabIndex] = useState(
    localStorage.getItem("tabId") !== null ? Number(tabId) : 1
  );

  useEffect(() => {
    setTabIndex(Number(tabId));
  });

  const handleClick = (id, event) => {
    localStorage.setItem("tabId", JSON.stringify(id));
    setTabIndex(id);
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
      {tab.map((el) => {
        return (
          <div
            key={el.id}
            onClick={(e) => handleClick(el.id, e)}
            className={
              tabIndex === el.id ? "menu-button active" : "menu-button"
            }
          >
            {el.category}
          </div>
        );
      })}
    </section>
  );
}
