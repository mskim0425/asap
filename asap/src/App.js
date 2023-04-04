import { BrowserRouter, Route, Routes } from "react-router-dom";

import Dashboard from "./Pages/Dashboard";
import Stuff from "./Pages/Stuff";

import Sidebar from "./Component/Sidebar";
import Error from "./Component/Error/Error";
import Loading from "./Component/loading/Loading";

function App() {
  return (
    <BrowserRouter>
      <Sidebar />
      <Routes>
        <Route path="/" Component={Dashboard} />
        <Route path="/stuff" Component={Stuff} />
        <Route path="/error" Component={Error} />
        <Route path="/loading" Component={Loading} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
