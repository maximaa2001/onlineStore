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


    public static final String USER_LOGIN = API + USERS + LOGIN;
    public static final String USER_LOGIN_GOOGLE = API + USERS + LOGIN + GOOGLE;
    public static final String USER_LOGOUT = API + USERS + LOGOUT;
    public static final String USER_AUTH = API + USERS + AUTH;
    public static final String USER_REGISTRATION = API + USERS + REGISTRATION;
    public static final String EMAIL_ACTIVATION = API + USERS + ACTIVATION;

    public static final String UPLOAD_IMAGE = API + UPLOAD;

    public static final String CREATE_PRODUCT = API + PRODUCT + CREATE;
    public static final String GET_MY_PRODUCTS = API + PRODUCT + MY_PRODUCTS;
}
