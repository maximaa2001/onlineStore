package by.bsuir;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.Rating;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.domain.UserRating;
import by.bsuir.entity.domain.key.RatingKey;
import by.bsuir.repo.ProductRepo;
import by.bsuir.repo.UserRatingRepo;
import by.bsuir.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
@Slf4j
class AuthServerApplicationTests {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRatingRepo userRatingRepo;

    @Test
    void contextLoads() {
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    @Transactional
    void oneToMany() {
        User user = userRepo.findById(176).orElseThrow(() -> new RuntimeException());
//        List<Product> products = productRepo.findByUser(user);
//        log.info("product size: {}", products.size());
//        log.info("image: {}", products.get(0).getImages());
    }

    @Test
    public void get(){
        userRatingRepo.save(UserRating.builder().key(RatingKey.builder().setUserId(186).getUserId(181).build())
                .rating(Rating.builder().ratingId(5).ratingNumber(5).build()).build());

    }


}
