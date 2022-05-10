package by.bsuir.dao;

import by.bsuir.entity.domain.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RefDao {
    UserStatus findNonActiveUserStatus();
    UserStatus findActiveUserStatus();
    UserStatus findBannedUserStatus();
    UserStatus findGoogleActiveStatus();
    UserRole findUserRole();
    UserRole findAdminUserRole();

    ProductStatus findWaitingToApproveStatus();
    ProductStatus findApprovedStatus();
    ProductStatus findNonApprovedStatus();
    ProductStatus findDeletedStatus();

    ProductLevel findLowPriorityLevel();
    ProductLevel findHighPriorityLevel();

    City findCityByName(String cityName);
    Category findCategoryByName(String categoryName);
    ProductStatus findProductStatusByName(String productName);
    ProductStatus findProductStatusById(Integer productId);

    void synchronizeDefine();
}
