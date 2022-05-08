package by.bsuir.dao.impl;

import by.bsuir.dao.UserRatingDao;
import by.bsuir.repo.UserRatingRepo;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
