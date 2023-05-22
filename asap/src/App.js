import { BrowserRouter, Route, Routes } from "react-router-dom";

import Header from "./Component/Header/Header";
import Main from "./Pages/Main";
import SignPage from "./Pages/Sign";
import Chart from "./Pages/Chart";
import Admin from "./Pages/Admin";
import StuffDetail from "./Pages/StuffDetail";
import Error from "./Component/Error/Error";
import Loading from "./Component/loading/Loading";

import "./style/App.css";
import Layout from "./routes";

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
        
        <Route path="/" element={<Layout />}>
          <Route path="dashboard" element={<Chart />} />
          <Route path="admin" element={<Admin />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
