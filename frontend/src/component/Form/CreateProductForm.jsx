import React, { useEffect, useState } from "react";
import { Form, Button, FloatingLabel } from "react-bootstrap";
import style from "./CreateProductForm.module.css"
import ApiService from "../../service/ApiService";
import PhotoSlider from "../Slider/PhotoSlider";
import ErrorAlert from "../UI/Alert/ErrorAlert";
import useAlert from "../../hook/useAlert";
import { observer } from "mobx-react-lite";
import { Formik } from "formik";
import * as Yup from 'yup';
import useLoading from "../../hook/useLoading";
import Loader from "../UI/Loader/Loader";
import SuccessAlert from "../UI/Alert/SuccessAlert";
import sliderStyle from "../Slider/PhotoSlider.module.css"

const CreateProductForm = observer(() => {

    const [categories, setCategories] = useState([])
    const [cities, setCities] = useState([])
    const [images, setImages] = useState([])
    const [slideImage, setSlideImage] = useState([])

    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [category, setCategory] = useState('')
    const [city, setCity] = useState('')
    const [price, setPrice] = useState('')

    const [createdProductId, setCreatedProductId] = useState()

    const successAlert = useAlert(5000);

    const data = new FormData() 

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

        for(let i = 0; i < e.target.files.length; i++){
  
          slide.push(URL.createObjectURL(e.target.files[i]));
        }   

        setSlideImage(slide)

        const arr = []

        for(let i = 0; i < e.target.files.length; i++){
          arr.push(e.target.files[i])
        }
        setImages(arr)
      }
    }

     const trySendPhotos = useLoading(async () => {
       await ApiService.uploadPhotos(data)
       .then(resp => {
         if(resp.status == '200'){
          successAlert.setShow(true)
          setName('')
          setDescription('')
          setPrice('')
          setImages([])
          setSlideImage([])
         }
       })
     })

     useEffect(() => {
      if(createdProductId > 0){
        sendImages();
        setCreatedProductId(0)
      }
    }, [createdProductId])

    const sendImages = () => {
      
      for(let i = 0; i < images.length; i++){
        data.append('file' + i, images[i])
      }

      data.append('productId', createdProductId)
     
      trySendPhotos.loadData()

    }

    const errorCountFiles = useAlert(5000)

    const validationSchema=Yup.object().shape({
      name: Yup.string().required('Обязательно'),
      price: Yup.number().typeError('Должна быть цифра').max(9999999999, "Слишком большое число"),
      category: Yup.string().required('Обязательно'),
      city: Yup.string().required('Обязательно')
    })

  

    return(

      <div>

      <Formik initialValues={{
        name: '',
        description: '',
        category: '',
        city: '',
        price: '',
      }}
    
      onSubmit={(values, {resetForm}) => {

        ApiService.createProduct(name, description, category, city, price)
        .then(resp => {
        setCreatedProductId(resp.data.productId)
        })

        resetForm()

      }}
      validationSchema={validationSchema}>

      {({values, errors, touched, handleChange, handleBlur, isValid, handleSubmit, dirty}) => (

        <div className={style.container}>

<blockquote className={[style.label, "font-weight-lighter"].join(' ')}>
Создать объявление
</blockquote>
    <Form className={style.formElement} >
  <Form.Group className="mb-3">
    <Form.Control placeholder="Название" onChange={(e) => {handleChange(e); setName(e.target.value)}} onBlur={handleBlur} name="name" value={name}/>
  </Form.Group>
  {errors.name && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.name}</div> }

  <FloatingLabel label="Описание" className="mb-3">
    <Form.Control as="textarea" className={style.description} onChange={(e) => {handleChange(e); setDescription(e.target.value)}} 
    onBlur={handleBlur} name="description" value={description}/>
  </FloatingLabel>
  {errors.description && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.description}</div> }

  <FloatingLabel controlId="floatingSelect" label="Выберите категорию" className="mb-3">
  <Form.Select onChange={(e) => {handleChange(e); setCategory(e.target.value)}} onBlur={handleBlur} name="category" value={category}>
  {categories.map((item) => <option key={item.category_id}>{item.category_name}</option>)}
  </Form.Select>
</FloatingLabel>
 {errors.category && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.category}</div> }

<FloatingLabel controlId="floatingSelect" label="Выберите город" className="mb-3">
  <Form.Select onChange={(e) => {handleChange(e); setCity(e.target.value)}} onBlur={handleBlur} name="city" value={city}>
  {cities.map((item) => <option key={item.city_id}>{item.city_name}</option>)}
  </Form.Select>
</FloatingLabel>
{errors.city && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.city}</div> }

  <Form.Group className="mb-3" controlId="formBasicPassword" >
    <Form.Control  placeholder="Цена" onChange={(e) => {handleChange(e); setPrice(e.target.value)}} onBlur={handleBlur} name="price" value={price}/>
  </Form.Group>
  {errors.price && <div style={{color:"red",marginTop:"-20px", fontSize:"12px", marginBottom: "5px"}}>{errors.price}</div> }

  <PhotoSlider images={slideImage} clazzContainer={sliderStyle.containerCreate} clazzMain={sliderStyle.mainCreate} clazzItem={[sliderStyle.imgCreate, "d-block", "w-100"].join(' ')}/>
  {
    errorCountFiles.show
  ? <ErrorAlert style={{marginTop:"20px"}}>Нельзя загружать больше 5 фотографий</ErrorAlert>
  : null
}

  <Form.Group controlId="formFile" className="mb-3">
    <Form.Control type="file" onChange={choosePhoto} multiple/>
  </Form.Group>

  {
          trySendPhotos.isLoading 
          ? <Loader  style={{position:"absolute", left:"48%", top:"30%"}}></Loader>
          : ""
    }

    {
    successAlert.show
    ? <SuccessAlert style={{ position: "absolute", bottom: "2px"}}>Заказ создан</SuccessAlert>
    : ""
        }


  <Button variant="primary" onClick={handleSubmit} type="submit">
    Submit
  </Button>


  
</Form>
</div>
      )}

   </Formik>

      </div>
      

    )
})

export default CreateProductForm;