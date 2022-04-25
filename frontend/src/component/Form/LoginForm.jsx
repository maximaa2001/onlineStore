import React from "react"
import { useContext, useState } from "react"
import Input from "../UI/Input/Input";
import ApiService from "../../service/ApiService";
import useLoading from "../../hook/useLoading";
import SendButton from "../UI/Button/SendButton";
import GoogleLogin from 'react-google-login';
import Loader from "../UI/Loader/Loader";
import Const from "../../const/Const";
import style from './RegForm.module.css'
import jwt_decode from "jwt-decode";
import {Context} from "../../index";

const LoginForm = ({setVisible}) =>{

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const {user} = useContext(Context)


    const tryLogin = useLoading(async () => {
        await ApiService.login(email, password)
         .then(resp =>{
             const result = resp.data.jwt;
             if(result){
                var decoded = jwt_decode(result);

                localStorage.setItem(Const.TOKEN, result)
                user.setRole(decoded.role)
             }
         })

         setVisible(false)
    });

    const responseGoogle = (response) => {
        const accessToken = response.accessToken;
        ApiService.googleLogin(accessToken)
        .then(resp =>{
            const result = resp.data.jwt;
            if(result){
                localStorage.setItem(Const.TOKEN, result)

            }
        }
        )
    }

    return(<div>
     <form autoComplete="off">
     <blockquote className={[style.label, "font-weight-lighter"].join(' ')}>


Авторизация
</blockquote>
        <Input placeholder="email" callback = {setEmail} type="email"/>
        <Input placeholder="password" callback = {setPassword} type="password"/>
        <SendButton sendDataCallback={() => tryLogin.loadData()}>Авторизироваться</SendButton>
        </form>
        <GoogleLogin 
    clientId="961016962748-g0afc56duujq91fll1nlt83beed09cu0.apps.googleusercontent.com"
    buttonText="Login"
    onSuccess={responseGoogle}
    onFailure={responseGoogle}
    cookiePolicy={'single_host_origin'}
  />,
   {
    tryLogin.isLoading 
          ? <Loader  style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
          : ""
      }
        </div>
    )

}

export default LoginForm;