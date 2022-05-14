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


const AboutMyProduct = (props) =>{

    const [product, setProduct] = useState( {  "imageUris": [] })
    const pathVariable = useParams();
    const navigate = useNavigate()

    const [loading, setLoading] = useState(true)
    const [auction, setAuction] = useState(false);
    const [auctionId, setAuctionId] = useState();


    const trySendRequest = useLoading(async() => {
     await ApiService.getInfoAboutMyProduct(pathVariable.id)
           .then((res) => {
            setProduct(res.data)
            if(res.data.auctionId){
                setAuction(true)
                setAuctionId(res.data.auctionId)
            }
            }).catch((err) => {
                if(err.response.status === 500 || err.response.status === 404){
            
                    navigate("/404")
                }
            }).finally(() => {
                setLoading(false)
            })
       
    })

    useEffect(() => {
        trySendRequest.loadData()
    }, [])

    const createAuction = () => {
        ApiService.createAuction(product.id)
        .then(resp => {
            console.log(resp)
            if(resp.data.auctionId){
                setAuction(true)
                setAuctionId(resp.data.auctionId)
                console.log(resp.data.auctionId)
            }
        })
    }


    useEffect(() => {
        switch(product.productStatus){
            case "WAITING_FOR_APPROVE":
                product.productStatus = "На модерации"
                break;
            case "APPROVED":
                    product.productStatus = "Активный"
                    break;
             case "NON_APPROVED":
                    product.productStatus = "Отклоненный"
                     break;
            case "DELETED":
                     product.productStatus = "Неактивный"
                     break;
        }

    }, [product])

    const editProduct = () => {
        navigate(Const.MY_PRODUCTS + "/edit/" + product.id)
    }

    

    if(loading){
        return <Loader  style={{position:"absolute", left:"48%", top:"30%"}}></Loader>
    }


    return(
        <div>
        {
            trySendRequest.isLoading 
          ? <Loader  style={{position:"absolute", left:"48%", top:"30%"}}></Loader>
          : ""
        }
        <Header/>
        <div style={{width:"100%", display: "flex"}}>
        <PhotoSlider images={product.imageUris}  clazzContainer={sliderStyle.containerAbout}  clazzMain={sliderStyle.mainAbout} clazzItem={[sliderStyle.imgAbout, "d-block", "w-100"].join(' ')}/>
      

      <div className={style.textContainer}>
      <div className={style.categoryText}>{product.category}</div>
      <div className={style.nameText}>{product.price}</div>
          <div className={style.nameText}>{product.name}</div>
          <div className={style.cityText}>Город: {product.city}</div>
          <div className={style.dateText}>Дата: {new Intl.DateTimeFormat('en-US', {year: 'numeric', month: '2-digit',day: '2-digit'}).format(product.actualDate)}</div>
          <div className={style.nameText}>Статус: {product.productStatus}</div>
          <SendButton sendDataCallback={() => editProduct()}>Редактировать</SendButton>
          {
              product.productStatus === "Активный"
              ? !auction 
              ?  <SendButton sendDataCallback={() => createAuction()}>Создать аукцион</SendButton>
            :   <Link to={"/myAuction/" + auctionId} style={{marginTop: "20px"}}>Состояние аукциона</Link>
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

export default AboutMyProduct;

