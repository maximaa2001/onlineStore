import React, { useEffect, useState } from "react";
import Header from "../component/Header";
import { ListGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import style from "../style/EditProfile.module.css"
import ApiService from "../service/ApiService";
import Input from "../component/UI/Input/Input";
import SendButton from "../component/UI/Button/SendButton";
import PhoneInput from "react-phone-input-2";



const EditProfile = (props) =>{
    const [oldNumber, setOldNumber] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')

    const [phoneMenu, setPhoneMenu] = useState(false)
    const [passwordMenu, setPasswordMenu] = useState(false)

    const [phoneError, setPhoneError] = useState(false)
    const [phoneSuccess, setPhoneSuccess] = useState(false)

    const showMenu = (number) => {
        switch(number){
            case 1:
                setPasswordMenu(false);
                setPhoneMenu(true)
                break;
            case 2:
                setPhoneMenu(false);
                setPasswordMenu(true)
                break;
        }
    }

    const changeNumber = () => {
        if(phoneNumber.length != 12 || phoneNumber === oldNumber){
            setPhoneSuccess(false)
            setPhoneError(true)
        } else{
            ApiService.changePhone(phoneNumber)
            .then(resp => {
                if(resp.data.phoneNumber === phoneNumber){
                    setPhoneError(false)
                    setPhoneSuccess(true)
                }
            })
        }
         

        
    }

    useEffect(() => {
        if(phoneMenu){
            ApiService.getPhone()
            .then(resp => {
                console.log(resp.data)
                console.log(resp.data.phoneNumber)
                setPhoneNumber(resp.data.phoneNumber)
                setOldNumber(resp.data.phoneNumber)
            })
        }
    }, [phoneMenu])


   return(
//style={{boxSizee: "borderBox"}}
    <div className={style.window}>
     <Header/>
      <div style={{display : "flex"}}>
    <ListGroup className={style.mainContainer}>
        <a onClick={e => showMenu(1)}  className={style.link}> <ListGroup.Item className={style.link}>Сменить номер телефона</ListGroup.Item></a>
        <a onClick={e => showMenu(2)}> <ListGroup.Item className={style.link}>Сменить пароль</ListGroup.Item></a>
    </ListGroup>

    <div style={{width: "80%"}}>
       {
           phoneMenu
           ? <div>
            <div>
 <PhoneInput style={{width: "400px", marginLeft: "50%", transform: "translate(-50%, 0)", marginTop: "20px"}} value={phoneNumber}
    country={"by"}
    countryCodeEditable={false}
    specialLabel={""}
    onChange={e => setPhoneNumber(e)}/>
    </div>
    {phoneError
    ? <div style={{width: "400px", marginLeft: "50%", transform: "translate(-50%, 0)", color: "red"}}>Введите корректный номер телефона</div>
    : null}
    {phoneSuccess
    ? <div style={{width: "400px", marginLeft: "50%", transform: "translate(-50%, 0)", color: "green"}}>Телефон успешно изменен</div>
    : null}
           <div><SendButton  sendDataCallback={changeNumber}>Изменить номер телефона</SendButton></div>
           </div>
           :
            passwordMenu
            ? <div>Пароль</div>
            : null
       }
   </div>
   </div>
   
   </div>
   )

}

export default EditProfile;

