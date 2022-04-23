import React, { useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import ProductItem from "./ProductItem";
import style from "./ProductItem.module.css"

const ProductContainer = ({productList}) =>{


    return(
        <div>

        {productList && productList.products
        ? <div className={style.test_container}>
        {productList.products.map((item) => <ProductItem key={item.id} product={item}/>)}
        </div>
        : null
        }
       
  
    </div>
    )

}

export default ProductContainer;

