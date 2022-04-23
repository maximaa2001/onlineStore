package by.bsuir.dao;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;

import java.util.List;

public interface ProductDao {
    Product save(Product product);
    Product findById(Integer productId);
    List<Product> findAllProducts(User user);
}
