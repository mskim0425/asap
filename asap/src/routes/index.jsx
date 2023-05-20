import { Outlet } from "react-router";
import { useRecoilValue } from "recoil";
import SSE from "../Component/SSE";
import { isLogin } from "../state/atoms";
import "../style/App.css";

const Layout = () => {
    const loginState = useRecoilValue(isLogin)

  return (
    <div className="sse_container">
      <Outlet />
      {loginState ? <SSE /> : 
      <div className="sse">
        <div className={"alert"}>
          <span className="alertText">SSE 기능을 확인하시려면 회원가입 후 로그인 해주세요 !</span>
        </div>
      </div>}
    </div>
  );
};

export default Layout;
