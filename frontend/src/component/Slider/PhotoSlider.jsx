import React from "react";
import Carousel from 'react-bootstrap/Carousel'

const PhotoSlider = (props) => {


    return <div className={[props.clazzContainer, "mb-3"].join(' ')}>
    <Carousel className={props.clazzMain} interval={null}>
    {props.images.map((item) => 
    
    <Carousel.Item key={item}><img
      className={props.clazzItem}
      src={item}
      alt="Не выбрана"
    /></Carousel.Item>)}
</Carousel></div>

}

export default PhotoSlider;