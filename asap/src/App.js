import { BrowserRouter, Route, Routes } from "react-router-dom";

import Dashboard from "./Pages/Dashboard";
import Stuff from "./Pages/Stuff";

import Sidebar from "./component/Sidebar";

function App() {
  return (
    <BrowserRouter>
      <Sidebar />
      <Routes>
        <Route path="/" Component={Dashboard} />
        <Route path="/stuff" Component={Stuff} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
