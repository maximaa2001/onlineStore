import axios from "axios";
import Const from "../const/Const";

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

    static getCategories = async (accessToken) => {
        const response =  axios.get(Const.BACKEND + "/api/categories/all")
        return response;
    }

    
}