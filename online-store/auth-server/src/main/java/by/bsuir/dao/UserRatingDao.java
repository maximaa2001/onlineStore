package by.bsuir.dao;

import by.bsuir.entity.domain.UserRating;
import by.bsuir.entity.domain.key.RatingKey;

import java.util.List;
import java.util.Optional;

public interface UserRatingDao {
    List<Integer> getRatingForUser(Integer userId);
    UserRating save(UserRating userRating);
    Optional<UserRating> findById(RatingKey ratingKey);
}
