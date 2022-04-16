import React, { useState, useEffect }  from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./page/Home";
import Error from "./page/Error";
import {BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { observer } from "mobx-react-lite";
import { Context } from "./index";
import { useContext } from "react";
import ApiService from "./service/ApiService";
import jwt_decode from "jwt-decode";
import useLoading from "./hook/useLoading";
import Loader from "./component/UI/Loader/Loader";

const App = observer(() => {

  const {user} = useContext(Context)
  const [loading, setLoading] = useState(true)

  useEffect(async () => {
    await ApiService.authToken()
    .then(resp => {
      const jwt = resp.data.jwt
      if(jwt){
        const decoded = jwt_decode(jwt)
        console.log(decoded)
        user.setRole(decoded.role)
      }
    }).finally(() => setLoading(false))
  }, [])

  if (loading) {
    return <Loader style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
  }

  return (
    
<Router>
    <Routes>
    <Route exact path="/" element={<Home/>}/>
    <Route path="*" element={<Error/>}></Route>
    </Routes>
    </Router>

  )
    
})

export default App;
