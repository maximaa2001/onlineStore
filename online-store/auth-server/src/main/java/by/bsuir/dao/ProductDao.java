package by.bsuir.dao;

import by.bsuir.entity.domain.Category;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductStatus;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.dto.PagesDto;

import java.util.List;

public interface ProductDao {
    Product save(Product product);
    Product findById(Integer productId);
    List<Product> findAllProductsByUser(User user);
    List<Product> findProductsByUserAndStatus(User user, ProductStatus productStatus);
    List<Product> findByPage(Integer page);
    PagesDto getCatalogPages();
    List<Product> findByPageAndCategory(Integer page, Category category);
    PagesDto getCatalogPagesByCategory(Category category);
    List<Product> searchProductsByPage(Integer page, String name);
    PagesDto getSearchPages(String name);
}
