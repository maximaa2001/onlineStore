package by.bsuir.repo;

import by.bsuir.entity.domain.UserRating;
import by.bsuir.entity.domain.key.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRatingRepo extends JpaRepository<UserRating, RatingKey> {

    @Query(value = "select rating_number_id from user_rating where get_user_id = :userId", nativeQuery = true)
    List<Integer> getRatingForUser(@Param("userId") Integer userId);
}
