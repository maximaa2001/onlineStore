import React, { useEffect, useState } from "react";
import { Form, Button, FloatingLabel } from "react-bootstrap";
import style from "./CreateProductForm.module.css"
import ApiService from "../../service/ApiService";
import PhotoSlider from "../Slider/PhotoSlider";
import ErrorAlert from "../UI/Alert/ErrorAlert";
import useAlert from "../../hook/useAlert";
import { observer } from "mobx-react-lite";
import axios from "axios";

const CreateProductForm = observer(() => {

    const [categories, setCategories] = useState([])
    const [cities, setCities] = useState([])
    const [images, setImages] = useState([])
    const [slideImage, setSlideImage] = useState([])


   

    useEffect(() => {
        ApiService.getCategories()
        .then(resp =>{
          setCategories(resp.data)
        })

        ApiService.getCities()
        .then(resp =>{
          setCities(resp.data)
        })
    }, [])

    const choosePhoto = (e) => {
      if(e.target.files.length > 5){
        errorCountFiles.setShow(true);
        e.target.value = "";
      } else if(e.target.files.length){

       let slide = []


     console.log(e.target.files.length)

        for(let i = 0; i < e.target.files.length; i++){
  
          slide.push(URL.createObjectURL(e.target.files[i]));
        }   

        setSlideImage(slide)

        const arr = []

        for(let i = 0; i < e.target.files.length; i++){
          console.log("+")
          arr.push(e.target.files[i])
        }
        setImages(arr)

  
      }
    }

    useEffect(() => {
      console.log(images.length)
    }, [images])

    const send = () => {
      console.log(images.length)
      const data = new FormData() 
      for(let i = 0; i < images.length; i++){
        data.append('file' + i, images[i])
      }
     
      axios.post("http://localhost:8100/api/upload",data,  {
        headers: {
            "Content-type": "multipart/form-data; boundary=----------287032381131322"
        },                    
    }).then(res => {
      console.log(res.data)
    })
    }

    const errorCountFiles = useAlert(5000)


    return(
      
<div className={style.container}>

<blockquote className={[style.label, "font-weight-lighter"].join(' ')}>
Создать объявление
</blockquote>
    <Form className={style.formElement} >
  <Form.Group className="mb-3">
    <Form.Control placeholder="Название" />
  </Form.Group>

  <FloatingLabel label="Описание" className="mb-3">
    <Form.Control as="textarea" className={style.description}/>
  </FloatingLabel>

  <FloatingLabel controlId="floatingSelect" label="Выберите категорию" className="mb-3">
  <Form.Select>
  {categories.map((item) => <option key={item.category_id}>{item.category_name}</option>)}
  </Form.Select>
</FloatingLabel>

<FloatingLabel controlId="floatingSelect" label="Выберите город" className="mb-3">
  <Form.Select>
  {cities.map((item) => <option key={item.city_id}>{item.city_name}</option>)}
  </Form.Select>
</FloatingLabel>

  <Form.Group className="mb-3" controlId="formBasicPassword">
    <Form.Control  placeholder="Цена" />
  </Form.Group>

  <PhotoSlider images={slideImage}/>
  {
    errorCountFiles.show
  ? <ErrorAlert style={{marginTop:"20px"}}>Нельзя загружать больше 5 фотографий</ErrorAlert>
  : null
}

  <Form.Group controlId="formFile" className="mb-3">
    <Form.Control type="file" onChange={choosePhoto} multiple/>
  </Form.Group>


  <Button variant="primary" onClick={send} >
    Submit
  </Button>
</Form>
</div>
    )
})

export default CreateProductForm;