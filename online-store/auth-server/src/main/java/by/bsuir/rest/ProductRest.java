package by.bsuir.rest;

import by.bsuir.entity.dto.product.CreateProductDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.ProductListDto;
import by.bsuir.repo.ProductRepo;
import by.bsuir.service.AuthService;
import by.bsuir.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static by.bsuir.constant.ApiPath.*;

@RestController
@Slf4j
public class ProductRest {
    private ProductService productService;
    private AuthService authService;

    @Autowired
    public ProductRest(ProductService productService, AuthService authService){
        this.productService = productService;
        this.authService = authService;
    }

    @PostMapping(CREATE_PRODUCT)
    public ProductIdDto createProduct(@RequestHeader(name = AUTHORIZATION) String token,
                                          @RequestBody CreateProductDto productDto){
        return productService.createProduct(authService.getUserIdByToken(token), productDto);
    }

    @GetMapping(GET_MY_PRODUCTS)
    public ProductListDto getMyProducts(@RequestHeader(name = AUTHORIZATION) String token){
        return productService.getAllProducts(authService.getUserIdByToken(token));
    }
}
