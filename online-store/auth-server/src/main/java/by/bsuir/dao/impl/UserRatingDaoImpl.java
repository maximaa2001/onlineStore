package by.bsuir.dao.impl;

import by.bsuir.dao.UserRatingDao;
import by.bsuir.entity.domain.UserRating;
import by.bsuir.entity.domain.key.RatingKey;
import by.bsuir.repo.UserRatingRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRatingDaoImpl implements UserRatingDao {
    private UserRatingRepo userRatingRepo;

    public UserRatingDaoImpl(UserRatingRepo userRatingRepo){
        this.userRatingRepo = userRatingRepo;
    }

    @Override
    public List<Integer> getRatingForUser(Integer userId) {
        return userRatingRepo.getRatingForUser(userId);
    }

    @Override
    public UserRating save(UserRating userRating) {
        return userRatingRepo.save(userRating);
    }

    @Override
    public Optional<UserRating> findById(RatingKey ratingKey) {
        return userRatingRepo.findById(ratingKey);
    }
}
