import "../style/Main.css";
import anime from "animejs/lib/anime.es.js";
import { useEffect } from "react";

const Main = () => {
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
            ASAP는 재고 공급 및 수요 활동을 처리하여 판매 채널 전체에서
            실시간으로 정확한 글로벌 가시성을 제공하는 클라우드 서비스
            솔루션입니다. 고객의 구매 경험을 향상하고 재고 부족 및 과다 판매를
            방지하도록 비즈니스 운영을 최적화할 수 있습니다.
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
