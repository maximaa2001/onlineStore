package by.bsuir.repo;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductStatus;
import by.bsuir.entity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByUser(User user);
    List<Product> findByUserAndProductStatus(User user, ProductStatus productStatus);
    Page<Product> findByProductStatus(ProductStatus productStatus, Pageable pageable);
}
