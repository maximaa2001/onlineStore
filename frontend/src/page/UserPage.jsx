import React from "react"
import { useState } from "react"
import Header from "../component/Header"
import {Context} from "../index"
import { useContext, useEffect } from "react"
import ProductStatusNav from "../component/UI/Navigaion/ProductStatusNav"
import { Link } from "react-router-dom";
import SendButton from "../component/UI/Button/SendButton";
import Const from "../const/Const"
import useLoading from "../hook/useLoading"
import ApiService from "../service/ApiService"
import Loader from "../component/UI/Loader/Loader"
import ProductContainer from "../component/Product/ProductContainer"
import { useParams } from "react-router-dom"
import 'font-awesome/css/font-awesome.min.css';
import style from "../style/UserPage.module.css"

const UserPage =() => {

    const pathVariable = useParams();
    const [myMark, setMyMark] = useState()
    const [user, setUser] = useState({
        "email": ""
    })
    const [canUseEvent, setCanUseEvent] = useState(true)

    const [one, setOne] = useState(["fa fa-star",style.star])
    const [two, setTwo] = useState(["fa fa-star",style.star])
    const [three, setThree] = useState(["fa fa-star",style.star])
    const [four, setFour] = useState(["fa fa-star",style.star])
    const [five, setFive] = useState(["fa fa-star",style.star])

    const hover = (e) => {
        if(canUseEvent){
            let id = e.target.id;

            switch(id){
                case "one":
                    setOne(["fa fa-star",style.fullStar])
                    break
                case "two":
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    break
                case "three":
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    setThree(["fa fa-star",style.fullStar])
                    break
                case "four":
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    setThree(["fa fa-star",style.fullStar])
                    setFour(["fa fa-star",style.fullStar])
                    break
                case "five":
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    setThree(["fa fa-star",style.fullStar])
                    setFour(["fa fa-star",style.fullStar])
                    setFive(["fa fa-star",style.fullStar])
                    break
            }
        }
       
    }

    const out = (e) => {

        if(canUseEvent){
            let id = e.target.id;

            switch(id){
                case "one":
                    setOne(["fa fa-star",style.star])
                    break
                case "two":
                    setOne(["fa fa-star",style.star])
                    setTwo(["fa fa-star",style.star])
                    break
                case "three":
                    setOne(["fa fa-star",style.star])
                    setTwo(["fa fa-star",style.star])
                    setThree(["fa fa-star",style.star])
                    break
                case "four":
                    setOne(["fa fa-star",style.star])
                    setTwo(["fa fa-star",style.star])
                    setThree(["fa fa-star",style.star])
                    setFour(["fa fa-star",style.star])
                    break
                case "five":
                    setOne(["fa fa-star",style.star])
                    setTwo(["fa fa-star",style.star])
                    setThree(["fa fa-star",style.star])
                    setFour(["fa fa-star",style.star])
                    setFive(["fa fa-star",style.star])
                    break
            }
        }
       
    }

    const click = (e) => {
        if(canUseEvent){
            let id = e.target.id;

            switch(id){
                case "one":
                    ApiService.createRating(user.id, 1)
                    .then((resp) => {
                        if(resp.data.isSuccess){
                            setCanUseEvent(false)
                        }
                    })
                    break
                case "two":
                     ApiService.createRating(user.id, 2)
                    .then((resp) => {
                        if(resp.data.isSuccess){
                            setCanUseEvent(false)
                        }
                    })
                    break
                case "three":
                    ApiService.createRating(user.id, 3)
                    .then((resp) => {
                        if(resp.data.isSuccess){
                            setCanUseEvent(false)
                        }
                    })
                    break
                case "four":
                    ApiService.createRating(user.id, 4)
                    .then((resp) => {
                        if(resp.data.isSuccess){
                            setCanUseEvent(false)
                        }
                    })
                    break
                case "five":
                    ApiService.createRating(user.id, 5)
                    .then((resp) => {
                        if(resp.data.isSuccess){
                            setCanUseEvent(false)
                        }
                    })
                    break
            }
        }
       
    }


    const [loading, setLoading] = useState(true)

    useEffect(() => {
         ApiService.getUserInfo(pathVariable.id)
           .then((res) => {
            setUser(res.data)
            if(res.data.myMark){
                console.log("b")
                setMyMark(res.data.myMark)
                setCanUseEvent(false)
            }
            }).finally(() => setLoading(false))
    }, [])

    useEffect(() => {
        if(!canUseEvent){
            switch(myMark){
                case 1:
                    setOne(["fa fa-star",style.fullStar])
                    break
                case 2:
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    break
                case 3:
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    setThree(["fa fa-star",style.fullStar])
                    break
                case 4:
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    setThree(["fa fa-star",style.fullStar])
                    setFour(["fa fa-star",style.fullStar])
                    break
                case 5:
                    setOne(["fa fa-star",style.fullStar])
                    setTwo(["fa fa-star",style.fullStar])
                    setThree(["fa fa-star",style.fullStar])
                    setFour(["fa fa-star",style.fullStar])
                    setFive(["fa fa-star",style.fullStar])
                    break
            }
        }

    }, [canUseEvent])


    if(loading){
        return <Loader style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
      }

    return(
        <div>
        <div>Почта: {user.email}</div>
        <div style={{display: "flex", cursor:"pointer"}}>
        <i id="one" onMouseOver={hover} onMouseOut={out} onClick={click} aria-hidden="true" className={one.join(' ')}></i>
        <i id="two" onMouseOver={hover} onMouseOut={out} onClick={click}  aria-hidden="true" className={two.join(' ')}></i>
        <i id="three" onMouseOver={hover} onMouseOut={out} onClick={click}  aria-hidden="true" className={three.join(' ')}></i>
        <i id="four" onMouseOver={hover} onMouseOut={out} onClick={click}  aria-hidden="true" className={four.join(' ')}></i>
        <i id="five" onMouseOver={hover} onMouseOut={out} onClick={click}  aria-hidden="true" className={five.join(' ')}></i>
        </div>
        
        </div>
    )
  
}

export default UserPage;