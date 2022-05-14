import React from "react"
import { useState } from "react"
import Header from "../component/Header"
import {Context} from "../index"
import { useContext, useEffect } from "react"
import ProductStatusNav from "../component/UI/Navigaion/ProductStatusNav"
import { Link, useParams, useNavigate } from "react-router-dom";
import SendButton from "../component/UI/Button/SendButton";
import Const from "../const/Const"
import useLoading from "../hook/useLoading"
import ApiService from "../service/ApiService"
import Loader from "../component/UI/Loader/Loader"
import ProductContainer from "../component/Product/ProductContainer"

const MyAuction =() => {

    const navigate = useNavigate ()

    const pathVariable = useParams();
    const [auction, setAuction] = useState({
        "email": null
    })

    useEffect(() => {
        ApiService.getAuctionState(pathVariable.id)
        .then(resp => {
            setAuction(resp.data)
            console.log(resp.data)
        }).catch(err => {
            navigate("/404")
        })
    }, [])

    return (
        <div>
            <Header/>
            <div style={{display: "flex", flexDirection:"column"}}>
            <div style={{padding: "10px", display:"flex"}}>
           <Link to={"/user/" + auction.userId}>{auction.email}</Link> <div style={{marginLeft: "20px"}}> предложил</div> <div style={{marginLeft: "20px"}}>
           {auction.currentPrice}</div> <div style={{marginLeft: "20px"}}>за следующий</div> 
           <Link  to={"/myProducts/about/" + auction.productId} style={{marginLeft: "20px"}}>товар</Link>
            </div>
            <div style={{display: "flex"}}>
            <div>Аукцион заканчивается </div> <div style={{marginLeft: "20px"}}>
            {new Intl.DateTimeFormat('en-US', {year: 'numeric', month: '2-digit',day: '2-digit', 'hour': '2-digit', 'minute': '2-digit'}).format(auction.endDate)}
            </div>
            <div style={{marginLeft: "50px"}}>
            {
                auction.isActive
                ? <div style={{color: "green"}}>Не завершен</div>
                : <div style={{color: "red"}}>Завершен</div>
            }
            </div>
            </div>
            </div>
        </div>
    )

}

export default MyAuction;