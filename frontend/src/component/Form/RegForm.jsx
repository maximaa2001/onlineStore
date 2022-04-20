import React,{ useState} from "react";
import style from './RegForm.module.css'
import Input from "../UI/Input/Input";
import SendButton from "../UI/Button/SendButton";
import PhoneInput from "react-phone-input-2";
import SuccessAlert from "../UI/Alert/SuccessAlert";
import ErrorAlert from "../UI/Alert/ErrorAlert";
import ApiService from "../../service/ApiService";
import useLoading from "../../hook/useLoading";
import Loader from "../UI/Loader/Loader";
import useAlert from "../../hook/useAlert";

const RegForm = () =>{

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [phoneNumber, seetPhoneNumber] = useState('')

    const successAlert = useAlert(10000);

    const tryRegistration = useLoading(async () => {
        await ApiService.registration(email, password, repeatPassword, phoneNumber)
         .then(resp =>{
             const result = resp.data.isSuccess;
             if(result){
                successAlert.setShow(true)
             }
         })
    });


  
return (
 <div>
 
    <form autoComplete="off">
<blockquote className={[style.label, "blockquote"].join(' ')}>


Регистрация
</blockquote>
      <Input placeholder="email" callback = {setEmail} type="email"/>
      <Input placeholder="password" callback = {setPassword} type="password"/>
      <Input placeholder="repeat password" callback = {setRepeatPassword} type="password"/>

      <PhoneInput
      country={"by"}
      countryCodeEditable={false}
      specialLabel={""}
      onChange={e => seetPhoneNumber(e)}
      />
      <SendButton sendDataCallback={() => tryRegistration.loadData()}>Зарегистрироваться</SendButton>
    
      </form>
      {
          tryRegistration.isLoading 
          ? <Loader  style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
          : ""
      }
      {
    successAlert.show
    ? <SuccessAlert style={{marginTop:"20px"}}>Ошибка при обращении к серверу</SuccessAlert>
    : ""
        }
        {
    tryRegistration.error 
    ? <ErrorAlert style={{marginTop:"20px"}}>Ошибка при обращении к серверу</ErrorAlert>
    : ""
        }
      </div>

)
}

export default RegForm;