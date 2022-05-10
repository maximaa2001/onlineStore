export default class Const {
    static AUTH_SERVER = "http://localhost:8100";
    static BACKEND = "http://localhost:3001";
    static TOKEN = "token";
    static AUTHORIZATION = "AUTHORIZATION"
    static USER_ROLE = "ROLE_USER"
    static ADMIN_ROLE = "ROLE_ADMIN"


    //Pages
    static HOME = "/"
    static MY_PRODUCTS = "/myProducts"
    static PROFILE = "/profile"
    static PRODUCT = "/product"
    static USER = "/user"
    static ADMIN = "/admin"
    static CTEATE_PRODUCT =  Const.MY_PRODUCTS + "/create"
    static ACTIVE_PRODUCTS =  Const.MY_PRODUCTS + "/active"
    static MODERATION_PRODUCTS =  Const.MY_PRODUCTS + "/moderation"
    static REJECTED_PRODUCTS =  Const.MY_PRODUCTS + "/rejected"
    static DELETED_PRODUCTS =  Const.MY_PRODUCTS + "/deleted"
    static ABOUT_MY_PRODUCT = Const.MY_PRODUCTS + "/about/:id"   //${id}
    static EDIT_MY_PRODUCT = Const.MY_PRODUCTS + "/edit/:id"   //${id}
    static FAVOURITE_PRODUCTS = Const.MY_PRODUCTS + "/favourite"
    static EDIT_PROFILE = Const.PROFILE + "/edit"
    static VIEW_PRODUCT = Const.PRODUCT + "/:id"   //${id}
    static USER_INFO = Const.USER + "/:id"   //${id}


    static ADMIN_WAITING = Const.ADMIN + "/waitingApprove"
    
}