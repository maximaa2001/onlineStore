package by.bsuir.service;

import by.bsuir.entity.domain.Category;
import by.bsuir.entity.dto.PagesDto;
import by.bsuir.entity.dto.basket.BasketBooleanDto;
import by.bsuir.entity.dto.product.*;
import by.bsuir.entity.dto.product.catalog.AboutCatalogProductDto;
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
    CatalogListDto getProductByPage(Integer userId, Integer page, String categoryName);
    BasketBooleanDto changeBasket(Integer userId, ProductIdDto productIdDto);
    ProductListDto getProductsInCart(Integer userId);
    AboutCatalogProductDto getProductFromCatalog(Integer userId, Integer productId);
    PagesDto getPagesCount(String categoryName);
    CatalogListDto searchProductsByPage(Integer userId, Integer page, String name);
    PagesDto searchProductsPageCount(String name);
}
