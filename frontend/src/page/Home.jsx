
import { useEffect, useState } from "react"
import Header from "../component/Header"
import Navigation from "../component/UI/Navigaion/Navigation"
import ApiService from "../API/ApiService"

const Home = () => {

    const [categories, setCategories] = useState([])

    useEffect(async () => {
        await ApiService.getCategories()
         .then(resp =>{
           setCategories(resp.data)
         })

    }, [])

    return<div>
    <Header/>
    <Navigation listItems={categories}/>

    </div>
}

export default Home;