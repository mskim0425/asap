import "../style/Main.css";
import anime from "animejs/lib/anime.es.js";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Main = () => {
  const navigate = useNavigate();

  const menuList = [
    { id: 0, icon: "fa fa-bar-chart", title: "메인" },
    { id: 1, icon: "fa fa-bar-chart", title: "대시보드" },
    { id: 2, icon: "fa fa-cog", title: "관리자" },
  ];

  const navigator = (id) => () => {
    //클릭된 메뉴 체크
    const lists = document.querySelectorAll(".nav li");
    // function active() {
    lists.forEach((list) => list.classList.remove("active"));
    this.classList.add("active");
    // }

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

  // const lists = document.querySelectorAll(".nav li");
  // function active() {
  //   lists.forEach((list) => list.classList.remove("active"));
  //   this.classList.add("active");
  // }

  // lists.forEach((list) => list.addEventListener("click", active));

  function menuToggle() {
    const header = document.querySelector("#header");
    header.classList.toggle("active");
  }

  useEffect(() => {
    let container = document.querySelector(".container2");
    for (let i = 1; i <= 200; i++) {
      let dot = document.createElement("div");
      dot.classList.add("element");
      container.appendChild(dot);
    }

    let dotAll = document.querySelectorAll(".element");

    const animation = anime.timeline({
      targets: dotAll,
      // easing: "easeInOutExpo",
      loop: true,
      delay: anime.stagger(100, { grid: [10, 10], from: "center" }),
    });
    animation
      .add({
        rotateZ: 180,
        transelateY: anime.stagger(-20, {
          grid: [10, 10],
          from: "center",
          axis: "y",
        }),
        transelateX: anime.stagger(-20, {
          grid: [10, 10],
          from: "center",
          axis: "x",
          opacity: 1,
        }),
      })
      .add({
        borderRadius: 50,
      })
      .add({
        scale: 0.2,
        opacity: 1,
      })

      .add({
        scale: 1,
        opacity: 0,
      });
  }, []);

  return (
    <div>
      <div id="header">
        <a href="#none" className="logo">
          ASAP
        </a>
        <div className="menuToggle" onClick={menuToggle}></div>
        <ul className="nav">
          <li className="active">
            <a href="#home">Home</a>
          </li>
          <li>
            <a href="#stuff">Stuff</a>
          </li>
          <li>
            <a href="#contact">Contact</a>
          </li>
        </ul>
      </div>
      <section id="home">
        <div className="content">
          <h2>실시간 재고 관리 시스템</h2>
          <p>실시간으로 전세계 창고 재고 관리</p>
        </div>
        <div className="scan">
          <div className="qrcode"></div>
          <h3>QR Code Scanning...</h3>
          <div className="border"></div>
        </div>
      </section>
      <section id="stuff">
        <div className="container2"></div>
        <div className="squareText">
          <h3>QR CODE로 어디서든 물품 위치 확인 가능</h3>
          <span>
            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Enim
            nostrum perferendis incidunt natus consequuntur facilis repellat
            quasi facere fugiat quod eaque at illo vel tempora, exercitationem
            officia totam minima voluptatem officiis aperiam hic voluptates
            dignissimos ipsa. Ullam impedit nulla, consectetur recusandae quod
            mollitia modi iure numquam, cum magni alias debitis!
          </span>
        </div>
      </section>
      <section id="contact">
        <div className="contactText">
          <h2>Ways To Contact Us</h2>
          <p>
            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Nemo,
            possimus dignissimos. Doloremque vel ipsa harum, nostrum suscipit
            sit odit perspiciatis. Sit dolores ex fugit, labore fuga quis optio
            eos accusamus?
          </p>
        </div>
        <div className="content">
          <div className="contentWrapper">
            <a href="#none">
              <i className="fa fa-envelope" aria-hidden="true"></i>
              asap@gmail.com
              <span style={{ "--i": 0 }}></span>
              <span style={{ "--i": 1 }}></span>
              <span style={{ "--i": 2 }}></span>
              <span style={{ "--i": 3 }}></span>
            </a>
          </div>
          <div className="contentWrapper">
            <a href="#none">
              <i className="fa fa-phone-square" aria-hidden="true"></i>
              010-1234-5678
              <span style={{ "--i": 0 }}></span>
              <span style={{ "--i": 1 }}></span>
              <span style={{ "--i": 2 }}></span>
              <span style={{ "--i": 3 }}></span>
            </a>
          </div>
          <div className="contentWrapper">
            <a href="https://github.com/mskim0425/asap" target="blank">
              <i className="fa fa-github" aria-hidden="true"></i>
              git hub
              <span style={{ "--i": 0 }}></span>
              <span style={{ "--i": 1 }}></span>
              <span style={{ "--i": 2 }}></span>
              <span style={{ "--i": 3 }}></span>
            </a>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Main;
