import "../style/stuff.css";

export default function Stuff() {
  return (
    <div className="container">
      <div className="visualization"></div>
      <div className="data">
        <div className="search">
          <input type="text" />
          <button>검색</button>
        </div>
        <div className="list">
          <div>
            <span>상품 코드</span>
            <span>상품명</span>
            <span>수량</span>
            <span>가격</span>
            <span>총 가격</span>
            <span>창고명</span>
            <span>창고 위치</span>
          </div>
          <div>
            <span>aaa</span>
            <span>물</span>
            <span>2개</span>
            <span>1000원</span>
            <span>2000원</span>
            <span>빠빠좋</span>
            <span>내마음속</span>
          </div>
        </div>
      </div>
      <div className="add-data">
        <div>
          <h4>수량코드</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>상품명</h4>
          <input type="text" id="name"></input>
        </div>
        <div>
          <h4>수량</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>가격</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>총 가격</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>창고명</h4>
          <input type="text" id="code"></input>
        </div>
        <div>
          <h4>창고 위치</h4>
          <input type="text" id="code"></input>
        </div>
        <div className="button">
          <button>추가</button>
        </div>
      </div>
      <section></section>
    </div>
  );
}
