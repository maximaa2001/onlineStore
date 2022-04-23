package by.bsuir.service.impl;

import by.bsuir.dao.ProductDao;
import by.bsuir.dao.RefDao;
import by.bsuir.dao.UserDao;
import by.bsuir.entity.domain.*;
import by.bsuir.entity.dto.product.CreateProductDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.ProductListDto;
import by.bsuir.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private UserDao userDao;
    private RefDao refDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, UserDao userDao, RefDao refDao){
        this.productDao = productDao;
        this.userDao = userDao;
        this.refDao = refDao;
    }

    @Override
    public ProductIdDto createProduct(Integer userId, CreateProductDto productDto) {
        Product defaultProduct = createDefaultProduct(productDto);
        User user = userDao.findById(userId);
        City city = refDao.findCityByName(productDto.getCity());
        Category category = refDao.findCategoryByName(productDto.getCategory());
        defaultProduct.setUser(user);
        defaultProduct.setCity(city);
        defaultProduct.setCategory(category);
        Product savedProduct = productDao.save(defaultProduct);
        return ProductIdDto.of(savedProduct.getProductId());
    }

    @Override
    public ProductListDto getAllProductsByUser(Integer userId) {
        log.info("Request for getAllProductsByUser endpoint");
        User user = userDao.findById(userId);
        List<Product> products = productDao.findAllProductsByUser(user);
        return ProductListDto.of(products);
    }

    @Override
    public ProductListDto getProductsByStatus(Integer userId, String statusName) {
        log.info("Request for getProductsByStatus endpoint");
        ProductStatus productStatus = refDao.findProductStatusByName(statusName.toUpperCase());
        User user = userDao.findById(userId);
        List<Product> products = productDao.findProductsByUserAndStatus(user, productStatus);
        return ProductListDto.of(products);
    }

    private Product createDefaultProduct(CreateProductDto createProductDto){
        return Product.builder()
                .productName(createProductDto.getProductName())
                .description(createProductDto.getDescription())
                .price(createProductDto.getPrice())
                .actualDate(LocalDateTime.now())
                .productStatus(refDao.findWaitingToApproveStatus())
                .productLevel(refDao.findLowPriorityLevel())
                .build();
    }
}
