package by.bsuir.dao;

import by.bsuir.entity.domain.Rating;

import java.util.List;

public interface RatingDao {
    List<Rating> findMarksByIds(List<Integer> ids);
}
