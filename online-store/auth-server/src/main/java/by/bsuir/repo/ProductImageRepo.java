package by.bsuir.repo;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImage, Integer> {
    void deleteByProduct(Product product);
}
