import React from "react"
import {useState, useContext, useEffect} from "react"
import { Link } from "react-router-dom";
import style from "./CatalogItem.module.css"
import 'font-awesome/css/font-awesome.min.css';
import {Context} from "../../index"
import Const from "../../const/Const";
import ApiService from "../../service/ApiService";
import SendButton from "../UI/Button/SendButton";
import {useParams, useNavigate  } from 'react-router-dom';



const CatalogItem = ({product}) =>{

    const [heartStyle, setHeartStyles] = useState([])

    const navigate = useNavigate();
    

     const {user} = useContext(Context)

     useEffect(() => {
         if(product.isAdded){
            setHeartStyles(["fa fa-heart", style.heart])
         } else{
            setHeartStyles(["fa fa-heart-o", style.heart])
         }
     },[])

     const approveProduct = (productId, statusId) => {
         ApiService.approveProduct(productId, statusId)
         .then((resp) => {
            window.location.reload();
         })

     }




    const changeBasket = () => {
        ApiService.changeBasket(product.id)
        .then((resp) => {
            const isAdded = resp.data.isAdded;
            if(isAdded){
                setHeartStyles(["fa fa-heart", style.heart])
            }
            else{
                setHeartStyles(["fa fa-heart-o", style.heart])
            }
        })
    }


    return(
    <div  className={style.container} style={{ textDecoration: 'none' }}>
    <img className={style.img}
     src={product.imageUri}
     alt={"Нет фото"}>
  
    </img>
    <div className={style.text_container}>
    <Link to={Const.PRODUCT + `/${product.id}`} className={style.name}>
    {product.name}
    </Link>
    <div className={style.price}>
    {
        product.price
        ? product.price
        : "Договорная"

    }
    </div>

    <div style={{marginTop:"5px", display:"flex", flexDirection:"column", fontSize:"14px"}}>
    <div className={style.category}>
    {product.category}
    </div>
  

    <div className={style.city}>
    {product.city}
    </div>
    </div>
    {/* fa fa-heart */}

    </div>
    {
        user.role === Const.USER_ROLE
        ? <div  style={{width:"100%", display: "flex", justifyContent: "end"}}><i  style={{fontSize: "30px"}}
            onClick={changeBasket}
            className={heartStyle.join(' ')}></i></div>
        : 
        user.role === Const.ADMIN_ROLE
        ?<div> <SendButton style={{position: "absolute", left: "70%", width: "150px"}} sendDataCallback={e => approveProduct(product.id, 2)}>Подтвердить</SendButton>
        <SendButton style={{position: "absolute", left: "90%", backgroundColor: "red",  width: "150px"}} sendDataCallback={e => approveProduct(product.id, 3)}>Отменить</SendButton></div>
        : null
    }
    
  

    </div>

    )

}

export default CatalogItem;