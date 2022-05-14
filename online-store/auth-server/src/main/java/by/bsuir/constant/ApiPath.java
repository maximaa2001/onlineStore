package by.bsuir.constant;

public class ApiPath {
    public static final String AUTHORIZATION = "AUTHORIZATION";
    public static final String API = "/api";
    public static final String USERS = "/users";
    public static final String LOGIN = "/login";
    public static final String GOOGLE = "/google";
    public static final String LOGOUT = "/logout";
    public static final String AUTH = "/auth";
    public static final String REGISTRATION = "/registration";
    public static final String ACTIVATION = "/activation/{key}";
    public static final String UPLOAD = "/upload";
    public static final String PRODUCT = "/product";
    public static final String CREATE = "/create";
    public static final String MY_PRODUCTS = "/myProducts";
    public static final String ABOUT = "/about";
    public static final String EDIT = "/edit";
    public static final String CATALOG = "/catalog";
    public static final String BASKET = "/basket";
    public static final String CHANGE = "/change";
    public static final String FAVOURITE = "/favourite";
    public static final String PHONE = "/phone";
    public static final String UPDATE = "/update";
    public static final String PASSWORD = "/password";
    public static final String PAGE = "/page";
    public static final String INFO = "/info";
    public static final String RATING = "/rating";
    public static final String SEARCH = "/search";
    public static final String ADMIN = "/admin";
    public static final String WAITING = "/waiting";
    public static final String APPROVE = "/approve";
    public static final String AUCTION = "/auction";
    public static final String STATE = "/state";
    public static final String PART = "/part";
    public static final String SEND = "/send";



    public static final String USER_LOGIN = API + USERS + LOGIN;
    public static final String USER_LOGIN_GOOGLE = API + USERS + LOGIN + GOOGLE;
    public static final String USER_LOGOUT = API + USERS + LOGOUT;
    public static final String USER_AUTH = API + USERS + AUTH;
    public static final String USER_REGISTRATION = API + USERS + REGISTRATION;
    public static final String EMAIL_ACTIVATION = API + USERS + ACTIVATION;

    public static final String UPLOAD_IMAGE = API + UPLOAD;

    public static final String CREATE_PRODUCT = API + PRODUCT + CREATE;
    public static final String GET_MY_PRODUCTS = API + PRODUCT + MY_PRODUCTS;
    public static final String GET_INFO_ABOUT_MY_PRODUCT = API + PRODUCT + MY_PRODUCTS + ABOUT  + "/{id}";
    public static final String EDIT_MT_PRODUCT = API + PRODUCT + MY_PRODUCTS + EDIT;
    public static final String GET_CATALOG = API + CATALOG;
    public static final String CHANGE_BASKET = API + BASKET + CHANGE;
    public static final String GET_FAVOURITE = API + BASKET + FAVOURITE;
    public static final String GET_PHONE_NUMBER = API + PHONE;
    public static final String UPDATE_PHONE_NUMBER = API + PHONE + UPDATE;
    public static final String CHANGE_PASSWORD = API + PASSWORD + CHANGE;
    public static final String VIEW_PRODUCT_BY_ID = API + CATALOG + PRODUCT + "/{id}";
    public static final String GET_CATALOG_PAGES = API + CATALOG + PAGE;
    public static final String GET_USER_INFO = API + USERS + INFO + "/{id}";
    public static final String CREATE_USER_RATING = API + USERS + RATING;
    public static final String GET_SEARCH_PRODUCTS = API + PRODUCT + SEARCH;
    public static final String GET_SEARCH_PAGES = API + PRODUCT + SEARCH + PAGE;


    public static final String GET_WAITING_PRODUCTS = API + ADMIN + WAITING;
    public static final String GET_WAITING_PAGE_COUNT = API + ADMIN + WAITING + PAGE;
    public static final String APPROVE_PRODUCT = API + ADMIN + PRODUCT + APPROVE;

    public static final String CREATE_AUCTION = API + AUCTION + CREATE;
    public static final String GET_STATE_MY_AUCTION = API + AUCTION + STATE;
    public static final String GET_STATE_AUCTION = API + AUCTION + PART;
    public static final String TAKE_PART_AUCTION = API + AUCTION + SEND;

}
