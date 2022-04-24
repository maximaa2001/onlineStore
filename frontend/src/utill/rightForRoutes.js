import Const from "../const/Const"
import AboutMyProduct from "../page/AboutMyProduct"
import CreateProduct from "../page/CreateProduct"
import EditMyProduct from "../page/EditMyProduct"
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
        path: Const.ACTIVE_PRODUCTS,
        Component: <MyProducts/>
    },
    {
        path: Const.MODERATION_PRODUCTS,
        Component: <MyProducts/>
    },
    {
        path: Const.REJECTED_PRODUCTS,
        Component: <MyProducts/>
    },
    {
        path: Const.DELETED_PRODUCTS,
        Component: <MyProducts/>
    },
    {
        path: Const.CTEATE_PRODUCT,
        Component: <CreateProduct/>
    },
    {
        path: Const.ABOUT_MY_PRODUCT,
        Component: <AboutMyProduct/>
    },
    {
        path: Const.EDIT_MY_PRODUCT,
        Component: <EditMyProduct/>
    }
]