package by.bsuir.dao;

import by.bsuir.entity.domain.Product;

public interface ProductDao {
    Product save(Product product);
    Product findById(Integer productId);
}
