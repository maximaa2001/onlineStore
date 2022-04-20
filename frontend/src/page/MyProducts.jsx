
import { observer } from "mobx-react-lite"
import Header from "../component/Header"
import {Context} from "../index"
import { useContext } from "react"
import ProductStatusNav from "../component/UI/Navigaion/ProductStatusNav"
import { Link } from "react-router-dom";
import SendButton from "../component/UI/Button/SendButton";
import Const from "../const/Const"

const MyProducts = observer(() => {


  const {user} = useContext(Context)

    return<div>

    <Header/>
    <ProductStatusNav/>
    <Link to={Const.CTEATE_PRODUCT}><SendButton>Создать объявление</SendButton></Link>

    </div>
})

export default MyProducts;