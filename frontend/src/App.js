import React, { useState, useEffect }  from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Error from "./page/Error";
import {BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { observer } from "mobx-react-lite";
import { Context } from "./index";
import { useContext } from "react";
import ApiService from "./service/ApiService";
import jwt_decode from "jwt-decode";
import Loader from "./component/UI/Loader/Loader";
import { adminRoutes, publicRoutes, userRoutes } from "./utill/rightForRoutes";
import Const from "./const/Const";

const App = observer(() => {

  const {user} = useContext(Context)
  const [loading, setLoading] = useState(true)

  useEffect(async () => {
    const jwt = localStorage.getItem("token")
    if(jwt){
      await ApiService.authToken()
      .then(resp => {
        const jwt = resp.data.jwt
        if(jwt){
          const decoded = jwt_decode(jwt)
          user.setRole(decoded.role)
        }
      }).finally(() => setLoading(false))
    }
    setLoading(false)
   
  }, [])

  if (loading) {
    return <Loader style={{position:"absolute", left:"48%", top:"15%"}}></Loader>
  }

  return (
    
<Router>
    <Routes>
    {
      publicRoutes.map(({path, Component}) => <Route exact path={path} element={Component}/>)
    }

    {
      user.role === Const.USER_ROLE && userRoutes.map(({path, Component}) => <Route exact path={path} element={Component}/>)
    }

    {
      user.role === Const.ADMIN_ROLE && adminRoutes.map(({path, Component}) => <Route exact path={path} element={Component}/>)
    }
    {/* <Route exact path="/" element={<Home/>}/>
    <Route exact path="/myProducts" element={<MyProducts/>}/> */}
    <Route path="*" element={<Error/>}></Route>
    </Routes>
    </Router>

  )
    
})

export default App;
