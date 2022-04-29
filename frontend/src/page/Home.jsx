import React from "react"
import { observer } from "mobx-react-lite"
import Header from "../component/Header"
import Navigation from "../component/UI/Navigaion/CategoryNavigation"
import {Context} from "../index"
import {useContext, useEffect, useState } from "react"
import useLoading from "../hook/useLoading"
import ApiService from "../service/ApiService"
import CatalogItem from "../component/Product/CatalogItem"
import style from "../style/Home.module.css"

const Home = observer(() => {

  const [navVisible, setNavVisible] = useState(true)
  const [page, setPage] = useState(0)
  const [products, setProducts] = useState([])

  const call = (value) => {
    setNavVisible(value)
  }

  const trySendRequest = useLoading(async() => {
    await ApiService.getCatalog(page)
    .then((resp) => {
      console.log(resp.data.products)
      setProducts(resp.data.products)
    })
  })

  useEffect(() => {
    console.log("aaa")
    trySendRequest.loadData()
  }, [])


  const {user} = useContext(Context)

    return<div>

    <Header setNavVisible={(value) => call(value)}/>

    {
      navVisible 
      ?<div className={style.container}> <Navigation /><div style={{display: "flex", flexDirection:"column", width:"100%"}}>
       {products.map((item) => <CatalogItem key={item.id} product={item}></CatalogItem>)}</div> </div>
      : null
    }
 
  
    </div>
})

export default Home;