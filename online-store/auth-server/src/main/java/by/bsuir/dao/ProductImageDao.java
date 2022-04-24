package by.bsuir.dao;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductImage;

import java.util.List;

public interface ProductImageDao {
    void saveAll(List<ProductImage> productImages);
    void removeByProduct(Product product);
}
