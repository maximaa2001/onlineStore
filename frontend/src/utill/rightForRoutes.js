import React from "react"
import Const from "../const/Const"
import AboutCatalogProduct from "../page/AboutCatalogProduct"
import AboutMyProduct from "../page/AboutMyProduct"
import AdminWaiting from "../page/AdminWaiting"
import CreateProduct from "../page/CreateProduct"
import EditMyProduct from "../page/EditMyProduct"
import EditProfile from "../page/EditProfile"
import Favourites from "../page/Favorites"
import Home from "../page/Home"
import MyProducts from "../page/MyProducts"
import UserPage from "../page/UserPage"
import MyAuction from "../page/MyAuction"
import Auction from "../page/Auction"

export const publicRoutes = [
   {
       path: Const.HOME,
       Component: <Home/>
   },
   {
    path: Const.VIEW_PRODUCT,
    Component: <AboutCatalogProduct/>
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
    },
    {
        path: Const.FAVOURITE_PRODUCTS,
        Component: <Favourites/>
    },
    {
        path: Const.EDIT_PROFILE,
        Component: <EditProfile/>
    },
    {
        path: Const.USER_INFO,
        Component: <UserPage/>
    },
    {
        path: Const.VIEW_MY_AUCTION,
        Component: <MyAuction/>
    },
    {
        path: Const.VIEW_AUCTION,
        Component: <Auction/>
    }

]

export const adminRoutes = [
    {
        path: Const.ADMIN_WAITING,
        Component: <AdminWaiting/>
    }

]