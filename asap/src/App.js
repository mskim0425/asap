import { BrowserRouter, Route, Routes } from "react-router-dom";

import Header from "./Component/Header/Header";
import Main from "./Pages/Main";
import Chart from "./Pages/Chart";
import Admin from "./Pages/Admin";
import StuffDetail from "./Pages/StuffDetail";
import Error from "./Component/Error/Error";
import Loading from "./Component/loading/Loading";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" Component={Main} />
        <Route path="/dashboard" Component={Chart} />
        <Route path="/admin" Component={Admin} />
        <Route path="/error" Component={Error} />
        <Route path="/loading" Component={Loading} />
        <Route path="/admin/:id" Component={StuffDetail} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
