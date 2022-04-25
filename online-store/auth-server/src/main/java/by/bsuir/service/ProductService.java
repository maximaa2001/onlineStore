package by.bsuir.service;

import by.bsuir.entity.dto.product.*;
import by.bsuir.entity.dto.product.catalog.CatalogListDto;
import by.bsuir.entity.dto.product.edit.EditProductDto;
import by.bsuir.entity.dto.product.my.AboutMyProductDto;
import by.bsuir.entity.dto.product.my.ProductListDto;

import java.util.Optional;

public interface ProductService {
    ProductIdDto createProduct(Integer userId, CreateProductDto productDto);
    ProductListDto getAllProductsByUser(Integer userId);
    ProductListDto getProductsByStatus(Integer userId, String statusName);
    AboutMyProductDto getMyProductInfo(Integer userId, Optional<String> productId);
    ProductIdDto editMyProduct(Integer userId, EditProductDto editProductDto);
    CatalogListDto getProductByPage(Integer page);
}
