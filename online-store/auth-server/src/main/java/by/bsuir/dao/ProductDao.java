package by.bsuir.dao;

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
}
