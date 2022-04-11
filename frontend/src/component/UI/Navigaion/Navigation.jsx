import React from "react";
import { ListGroup } from "react-bootstrap";
import style from '../Navigaion/Navigation.module.css'
import { Link } from "react-router-dom";

const Navigation = ({listItems}) =>{
   

return <ListGroup className={style.navigation_group}>
{listItems.map((item) => <ListGroup.Item key={item.category_id} className={style.navigation_item}>
<Link to="/" className={style.navigation_link}> {item.category_name}</Link></ListGroup.Item>)}
</ListGroup>
}

export default Navigation;