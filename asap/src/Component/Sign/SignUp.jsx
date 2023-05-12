import axios from "axios";
import { useForm } from "react-hook-form";

const SignUp = ({setPanelState}) => {
  const { register, handleSubmit } = useForm();
  const signUp = async (data) => {
    try{
        const response = await axios.post(`/signin`, data)
        window.alert(`${response.data}\n메일함을 확인하시고 이메일 인증을 해주세요\n인증 후 ASAP의 모든 기능을 사용하실 수 있습니다.`)
        setPanelState(true)
    }
    catch(err){console.log(err)}
  }

  return (
    <div className="container__form container--signup">
      <form onSubmit={handleSubmit((data) => signUp(data))} className="form" id="form1">
        <h2 className="form__title">Sign Up</h2>
        <input {...register("email")} type="email" placeholder="Email" className="input" />
        <input {...register("password")} type="password" placeholder="Password" className="input" />
        <input {...register("nickname")} type="text" placeholder="Nickname" className="input" />
        <button className="btn">
          Sign Up
        </button>
      </form>
    </div>
  );
};

export default SignUp;
