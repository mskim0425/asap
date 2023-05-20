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
      {loginState ? <SSE /> : null}
    </div>
  );
};

export default Layout;
