package by.bsuir.repo;

import by.bsuir.entity.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryName(String categoryName);
}
