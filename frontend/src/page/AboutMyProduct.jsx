import React, {useEffect, useState,  } from "react";
import {useParams, useNavigate  } from 'react-router-dom';
import PhotoSlider from "../component/Slider/PhotoSlider";
import useLoading from "../hook/useLoading";
import ApiService from "../service/ApiService";
import sliderStyle from "../component/Slider/PhotoSlider.module.css"
import Header from "../component/Header";
import style from "../style/AboutMyProductPage.module.css"


const AboutMyProduct = (props) =>{

    const [product, setProduct] = useState( {  "imageUris": [] })
    const pathVariable = useParams();
    const navigate = useNavigate ()

    const trySendRequest = useLoading(async() => {
     await ApiService.getInfoAboutMyProduct(pathVariable.id)
           .then((res) => {
            setProduct(res.data)
            }).catch((err) => {
                if(err.response.status === 500 || err.response.status === 404){
                    console.log(err.response.status)
                    navigate("/404")
                }
            })
       
       
       
    })

    useEffect(() => {
        trySendRequest.loadData()
    }, [])

    useEffect(() => {
        console.log(product)
    }, [product])


    return(
        <div>
        <Header/>
        <div style={{width:"100%", display: "flex"}}>
        <PhotoSlider images={product.imageUris}  clazzContainer={sliderStyle.containerAbout}  clazzMain={sliderStyle.mainAbout} clazzItem={[sliderStyle.imgAbout, "d-block", "w-100"].join(' ')}/>
      

      <div className={style.textContainer}>
      <div className={style.categoryText}>Категория</div>
      <div className={style.nameText}>Цена</div>
          <div className={style.nameText}>Название</div>
          <div className={style.cityText}>Город</div>
          <div className={style.dateText}>Дата</div>
          <div className={style.nameText}>Статус товара</div>
          <button>gjmft</button>
      </div>
        </div>
        <div>
        <div style={{textAlign:"center", fontSize:"40px"}}>Описание</div>
       <div style={{paddingLeft:"10px", fontSize:"30px"}}>Описание</div>
            
        </div>
  
    </div>
    )

}

export default AboutMyProduct;

