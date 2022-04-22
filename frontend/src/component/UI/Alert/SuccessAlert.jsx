import React from "react";
import style from "./Alert.module.css"

const SuccessAlert = ({children, ...props}) =>{
  
return <div  className={[style.alert, "alert", "alert-success"].join(' ')} role="alert" {...props}>
{children}
</div>
}

export default SuccessAlert;