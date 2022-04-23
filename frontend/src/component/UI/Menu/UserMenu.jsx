import React from "react";
import { NavDropdown } from "react-bootstrap";
import style from "./UserMenu.module.css"
import { Link } from "react-router-dom";
import Const from "../../../const/Const"


const UserMenu = () =>{
   
return (
    <NavDropdown title="Меню" id="nav-dropdown" className={style.userMenu}>
        <NavDropdown.Item eventKey="4.1"><Link to={Const.MY_PRODUCTS}>Мои объявления</Link></NavDropdown.Item>
        <NavDropdown.Item eventKey="4.2">Мои сообщения</NavDropdown.Item>
        <NavDropdown.Item eventKey="4.3">Избранное</NavDropdown.Item>
        <NavDropdown.Item eventKey="4.3">Настройки</NavDropdown.Item>
        <NavDropdown.Divider />
        <NavDropdown.Item eventKey="4.4">Выход</NavDropdown.Item>
      </NavDropdown>
)
}

export default UserMenu;