import React, {useEffect, useState,  } from "react";
import PhotoSlider from "../component/Slider/PhotoSlider";
import useLoading from "../hook/useLoading";
import ApiService from "../service/ApiService";
import sliderStyle from "../component/Slider/PhotoSlider.module.css"
import Header from "../component/Header";
import style from "../style/AboutMyProductPage.module.css"
import SendButton from "../component/UI/Button/SendButton";
import EditProductForm from "../component/Form/EditProductForm";
import {useParams, useNavigate  } from 'react-router-dom';
import Loader from "../component/UI/Loader/Loader";


const EditMyProduct = (props) =>{

   return(
   <div>
     
       <EditProductForm/>
   </div>
   )

}

export default EditMyProduct;

