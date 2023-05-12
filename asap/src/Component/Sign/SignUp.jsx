import axios from "axios";
import { useForm } from "react-hook-form";

const SignUp = () => {
  const { register, handleSubmit } = useForm();
  const signUp = async (data) => {
    try{
        const response = await axios.post(`/signin`, data)
        console.log(response.data)
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
