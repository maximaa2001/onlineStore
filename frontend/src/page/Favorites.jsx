import React, {useEffect, useState,  } from "react";
import useLoading from "../hook/useLoading";
import ApiService from "../service/ApiService";
import Header from "../component/Header";
import Loader from "../component/UI/Loader/Loader";
import FavoiriteContainer from "../component/Product/FavouriteContainer";


const Favourites = (props) =>{

    const [favourites, setFavourites] = useState()
    const [loading, setLoading] = useState(true)


    const trySendRequest = useLoading(async () => {
        await ApiService.getFavourite()
        .then(resp => {
            setFavourites(resp.data);
        }).finally(() => setLoading(false))
    })

    useEffect(() => {
        trySendRequest.loadData()
    }, [])

    
    if(loading){
        return <Loader  style={{position:"absolute", left:"48%", top:"30%"}}></Loader>

    }



    return(
        <div>
        <Header/>
            <FavoiriteContainer productList={favourites}/>
        </div>
    )

}

export default Favourites;

