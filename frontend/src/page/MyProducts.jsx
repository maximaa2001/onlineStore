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

const MyProducts = observer(() => {

  const [productList, setProductList] = useState()


  const trySendRequest = useLoading(async () => {
    const path = window.location.pathname;
    switch(path){
      case '/myProducts':
        await ApiService.getAllMyProducts()
        .then(resp =>{
         //  console.log(resp.data)
         console.log(resp.data)
         setProductList(resp.data)
        })
        break
    }

});

  useEffect(() => {
   trySendRequest.loadData()
  },[])


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
})

export default MyProducts;