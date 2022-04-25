import React from "react"
import {useState} from "react"
import { Link } from "react-router-dom";
import style from "./CatalogItem.module.css"
import 'font-awesome/css/font-awesome.min.css';



const CatalogItem = ({product}) =>{

    const [heartStyle, setHeartStyles] = useState(["fa fa-heart-o", style.heart])

    const overHeart = () => {
     setHeartStyles(["fa fa-heart", style.heart])
    }

    const leaveHeart = () => {
        setHeartStyles(["fa fa-heart-o", style.heart])
    }


    return(
    <Link  to={""} className={style.container} style={{ textDecoration: 'none' }}>
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
    <div  style={{width:"100%", display: "flex", justifyContent: "end"}}><i  style={{fontSize: "30px"}}
      onMouseOver={() => overHeart()} onMouseLeave={(() =>  leaveHeart())}
    className={heartStyle.join(' ')}></i></div>;
  

    </Link>

    )

}

export default CatalogItem;

