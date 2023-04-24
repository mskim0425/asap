import { BrowserRouter, Route, Routes } from "react-router-dom";

import Dashboard from "./Pages/Dashboard";
import Admin from "./Pages/Admin";
import Sidebar from "./Component/Sidebar/Sidebar";
import Error from "./Component/Error/Error";
import Loading from "./Component/loading/Loading";
import SSE from "./Component/SSE";

import "./style/App.css";

function App() {
  return (
    <BrowserRouter>
      <Sidebar />
      <div id="globalWrapper">
        <Routes>
          <Route path="/" Component={Dashboard} />
          <Route path="/admin" Component={Admin} />
          <Route path="/error" Component={Error} />
          <Route path="/loading" Component={Loading} />
        </Routes>
      </div>
      <SSE />
    </BrowserRouter>
  );
}

export default App;
