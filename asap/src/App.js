import { BrowserRouter, Route, Routes } from "react-router-dom";

import Header from "./Component/Header/Header";
import Main from "./Pages/Main";
import SignPage from "./Pages/Sign";
import Chart from "./Pages/Chart";
import Admin from "./Pages/Admin";
import StuffDetail from "./Pages/StuffDetail";
import Error from "./Component/Error/Error";
import Loading from "./Component/loading/Loading";

import SSE from "./Component/SSE";
import "./style/App.css";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" Component={Main} />
        <Route path="/sign" Component={SignPage} />
        <Route path="/error" Component={Error} />
        <Route path="/loading" Component={Loading} />
        <Route path="/admin/:id" Component={StuffDetail} />
      </Routes>
      <div className="sse_container">
        <Routes>
          <Route path="/dashboard" Component={Chart} />
          <Route path="/admin" Component={Admin} />
        </Routes>
        <SSE />
      </div>
    </BrowserRouter>
  );
}

export default App;
