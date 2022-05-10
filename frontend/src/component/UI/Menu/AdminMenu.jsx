import React from "react";
import { NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import Const from "../../../const/Const"
import ApiService from "../../../service/ApiService";
import { useParams, useNavigate } from "react-router-dom"
import style from "./AdminMenu.module.css"



const AdminMenu = () =>{
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
    <NavDropdown title="Меню" id="nav-dropdown" className={style.adminMenu}>
        <NavDropdown.Item eventKey="4.1"><Link to={Const.ADMIN_WAITING} style={{textDecoration:"none"}}>Объявления</Link></NavDropdown.Item>
        <NavDropdown.Item eventKey="4.3"><Link to="/" style={{textDecoration: "none"}}>Все пользователи</Link></NavDropdown.Item>
        <NavDropdown.Divider />
        <NavDropdown.Item eventKey="4.4" onClick={logout}>Выход</NavDropdown.Item>
      </NavDropdown>
)
}

export default AdminMenu;