package by.bsuir.dao;

import java.util.List;

public interface UserRatingDao {
    List<Integer> getRatingForUser(Integer userId);
}
