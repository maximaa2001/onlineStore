import React, { useEffect } from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import style from "./FavouriteItem.module.css"
import Const from "../../const/Const";
import 'font-awesome/css/font-awesome.min.css';
import ApiService from "../../service/ApiService";
import Loader from "../UI/Loader/Loader";

const FavouriteItem = ({product}) =>{

    const [heartStyle, setHeartStyle] = useState(["fa fa-heart", style.heart])



    const deleteFromFavourite = () => {
        ApiService.changeBasket(product.id)
        .then(resp => {
            if(resp.data.isAdded === false){
                setHeartStyle(["fa fa-heart-o", style.heart])
            } else{
                setHeartStyle(["fa fa-heart", style.heart])
            }
        })
    }
 


    return(
    <Link  to="" className={style.container} style={{ textDecoration: 'none' }}>
    <img className={style.img}
     src={product.imageUri}>
  
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


    </div>
    <div  style={{width:"100%", display: "flex", justifyContent: "end"}}><i  style={{fontSize: "30px"}}
    onClick={deleteFromFavourite}
            className={heartStyle.join(' ')}></i></div>

    </Link>

    )

}

export default FavouriteItem;

