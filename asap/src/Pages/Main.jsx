import "../style/Main.css";

const Main = () => {
  return (
    <div>
      <div id="header">
        <a href="#none" className="logo">
          ASAP
        </a>
        <div className="menuToggle"></div>
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
      <section id="contact"></section>
    </div>
  );
};

export default Main;
