import "../style/Main.css";

const Main = () => {
  const lists = document.querySelectorAll(".nav li");
  function active() {
    lists.forEach((list) => list.classList.remove("active"));
    this.classList.add("active");
  }

  lists.forEach((list) => list.addEventListener("click", active));

  function menuToggle() {
    const header = document.querySelector("#header");
    header.classList.toggle("active");
  }

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
      <section id="stuff"></section>
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
          <a href="#none">
            <i class="fa fa-envelope" aria-hidden="true"></i>
            asap@gmail.com
          </a>
          <a href="#none">
            <i className="fa fa-phone-square" aria-hidden="true"></i>
            010-1234-5678
          </a>
          <a href="#none">
            <i className="fa fa-github" aria-hidden="true"></i>
            <a href="https://github.com/mskim0425/asap" target="blank">
              git hub
            </a>
          </a>
        </div>
      </section>
    </div>
  );
};

export default Main;
