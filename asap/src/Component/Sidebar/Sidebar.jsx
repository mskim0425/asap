import { useNavigate } from "react-router-dom";

import "./sidebar.css";

const Sidebar = () => {
  const navigate = useNavigate();

  const menuList = [
    { id: 0, icon: "fa fa-bar-chart", title: "메인" },
    { id: 1, icon: "fa fa-bar-chart", title: "대시보드" },
    { id: 2, icon: "fa fa-cog", title: "관리자" },
  ];

  const navigator = (id) => () => {
    //클릭된 메뉴 체크
    const menuList = document.querySelectorAll("#navigation ul li");
    menuList.forEach((menu) => menu.classList.remove("active"));
    menuList[id].classList.add("active");

    // 이동
    switch (id) {
      case 0:
        navigate("/");
        break;
      case 1:
        navigate("/dashboard");
        break;
      case 2:
        navigate("/admin");
        break;
      default:
    }
  };

  const foldMenu = () => {
    const navigation = document.querySelector("#navigation");
    navigation.classList.toggle("active");
  };

  return (
    <div id="container">
      <div id="navigation">
        <div id="toggle" onClick={foldMenu}>
          <i className="fa fa-bars" aria-hidden="true"></i>
        </div>
        <ul>
          {menuList.map((list, index) => {
            return (
              <li key={index} onClick={navigator(list.id)}>
                <a href="#none">
                  <span className="icon">
                    <i className={list.icon} aria-hidden="true"></i>
                  </span>
                  <span className="title">{list.title}</span>
                </a>
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
};

export default Sidebar;
