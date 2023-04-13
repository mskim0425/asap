import "./Error.css";
import { useNavigate } from "react-router-dom";

export default function Error() {
  const navigate = useNavigate();
  function moveToMain() {
    navigate("/");
    localStorage.setItem("tabId", 1);
  }

  return (
    <div className="wrapper">
      <div>
        <div className="heading">PAGE NOT FOUND</div>
        <p>
          존재하지않은 주소를 입력하셨거나,
          <pre>요청하신 페이지의 주소가 변경, 삭제되어 찾을 수 없습니다.</pre>
        </p>
        <div>
          <span onClick={moveToMain}>메인으로 가기</span>
        </div>
      </div>
    </div>
  );
}
