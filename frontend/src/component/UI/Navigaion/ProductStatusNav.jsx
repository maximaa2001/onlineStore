import React from "react";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import SendButton from "../Button/SendButton";
import Const from "../../../const/Const";
import style from '../Navigaion/Navigation.module.css'


const ProductStatusNav = () =>{



return <div>
<Nav className="justify-content-center" activeKey="/home">
<Nav.Item>
  <Nav.Link ><Link className={style.navigation_link} to={Const.ACTIVE_PRODUCTS}>Активные</Link></Nav.Link>
</Nav.Item>
<Nav.Item>
  <Nav.Link><Link className={style.navigation_link} to={Const.MODERATION_PRODUCTS}>На модерации</Link></Nav.Link>
</Nav.Item>
<Nav.Item>
  <Nav.Link><Link className={style.navigation_link} to={Const.REJECTED_PRODUCTS}>Отклоненные</Link></Nav.Link>
</Nav.Item>
<Nav.Item>
  <Nav.Link><Link className={style.navigation_link} to={Const.DELETED_PRODUCTS}>Неактивные</Link></Nav.Link>
</Nav.Item>
</Nav>
</div>

}

export default ProductStatusNav;