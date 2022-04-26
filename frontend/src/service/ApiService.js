import axios from "axios";
import Const from "../const/Const";
import configToken from "../utill/config"
import configTokenAndImage from "../utill/config";

export default class ApiService{
   

    static registration = async (email, password, repeatPassword, phoneNumber) => {
        const response = await  axios.post(Const.AUTH_SERVER + "/api/users/registration",
        {
            "email": email,
            "password": password,
            "repeatPassword": repeatPassword,
            "phoneNumber": phoneNumber
        })
        return response;
    }

    static login = async (email, password) => {
        const response = await  axios.post(Const.AUTH_SERVER + "/api/users/login",
        {
            "email": email,
            "password": password,
        })
        return response;
    }

    static googleLogin = async (accessToken) => {
        const response = await  axios.post(Const.AUTH_SERVER + "/api/users/login/google",   
        {
            "access_token": accessToken,
        })
        return response;
    }

    static getCategories = async () => {
        const response = await  axios.get(Const.BACKEND + "/api/categories/all")
        return response;
    }

    static getCities = async () => {
        const response = await  axios.get(Const.BACKEND + "/api/cities/all")
        console.log(response)
        return response;
    }

    static authToken = async () => {
        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = await axios.get(Const.AUTH_SERVER + "/api/users/auth", configToken(jwt))
            return response;
        }
        return null;
    }

    static createProduct = async (name, desc, category, city, price) => {
        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = await axios.post(Const.AUTH_SERVER + "/api/product/create",
            {
                "productName": name,
                "description": desc,
                "city": city,
                "category": category,
                "price": price,
            }, configToken(jwt))
            return response;
        }
        return null;
    }

    static uploadPhotos = async (data) => {
        const jwt = await localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response =  axios.post("http://localhost:8100/api/upload",data,  {
                headers: {
                    "AUTHORIZATION": jwt,
                    "Content-type": "application/json"
                },                    
              })
              return response;
        }
       
    }

    static getAllMyProducts = async () => {
        const jwt =  localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = await  axios.get("http://localhost:8100/api/product/myProducts",  {
                headers: {
                    "AUTHORIZATION": jwt,
                    "Content-type": "application/json"
                },                    
              })
              return response;
        }
       
    }

    static getMyProductsByStatus = async (status) => {
        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = await  axios.get("http://localhost:8100/api/product/myProducts",  {
                headers: {
                    "AUTHORIZATION": jwt,
                    "Content-type": "application/json"
                },
                params: {
                    "productStatus": status
                },                  
              })
              return response;
        }
       
    }

    static getInfoAboutMyProduct = async (id) => {

        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = await  axios.get(`http://localhost:8100/api/product/myProducts/about/${id}`,  {
                headers: {
                    "AUTHORIZATION": jwt,
                    "Content-type": "application/json"
                },                 
              })
              return response;
        }
       
    }

    static editProduct = async (id, name, desc, category, city, price) => {
        const jwt = await localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = axios.post(Const.AUTH_SERVER + "/api/product/myProducts/edit",
            {
                "id": id,
                "productName": name,
                "description": desc,
                "city": city,
                "category": category,
                "price": price,
            }, configToken(jwt))
            return response;
        }
        return null;
    }

    static getCatalog = async (page) => {
        const jwt = localStorage.getItem(Const.TOKEN)
            const response = await  axios.get(Const.AUTH_SERVER + "/api/catalog",  {
                headers: {
                    "AUTHORIZATION": jwt,
                    "Content-type": "application/json"
                },    
                params: {
                    page: page
                }             
              })
              return response;
    }

    static changeBasket = async (productId) => {
        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = await axios.post(Const.AUTH_SERVER + "/api/basket/change",
            {
                "productId": productId
            }, configToken(jwt))
            return response;
        }
        return null;
    }
}
