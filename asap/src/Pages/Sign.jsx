import { useState } from "react";

import "../style/Sign.css";

export const SignPage = () => {
    const [panelState, setPanelState] = useState(false)

  return (
    <div className="signsection">
      <div className={`container ${panelState ? "" : "right-panel-active"}`}>
        <div className="container__form container--signup">
          <form action="#" className="form" id="form1">
            <h2 className="form__title">Sign Up</h2>
            <input type="text" placeholder="Ni" className="input" />
            <input type="email" placeholder="Email" className="input" />
            <input type="password" placeholder="Password" className="input" />
            <button className="btn">Sign Up</button>
          </form>
        </div>

        <div className="container__form container--signin">
          <form action="#" className="form" id="form2">
            <h2 className="form__title">Sign In</h2>
            <input type="email" placeholder="Email" className="input" />
            <input type="password" placeholder="Password" className="input" />
            <button className="btn">Sign In</button>
          </form>
        </div>

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
