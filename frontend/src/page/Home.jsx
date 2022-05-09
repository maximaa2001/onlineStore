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
import { Pagination } from "react-bootstrap"
import Loader from "../component/UI/Loader/Loader"

const Home = observer(() => {

  const [navVisible, setNavVisible] = useState(true)
  const [pages, setPages] = useState([1])
  const [products, setProducts] = useState([])
  const [currentCategory, setCurrentCategory] = useState()


  const [activePage, setActivePage] = useState(1)

  const [loading, setLoading] = useState(true)

  const searchProduct = (name) => {
    if(name){
      ApiService.searchProductsPageCount(name)
      .then(resp => {
        let arr = []
        for(let i = 1; i <= resp.data.count; i++){
          arr.push(i)
        }
        setPages(arr)
        setActivePage(1)
      }).finally(() => setLoading(false))
  
       ApiService.searchProductsByPage(0, name)
      .then((resp) => {
        setProducts(resp.data.products)
      })
    }
  }


  const call = (value) => {
    setNavVisible(value)
  }

  const trySendRequest = useLoading(async() => {
      await ApiService.getCatalog(pages[pages.length - 1] - 1)
      .then((resp) => {
        setProducts(resp.data.products)
      })
  
      await ApiService.getPagesCount()
      .then((resp) => {
        let arr = []
        for(let i = 1; i <= resp.data.count; i++){
          arr.push(i)
        }
        setPages(arr)
      }).finally(() => setLoading(false))  

  })

  useEffect(() => {
    trySendRequest.loadData()
  }, [])

  useEffect(() => {
    if(currentCategory){
     ApiService.getPagesCount(currentCategory)
      .then((resp) => {
      let arr = []
      for(let i = 1; i <= resp.data.count; i++){
        arr.push(i)
      }
      setPages(arr)
      setActivePage(1)
    }).finally(() => setLoading(false))

    ApiService.getCatalog(0, currentCategory)
    .then((resp) => {
      setProducts(resp.data.products)
    })

    } 
  }, [currentCategory])


  const nextPage = (page) => {
    setLoading(true)
    setActivePage(page)
    ApiService.getCatalog(page-1, currentCategory)
    .then((resp) => {
      setProducts(resp.data.products)
    }).finally(() => setLoading(false))
  }


  const {user} = useContext(Context)

  if(loading){
    return <Loader style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
  }

    return<div>

    <Header searchProduct={(name) => searchProduct(name)} setNavVisible={(value) => call(value)}/>

    {
      navVisible 
      ?
      <div>
    
      <div className={style.container}> <Navigation setCurrentCategory={setCurrentCategory} />
      
      <div style={{display: "flex", flexDirection:"column", width:"100%"}}>
      {
        products.length > 0
        ? <div>{products.map((item) => <CatalogItem key={item.id} product={item}></CatalogItem>)}</div>
        :  <div style={{marginLeft: "50px", fontSize: "20px"}}>Товаров не найдено</div>
      }
      
       <div>

       </div>
       
              
       <Pagination style={{marginLeft: "50%", marginTop: "10px",  marginTop: "10px", transform: "translate(-10%, 0)"}}>

  
{pages.map((item) => <Pagination.Item key={item}
active={ item === activePage ? true : false} onClick={e => nextPage(item)}>
{item}</Pagination.Item>)}
   

</Pagination>
       
        </div> 
       

       
       </div> 

       </div>
       
      : null
    }

   
    </div>
})

export default Home;