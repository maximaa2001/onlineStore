import React from "react";
import style from "./SendButton.module.css"

import { Button } from "react-bootstrap";

const SendButton = ({sendDataCallback, children, ...props}) =>{


return(
<Button variant="success" className={style.btnSend} onClick={sendDataCallback} {...props}>{children}</Button>
)

}


export default SendButton