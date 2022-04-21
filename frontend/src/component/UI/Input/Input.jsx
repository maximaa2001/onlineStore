import React from "react";
import style from "./Input.module.css"

const Input = ({callback, reg, ...props}) =>{


return(
    <input  className={[style.input, "form-control"].join(' ')}  onChange={e => callback(e.currentTarget.value)}
     {...props}/>
)

}


export default Input