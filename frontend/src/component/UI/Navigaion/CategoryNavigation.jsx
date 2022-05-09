import React from "react";
import { ListGroup } from "react-bootstrap";
import style from '../Navigaion/Navigation.module.css'
import { Link } from "react-router-dom";
import { useEffect, useState } from "react"
import ApiService from "../../../service/ApiService";

const Navigation = ({setCurrentCategory}) =>{

    const [categories, setCategories] = useState([])

    useEffect( () => {
         ApiService.getCategories()
         .then(resp =>{
           setCategories(resp.data)
         })

    }, [])
   

return <ListGroup className={style.navigation_group}>
{categories.map((item) => <ListGroup.Item key={item.category_id} className={style.navigation_item}>
<Link to="/" onClick={ e => setCurrentCategory(item.category_name)} className={style.navigation_link}> {item.category_name}</Link></ListGroup.Item>)}
</ListGroup>
}

export default Navigation;