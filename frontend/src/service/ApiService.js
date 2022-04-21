import axios from "axios";
import Const from "../const/Const";
import configToken from "../utill/config"
import configTokenAndImage from "../utill/config";

export default class ApiService{
   

    static registration = async (email, password, repeatPassword, phoneNumber) => {
        const response =  axios.post(Const.AUTH_SERVER + "/api/users/registration",
        {
            "email": email,
            "password": password,
            "repeatPassword": repeatPassword,
            "phoneNumber": phoneNumber
        })
        return response;
    }

    static login = async (email, password) => {
        const response =  axios.post(Const.AUTH_SERVER + "/api/users/login",
        {
            "email": email,
            "password": password,
        })
        return response;
    }

    static googleLogin = async (accessToken) => {
        const response =  axios.post(Const.AUTH_SERVER + "/api/users/login/google",   
        {
            "access_token": accessToken,
        })
        return response;
    }

    static getCategories = async () => {
        const response =  axios.get(Const.BACKEND + "/api/categories/all")
        return response;
    }

    static getCities = async () => {
        const response =  axios.get(Const.BACKEND + "/api/cities/all")
        console.log(response)
        return response;
    }

    static authToken = async () => {
        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = axios.get(Const.AUTH_SERVER + "/api/users/auth", configToken(jwt))
            return response;
        }
        return null;
    }

    static uploadImages = async () => {
        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = axios.post(Const.AUTH_SERVER + "/api/users/auth", configTokenAndImage(jwt))
            return response;
        }
        return null;
    }

    static createProduct = async (name, desc, category, city, price) => {
        const jwt = localStorage.getItem(Const.TOKEN);
        if(jwt){
            const response = axios.post(Const.AUTH_SERVER + "/api/product/create",
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


   

    
}