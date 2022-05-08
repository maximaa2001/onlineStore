package by.bsuir.dao.impl;

import by.bsuir.dao.RatingDao;
import by.bsuir.entity.domain.Rating;
import by.bsuir.repo.RatingRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingDaoImpl implements RatingDao {
    private RatingRepo ratingRepo;

    public RatingDaoImpl(RatingRepo ratingRepo){
        this.ratingRepo = ratingRepo;
    }

    @Override
    public List<Rating> findMarksByIds(List<Integer> ids) {
        return ratingRepo.findAllById(ids);
    }
}
