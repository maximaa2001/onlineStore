import React from "react"
import {useState, useContext, useEffect} from "react"
import { Link } from "react-router-dom";
import style from "./CatalogItem.module.css"
import 'font-awesome/css/font-awesome.min.css';
import {Context} from "../../index"
import Const from "../../const/Const";
import ApiService from "../../service/ApiService";



const CatalogItem = ({product}) =>{

    

    const [heartStyle, setHeartStyles] = useState([])

     const {user} = useContext(Context)

     useEffect(() => {
         if(product.isAdded){
            setHeartStyles(["fa fa-heart", style.heart])
         } else{
            setHeartStyles(["fa fa-heart-o", style.heart])
         }
     },[])




    const changeBasket = (event) => {
        ApiService.changeBasket(product.id)
        .then((resp) => {
            console.log(resp.data.isAdded)
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
    <Link  to={Const.PRODUCT + `/${product.id}`} className={style.container} style={{ textDecoration: 'none' }}>
    <img className={style.img}
     src={product.imageUri}
     alt={"Нет фото"}>
  
    </img>
    <div className={style.text_container}>
    <div className={style.name}>
    {product.name}
    </div>
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
            onClick={e => changeBasket(e)}
            className={heartStyle.join(' ')}></i></div>
        : null
    }
    
  

    </Link>

    )

}

export default CatalogItem;

