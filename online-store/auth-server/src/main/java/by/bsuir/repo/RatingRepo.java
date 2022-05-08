package by.bsuir.repo;

import by.bsuir.entity.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepo extends JpaRepository<Rating, Integer> {
    Optional<Rating> findByRatingNumber(Integer ratingNumber);
}
