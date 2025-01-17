package by.bsuir.rest;

import by.bsuir.entity.dto.PagesDto;
import by.bsuir.entity.dto.basket.BasketBooleanDto;
import by.bsuir.entity.dto.product.CreateProductDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.catalog.AboutCatalogProductDto;
import by.bsuir.entity.dto.product.catalog.CatalogListDto;
import by.bsuir.entity.dto.product.edit.EditProductDto;
import by.bsuir.entity.dto.product.my.AboutMyProductDto;
import by.bsuir.entity.dto.product.my.ProductListDto;
import by.bsuir.service.AuthService;
import by.bsuir.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static by.bsuir.constant.ApiPath.*;
import static by.bsuir.constant.Request.PRODUCT_STATUS_NAME;

@RestController
@Slf4j
public class ProductRest {
    private ProductService productService;
    private AuthService authService;

    @Autowired
    public ProductRest(ProductService productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;
    }


    @PostMapping(CREATE_PRODUCT)
    public ProductIdDto createProduct(@RequestHeader(name = AUTHORIZATION) String token,
                                      @RequestBody CreateProductDto productDto) {
        return productService.createProduct(authService.getUserIdByToken(token), productDto);
    }

    @GetMapping(GET_MY_PRODUCTS)
    public ProductListDto getMyProducts(@RequestHeader(name = AUTHORIZATION) String token,
                                        @RequestParam(name = PRODUCT_STATUS_NAME, required = false) String productStatus) {
        return (productStatus == null) ? productService.getAllProductsByUser(authService.getUserIdByToken(token))
                : productService.getProductsByStatus(authService.getUserIdByToken(token), productStatus);
    }

    @GetMapping(GET_INFO_ABOUT_MY_PRODUCT)
    public AboutMyProductDto getMyProductInfo(@RequestHeader(name = AUTHORIZATION) String token,
                                              @PathVariable(name = "id") String productId) {
        return productService.getMyProductInfo(authService.getUserIdByToken(token), Optional.of(productId));
    }

    @PostMapping(EDIT_MT_PRODUCT)
    public ProductIdDto editProduct(@RequestHeader(name = AUTHORIZATION) String token,
                                    @RequestBody EditProductDto editProductDto) {
        return productService.editMyProduct(authService.getUserIdByToken(token), editProductDto);
    }

    @GetMapping(GET_CATALOG)
    public CatalogListDto getCatalogByPage(@RequestHeader(name = AUTHORIZATION, required = false) String token,
                                           @RequestParam(name = "page") Integer page,
                                           @RequestParam(name = "category", required = false) String categoryName) {
        return productService.getProductByPage((token == null) ? null : authService.getUserIdByToken(token), page, categoryName);
    }

    @PostMapping(CHANGE_BASKET)
    public BasketBooleanDto changeBasket(@RequestHeader(name = AUTHORIZATION) String token,
                                         @RequestBody ProductIdDto productIdDto) {
        return productService.changeBasket(authService.getUserIdByToken(token), productIdDto);
    }

    @GetMapping(GET_FAVOURITE)
    public ProductListDto getFavouriteProducts(@RequestHeader(name = AUTHORIZATION) String token) {
        return productService.getProductsInCart(authService.getUserIdByToken(token));
    }

    @GetMapping(VIEW_PRODUCT_BY_ID)
    public AboutCatalogProductDto getCatalogItem(@RequestHeader(name = AUTHORIZATION, required = false) String token,
                                                     @PathVariable(name = "id") Integer id) {
        return (token == null) ? productService.getProductFromCatalog(null, id)
                : productService.getProductFromCatalog(authService.getUserIdByToken(token), id);
    }

    @GetMapping(GET_CATALOG_PAGES)
    public PagesDto getPagesCount(@RequestParam(name = "category", required = false) String category) {
        return productService.getPagesCount(category);
    }

    @GetMapping(GET_SEARCH_PRODUCTS)
    public CatalogListDto searchProducts(@RequestHeader(name = AUTHORIZATION, required = false) String token,
                                         @RequestParam(name = "page") Integer page,
                                         @RequestParam(name = "name") String name) {
        return productService.searchProductsByPage((token == null) ? null : authService.getUserIdByToken(token), page, name);
    }

    @GetMapping(GET_SEARCH_PAGES)
    public PagesDto searchProducts(@RequestParam(name = "name") String name) {
        return productService.searchProductsPageCount(name);
    }
}
