import axios from "axios";
import React from "react";
import ReactDOM from "react-dom/client";
import { RecoilRoot } from "recoil";

import App from "./App";

import "./style/style.css";

axios.defaults.baseURL = process.env.REACT_APP_SERVER_URL;

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <div className="body">
    <RecoilRoot>
      <App />
    </RecoilRoot>
  </div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
