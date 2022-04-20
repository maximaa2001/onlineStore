import React from "react";
import style from "./Alert.module.css"

const ErrorAlert = ({children, ...props}) =>{
  
return <div  className={[style.alert, "alert", "alert-danger"].join(' ')} role="alert" {...props}>
{children}
</div>
}

export default ErrorAlert;