import style from "./ProductItem.module.css"
import React from "react"
import FavouriteItem from "./FavouriteItem";

const FavoiriteContainer = ({productList}) =>{




    return(
        <div>

        {productList && productList.products
        ? <div className={style.test_container}>
        {productList.products.map((item) =><FavouriteItem key={item.id} product={item}/>)}
        </div>
        : null
        }
       
  
    </div>
    )

}

export default FavoiriteContainer;

