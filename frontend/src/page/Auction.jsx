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
import Input from "../component/UI/Input/Input";


const Auction = (props) =>{

    const pathVariable = useParams();
    const navigate = useNavigate ()

    const [auction, setAuction] = useState({
        "email": null
    })

    const [price, setPrice] = useState([])
    const [error, setError] = useState(false)

    useEffect(() => {
        ApiService.getAuction(pathVariable.id)
        .then(resp => {
            console.log(resp.data)
            setAuction(resp.data)
        }).catch(err => {
            navigate("/404")
        })
    }, [])

    const send = () => {
        ApiService.sendToAuction(pathVariable.id, price)
        .then(resp => {
            if(!resp.data.isSuccess){
                setError(true)
            } else{
                setError(false)
            }
        }).catch(err => {
            setError(true)
        })
    }


    return(
        <div>
        <Header/>
        <div style={{display: "flex", flexDirection:"column"}}>
        <div style={{padding: "10px", display:"flex"}}>
       <Link to={"/user/" + auction.userId}>{auction.email}</Link> <div style={{marginLeft: "20px"}}> предложил</div> <div style={{marginLeft: "20px"}}>
       {auction.currentPrice}</div> <div style={{marginLeft: "20px"}}>за следующий</div> 
       <Link  to={"/product/" + auction.productId} style={{marginLeft: "20px"}}>товар</Link>
        </div>
        <div style={{display: "flex"}}>
        <div>Аукцион заканчивается </div> <div style={{marginLeft: "20px"}}>
        {new Intl.DateTimeFormat('en-US', {year: 'numeric', month: '2-digit',day: '2-digit', 'hour': '2-digit', 'minute': '2-digit'}).format(auction.endDate)}
        </div>
        </div>
        <Input style={{width: "200px"}} callback={setPrice}/>
        <SendButton sendDataCallback={send}>Участвовать</SendButton>
        {
            error
            ? <div>Введите корректное значение</div>
            : null
        }
        </div>
    </div>
    )
  

}

export default Auction;

