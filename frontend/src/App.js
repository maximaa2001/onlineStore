import React  from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./page/Home";
import Error from "./page/Error";
import {BrowserRouter as Router, Route, Routes } from "react-router-dom";

function App() {
  return (

    <Router>
    <Routes>
    <Route exact path="/" element={<Home/>}/>
    <Route path="*" element={<Error/>}></Route>
    </Routes>

    </Router>

  );
}

export default App;
