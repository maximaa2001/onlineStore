package by.bsuir.dao.impl;

import by.bsuir.constant.ref.*;
import by.bsuir.dao.RefDao;
import by.bsuir.entity.domain.*;
import by.bsuir.repo.*;
import by.bsuir.util.jsonEntity.CategoryJson;
import by.bsuir.util.jsonEntity.CityJson;
import by.bsuir.util.jsonEntity.CountryJson;
import by.bsuir.util.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RefDaoImpl implements RefDao {

    private final UserStatusRepo userStatusRepo;
    private final UserRoleRepo userRoleRepo;
    private final RatingRepo ratingRepo;
    private final ProductStatusRepo productStatusRepo;
    private final ProductLevelRepo productLevelRepo;
    private final MessageStatusRepo messageStatusRepo;
    private final CityRepo cityRepo;
    private final CategoryRepo categoryRepo;


    public RefDaoImpl(UserStatusRepo userStatusRepo, UserRoleRepo userRoleRepo,
                      RatingRepo ratingRepo, ProductStatusRepo productStatusRepo,
                      ProductLevelRepo productLevelRepo, MessageStatusRepo messageStatusRepo,
                      CityRepo cityRepo, CategoryRepo categoryRepo) {
        this.userStatusRepo = userStatusRepo;
        this.userRoleRepo = userRoleRepo;
        this.ratingRepo = ratingRepo;
        this.productStatusRepo = productStatusRepo;
        this.productLevelRepo = productLevelRepo;
        this.messageStatusRepo = messageStatusRepo;
        this.cityRepo = cityRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public UserStatus findNonActiveUserStatus() {
        return userStatusRepo.findByUserStatusName(UserStatusRef.NON_ACTIVE.getName());
    }

    @Override
    public UserStatus findActiveUserStatus() {
        return userStatusRepo.findByUserStatusName(UserStatusRef.ACTIVE.getName());
    }

    @Override
    public UserStatus findBannedUserStatus() {
        return userStatusRepo.findByUserStatusName(UserStatusRef.BANNED.getName());
    }

    @Override
    public UserStatus findGoogleActiveStatus() {
        return userStatusRepo.findByUserStatusName(UserStatusRef.GOOGLE_ACTIVE.getName());
    }

    @Override
    public UserRole findUserRole() {
        return userRoleRepo.findByRoleName(UserRoleRef.USER.getName());
    }

    @Override
    public UserRole findAdminUserRole() {
        return userRoleRepo.findByRoleName(UserRoleRef.ADMIN.getName());
    }

    @Override
    public ProductStatus findWaitingToApproveStatus() {
        return productStatusRepo.findByProductStatusName(ProductStatusRef.WAITING_FOR_APPROVE.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find WAITING_FOR_APPROVE product status"));
    }

    @Override
    public ProductStatus findApprovedStatus() {
        return productStatusRepo.findByProductStatusName(ProductStatusRef.APPROVED.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find APPROVED product status"));
    }

    @Override
    public ProductStatus findNonApprovedStatus() {
        return productStatusRepo.findByProductStatusName(ProductStatusRef.NON_APPROVED.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find NON_APPROVED product status"));
    }

    @Override
    public ProductStatus findDeletedStatus() {
        return productStatusRepo.findByProductStatusName(ProductStatusRef.DELETED.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find DELETED product status"));
    }

    @Override
    public ProductLevel findLowPriorityLevel() {
        return productLevelRepo.findByProductLevelName(ProductLevelRef.LOW_PRIORITY.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find LOW_PRIORITY product level"));
    }

    @Override
    public ProductLevel findHighPriorityLevel() {
        return productLevelRepo.findByProductLevelName(ProductLevelRef.HIGH_PRIORITY.getName())
                .orElseThrow(() -> new RuntimeException("Cannot find HIGH_PRIORITY product level"));
    }

    @Override
    public City findCityByName(String cityName) {
        return cityRepo.findByCityName(cityName)
                .orElseThrow(() -> new RuntimeException("Cannot find city with name " + cityName));
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Cannot find category with name " + categoryName));

    }

    @Override
    public void synchronizeDefine() {
        List<UserStatus> userStatuses = UserStatusRef.getAll();
        List<UserRole> userRoles = UserRoleRef.getAll();
        List<Rating> ratings = RatingRef.getAll();
        List<ProductStatus> productStatuses = ProductStatusRef.getAll();
        List<ProductLevel> productLevels = ProductLevelRef.getAll();
        List<MessageStatus> messageStatuses = MessageStatusRef.getAll();
        List<City> cities = saveCities();
        List<Category> categories = saveCategories();

        userStatusRepo.saveAll(userStatuses);
        userRoleRepo.saveAll(userRoles);
        ratingRepo.saveAll(ratings);
        productStatusRepo.saveAll(productStatuses);
        productLevelRepo.saveAll(productLevels);
        messageStatusRepo.saveAll(messageStatuses);
        cityRepo.saveAll(cities);
        categoryRepo.saveAll(categories);
    }

    private List<City> saveCities() {
        File file = new File(getClass().getClassLoader().getResource("cities.json").getFile());
        if (file.exists()) {
            CountryJson countryJson = JsonParser.simpleParser(file, CountryJson.class);

            if (countryJson != null) {
                List<CityJson> cityJsons = Arrays.stream(countryJson.getRegionJsons())
                        .flatMap(region -> Arrays.stream(region.getCities()))
                        .collect(Collectors.toList());

                List<City> cities = new ArrayList<>();
                for (int i = 1; i <= cityJsons.size(); i++) {
                    cities.add(City.builder()
                            .cityId(i)
                            .cityName(cityJsons.get(i - 1).getName())
                            .build());
                }
                return cities;
            }
        }
        return new ArrayList<>();
    }

    private List<Category> saveCategories() {
        File file = new File(getClass().getClassLoader().getResource("categories.json").getFile());
        if (file.exists()) {
            CategoryJson categoryJson = JsonParser.simpleParser(file, CategoryJson.class);
            if (categoryJson != null) {
                return categoryJson.getCategories();
            }
        }
        return new ArrayList<>();
    }

}
