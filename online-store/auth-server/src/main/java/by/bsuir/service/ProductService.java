package by.bsuir.service;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.dto.product.CreateProductDto;
import by.bsuir.entity.dto.product.ProductIdDto;

public interface ProductService {
    ProductIdDto createProduct(Integer userId, CreateProductDto productDto);
}
