import Const from "../const/Const"
import CreateProduct from "../page/CreateProduct"
import Home from "../page/Home"
import MyProducts from "../page/MyProducts"

export const publicRoutes = [
   {
       path: Const.HOME,
       Component: <Home/>
   }
]

export const userRoutes = [
    {
        path: Const.MY_PRODUCTS,
        Component: <MyProducts/>
    },
    {
        path: Const.MY_PRODUCTS + "/active",
        Component: <MyProducts/>
    },
    {
        path: Const.CTEATE_PRODUCT,
        Component: <CreateProduct/>
    }
]