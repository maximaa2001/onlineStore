import React, {useEffect, useState,  } from "react";
import {useParams, useNavigate  } from 'react-router-dom';
import PhotoSlider from "../component/Slider/PhotoSlider";
import useLoading from "../hook/useLoading";
import ApiService from "../service/ApiService";
import sliderStyle from "../component/Slider/PhotoSlider.module.css"
import Header from "../component/Header";
import style from "../style/AboutMyProductPage.module.css"
import SendButton from "../component/UI/Button/SendButton";
import Const from "../const/Const";
import Loader from "../component/UI/Loader/Loader";
import axios from "axios";


const AboutCatalogProduct = (props) =>{

    useEffect(() =>{

     
    }, [])
   
    return(
        <div></div>
    )

}

export default AboutCatalogProduct;

