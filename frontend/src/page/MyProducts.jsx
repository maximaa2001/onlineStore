import React from "react"
import { useState } from "react"
import { observer } from "mobx-react-lite"
import Header from "../component/Header"
import {Context} from "../index"
import { useContext, useEffect } from "react"
import ProductStatusNav from "../component/UI/Navigaion/ProductStatusNav"
import { Link, useResolvedPath } from "react-router-dom";
import SendButton from "../component/UI/Button/SendButton";
import Const from "../const/Const"
import ProductItem from "../component/Product/ProductItem"
import useLoading from "../hook/useLoading"
import ApiService from "../service/ApiService"
import Loader from "../component/UI/Loader/Loader"
import ProductContainer from "../component/Product/ProductContainer"
import AboutMyProduct from "./AboutMyProduct"

const MyProducts =() => {

  const [productList, setProductList] = useState()


  const trySendRequest = useLoading(async () => {
    debugger
    switch(window.location.pathname){
      case Const.MY_PRODUCTS:
        await ApiService.getAllMyProducts()
        .then(resp =>{
         setProductList(resp.data)
        })
        break
      case Const.ACTIVE_PRODUCTS:
        await ApiService.getMyProductsByStatus("APPROVED")
        .then(resp =>{
         setProductList(resp.data)
        })
        break
      case Const.MODERATION_PRODUCTS:
          await ApiService.getMyProductsByStatus("WAITING_FOR_APPROVE")
          .then(resp =>{
           setProductList(resp.data)
          })
          break
      case Const.REJECTED_PRODUCTS:
          await ApiService.getMyProductsByStatus("NON_APPROVED")
          .then(resp =>{
           setProductList(resp.data)
          })
          break
      case Const.DELETED_PRODUCTS:
          await ApiService.getMyProductsByStatus("DELETED")
          .then(resp =>{
           setProductList(resp.data)
          })
          break

    }

});

  useEffect(() => {
   trySendRequest.loadData()
  },[window.location.pathname])

  const {user} = useContext(Context)

    return<div>
    {
      trySendRequest.isLoading 
          ? <Loader  style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
          : ""
    }

    <Header/>
    <ProductStatusNav/>
    <Link to={Const.CTEATE_PRODUCT}><SendButton>Создать объявление</SendButton></Link>
    <ProductContainer productList={productList}/>
    </div>
}

export default MyProducts;