import { BrowserRouter, Route, Routes } from "react-router-dom";

import Dashboard from "./Pages/Dashboard";
import Main from "./Pages/Main";
import Admin from "./Pages/Admin";
import Header from "./Component/Header/Header";
import Error from "./Component/Error/Error";
import Loading from "./Component/loading/Loading";
import SSE from "./Component/SSE";

import "./style/App.css";

function App() {
  return (
    <BrowserRouter>
      <Header />
      {/* <div id="globalWrapper"> */}
      <Routes>
        <Route path="/" Component={Main} />
        <Route path="/dashboard" Component={Dashboard} />
        <Route path="/admin" Component={Admin} />
        <Route path="/error" Component={Error} />
        <Route path="/loading" Component={Loading} />
      </Routes>
      {/* </div> */}
      <SSE />
    </BrowserRouter>
  );
}

export default App;
