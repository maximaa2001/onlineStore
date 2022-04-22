package by.bsuir.repo;

import by.bsuir.entity.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductStatusRepo extends JpaRepository<ProductStatus, Integer> {
    Optional<ProductStatus> findByProductStatusName(String statusName);
}
