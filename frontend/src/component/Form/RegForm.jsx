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
import { Formik } from "formik";
import * as Yup from 'yup';

const RegForm = () =>{

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [phoneNumber, seetPhoneNumber] = useState('')


    const [phoneError, setPhoneError] = useState(false)
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

    
const validationSchema=Yup.object().shape({
    email: Yup.string().email('Введите корректный email').required('Обязательно'),
    password: Yup.string().min(9, "Не менее 9 символов").required('Обязательно'),
    repeatPassword: Yup.string().oneOf([Yup.ref('password')], "Пароли не совпадают").required('Обязательно'),
  })
  
return (
 <div>

 

<Formik initialValues={{
        email: '',
        password: '',
        repeatPassword: '',
        phoneNumber: '',
      }}
    
      onSubmit={async values => {
        setEmail(values.email)
        setPassword(values.password)
        setRepeatPassword(values.repeatPassword)

        console.log(phoneNumber.length)

        if(phoneNumber.length != 12){
          console.log(phoneNumber)
          setPhoneError(true)
        } else{
          tryRegistration.loadData()
        }


      }}
      validationSchema={validationSchema}>

      {({values, errors, touched, handleChange, handleBlur, isValid, handleSubmit, dirty}) => (
        <div>
      <form autoComplete="off">
<blockquote className={[style.label, "blockquote"].join(' ')}>

Регистрация
</blockquote>

<Input placeholder="email" callback = {setEmail} type="email" onChange={handleChange} onBlur={handleBlur} name="email"/>
{errors.email && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.email}</div> }


<Input placeholder="password" callback = {setPassword} type="password" onChange={handleChange} onBlur={handleBlur} name="password"/>
{errors.password && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.password}</div> }


<Input placeholder="repeat password" callback = {setRepeatPassword} type="password" onChange={handleChange} onBlur={handleBlur} name="repeatPassword"/>
{errors.repeatPassword && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.repeatPassword}</div> }


 <PhoneInput
    country={"by"}
    countryCodeEditable={false}
    specialLabel={""}
    onChange={e => seetPhoneNumber(e)}
/>
{phoneError 
? <div style={{color:"red",marginTop:"-5px", fontSize:"12px", marginBottom: "5px"}}>Введите номер телефона</div>
: null
}


<SendButton sendDataCallback={handleSubmit} type="submit">Зарегистрироваться</SendButton>
      </form>
      {
          tryRegistration.isLoading 
          ? <Loader  style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
          : ""
      }
      {
    successAlert.show
    ? <SuccessAlert style={{marginTop:"20px"}}>Проверьте почту</SuccessAlert>
    : ""
        }
        {
    tryRegistration.error 
    ? <ErrorAlert style={{marginTop:"20px"}}>Ошибка при обращении к серверу</ErrorAlert>
    : ""
        }


      </div>
      )}
    
    
</Formik>

    
      </div>

)
}

export default RegForm;