package by.bsuir.service;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.dto.product.AboutMyProductDto;
import by.bsuir.entity.dto.product.CreateProductDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.ProductListDto;

import java.util.Optional;

public interface ProductService {
    ProductIdDto createProduct(Integer userId, CreateProductDto productDto);
    ProductListDto getAllProductsByUser(Integer userId);
    ProductListDto getProductsByStatus(Integer userId, String statusName);
    AboutMyProductDto getMyProductInfo(Integer userId, Optional<String> productId);
}
