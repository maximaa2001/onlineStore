package by.bsuir.repo;

import by.bsuir.entity.domain.ProductLevel;
import by.bsuir.entity.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductLevelRepo extends JpaRepository<ProductLevel, Integer> {
    Optional<ProductLevel> findByProductLevelName(String levelName);
}
