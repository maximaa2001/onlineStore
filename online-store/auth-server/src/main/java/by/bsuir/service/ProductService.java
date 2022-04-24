package by.bsuir.service;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.dto.product.*;

import java.util.Optional;

public interface ProductService {
    ProductIdDto createProduct(Integer userId, CreateProductDto productDto);
    ProductListDto getAllProductsByUser(Integer userId);
    ProductListDto getProductsByStatus(Integer userId, String statusName);
    AboutMyProductDto getMyProductInfo(Integer userId, Optional<String> productId);
    ProductIdDto editMyProduct(Integer userId, EditProductDto editProductDto);
}
