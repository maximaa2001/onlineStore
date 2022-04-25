import { Link } from "react-router-dom";
import style from "./ProductItem.module.css"
import Const from "../../const/Const";
import React from "react"

const ProductItem = ({product}) =>{


    return(
    <Link  to={Const.MY_PRODUCTS + "/edit/" + product.id} className={style.container}>
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

    </Link>

    )

}

export default ProductItem;

