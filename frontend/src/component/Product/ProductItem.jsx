import React, { useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import style from "./ProductItem.module.css"

const ProductItem = ({product}) =>{


    return(
    <div className={style.container}>
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

    </div>

    )

}

export default ProductItem;

