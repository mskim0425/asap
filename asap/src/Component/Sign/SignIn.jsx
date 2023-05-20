import axios from "axios";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import { useSetRecoilState } from "recoil";
import { isLogin } from "../../state/atoms";

const SignIn = () => {
  const setLoginState = useSetRecoilState(isLogin)
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate()
  const signIn = async (data) => {
    try{
        await axios.post(`/login`, data)
        setLoginState(true)
        navigate('/admin')
    }
    catch(err){console.log(err)}
  }

  return (
    <div className="container__form container--signin">
      <form onSubmit={handleSubmit((data)=>signIn(data))} className="form" id="form2">
        <h2 className="form__title">Sign In</h2>
        <input {...register("email")} type="email" placeholder="Email" className="input" />
        <input {...register("password")} type="password" placeholder="Password" className="input" />
        <button className="btn">Sign In</button>
      </form>
    </div>
  );
};

export default SignIn;
