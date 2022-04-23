package by.bsuir.rest;

import by.bsuir.entity.dto.product.CreateProductDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.ProductListDto;
import by.bsuir.service.AuthService;
import by.bsuir.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static by.bsuir.constant.ApiPath.*;
import static by.bsuir.constant.Request.*;

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
        log.info(productStatus);
        return (productStatus == null) ? productService.getAllProductsByUser(authService.getUserIdByToken(token))
                : productService.getProductsByStatus(authService.getUserIdByToken(token), productStatus);
    }
}
