import { useNavigate } from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();

  const menuList = [
    { id: 0, icon: "fa fa-bar-chart", title: "메인" },
    { id: 1, icon: "fa fa-bar-chart", title: "대시보드" },
    { id: 2, icon: "fa fa-cog", title: "관리자" },
  ];

  // const lists = document.querySelectorAll(".nav li");
  // function active() {
  // lists.forEach((list) => list.classList.remove("active"));
  // this.classList.add("active");
  // }

  // lists.forEach((list) => list.addEventListener("click", active));

  const navigator = (id) => () => {
    const lists = document.querySelectorAll(".nav li");
    function active() {
      lists.forEach((list) => list.classList.remove("active"));
      this.classList.add("active");
    }

    lists.forEach((list) => list.addEventListener("click", active));

    //클릭된 메뉴 체크

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

  function menuToggle() {
    const header = document.querySelector("#header");
    header.classList.toggle("active");
  }

  return (
    <div id="header">
      <a href="#none" className="logo">
        ASAP
      </a>
      <div className="menuToggle" onClick={menuToggle}></div>
      <ul className="nav">
        <li className="active" onClick={navigator(0)}>
          <a href="#none">Home</a>
        </li>
        <li onClick={navigator(1)}>
          <a href="#none">Dashboard</a>
        </li>
        <li onClick={navigator(2)}>
          <a href="#none">Admin</a>
        </li>
      </ul>
      {/* <ul className="nav">
        {menuList.map((list, index) => {
          return (
            <li key={index} onClick={navigator(list.id)}>
              <a href="#none">{list.title}</a>
            </li>
          );
        })}
      </ul> */}
    </div>
  );
};

export default Header;
