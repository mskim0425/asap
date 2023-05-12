import { useState } from "react";

import SignIn from "../Component/Sign/SignIn";
import SignUp from "../Component/Sign/SignUp";

import "../style/Sign.css";

const SignPage = () => {
    const [panelState, setPanelState] = useState(false)

  return (
    <div className="signsection">
      <div className={panelState ? "container" : "container right-panel-active"}>
        <SignUp setPanelState={setPanelState} />
        <SignIn />

        <div className="container__overlay">
          <div className="overlay">
            <div className="overlay__panel overlay--left">
              <button className="btn" id="signIn" onClick={()=>setPanelState(!panelState)}>
                Sign In
              </button>
            </div>
            <div className="overlay__panel overlay--right">
              <button className="btn" id="signUp" onClick={()=>setPanelState(!panelState)}>
                Sign Up
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SignPage;