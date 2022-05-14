import React, {useEffect, useState,  } from "react";
import {useParams, useNavigate, Link  } from 'react-router-dom';
import PhotoSlider from "../component/Slider/PhotoSlider";
import useLoading from "../hook/useLoading";
import ApiService from "../service/ApiService";
import sliderStyle from "../component/Slider/PhotoSlider.module.css"
import Header from "../component/Header";
import style from "../style/AboutMyProductPage.module.css"
import SendButton from "../component/UI/Button/SendButton";
import Const from "../const/Const";
import Loader from "../component/UI/Loader/Loader";


const AboutCatalogProduct = () =>{

    const [product, setProduct] = useState( {  "imageUris": [] })
    const pathVariable = useParams();
    const navigate = useNavigate ()

    const [loading, setLoading] = useState(true)

    useEffect(() => {
         ApiService.getInfoAboutCatalogProduct(pathVariable.id)
           .then((res) => {
            setProduct(res.data)
            console.log(res.data)
            }).catch((err) => {
                if(err.response.status === 500 || err.response.status === 404 || err.response.status === 400){
            
                    navigate("/404")
                }
            }).finally(() => {
                setLoading(false)
            })
    }, [])


    return(
        <div>
        <Header/>
        <div style={{width:"100%", display: "flex"}}>
        <PhotoSlider images={product.imageUris}  clazzContainer={sliderStyle.containerAbout}  clazzMain={sliderStyle.mainAbout} clazzItem={[sliderStyle.imgAbout, "d-block", "w-100"].join(' ')}/>
      

      <div className={style.textContainer}>
      <div className={style.categoryText}>{product.category}</div>
      <div className={style.nameText}>{product.price}</div>
          <div className={style.nameText}>{product.name}</div>
          <div className={style.cityText}>Город: {product.city}</div>
          <div className={style.dateText}>Дата: {new Intl.DateTimeFormat('en-US', {year: 'numeric', month: '2-digit',day: '2-digit'}).format(product.actualDate)}</div>
          <Link to={Const.USER + "/" + product.userId} className={style.cityText}>Посмотреть профиль продавца: {product.email}</Link>
          {
            !product.isMyProduct
            ? 
          <Link to={Const.AUCTION + "/" + product.auctionId} className={style.cityText}>Участвовать в аукционе</Link>
          : null
          }
      </div>
        </div>
        <div>
        <div style={{textAlign:"center", fontSize:"40px"}}>Описание</div>
       <div style={{paddingLeft:"10px", fontSize:"30px"}}>{product.description}</div>
     
            
        </div>
  
    </div>
    )

   

}

export default AboutCatalogProduct;

