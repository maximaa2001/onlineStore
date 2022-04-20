import React from "react";
import Carousel from 'react-bootstrap/Carousel'
import style from "./PhotoSlider.module.css"

const PhotoSlider = (props) => {


    return <div className="mb-3">
    <Carousel className={style.main} interval={null}>
    {props.images.map((item) => 
    
    <Carousel.Item key={item}><img
      className={["d-block", "w-100", style.img].join(' ')}
      src={item}
      alt="Не выбрана"
    /></Carousel.Item>)}
</Carousel></div>

}

export default PhotoSlider;