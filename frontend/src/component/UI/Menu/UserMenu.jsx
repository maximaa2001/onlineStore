import React from "react";
import { NavDropdown } from "react-bootstrap";
import style from "./UserMenu.module.css"
import { Link } from "react-router-dom";
import Const from "../../../const/Const"
import ApiService from "../../../service/ApiService";
import { useParams, useNavigate } from "react-router-dom"



const UserMenu = () =>{
  const navigate = useNavigate ()

  const logout = () => {
    ApiService.logout()
    .then((resp) => {
      if(resp.data.isSuccess){
        localStorage.removeItem(Const.TOKEN)
        window.location.reload();
      }
    })
  }
   
return (
    <NavDropdown title="Меню" id="nav-dropdown" className={style.userMenu}>
        <NavDropdown.Item eventKey="4.1"><Link to={Const.MY_PRODUCTS} style={{textDecoration:"none"}}>Мои объявления</Link></NavDropdown.Item>
        {/* <NavDropdown.Item eventKey="4.2" >Мои сообщения</NavDropdown.Item> */}
        <NavDropdown.Item eventKey="4.3"><Link to={Const.FAVOURITE_PRODUCTS} style={{textDecoration: "none"}}>Избранное</Link></NavDropdown.Item>
        <NavDropdown.Item eventKey="4.3"><Link to={Const.EDIT_PROFILE} style={{textDecoration: "none"}}>Настройки</Link></NavDropdown.Item>
        <NavDropdown.Divider />
        <NavDropdown.Item eventKey="4.4" onClick={logout}>Выход</NavDropdown.Item>
      </NavDropdown>
)
}

export default UserMenu;