package by.bsuir.service.impl;

import by.bsuir.dao.BasketItemDao;
import by.bsuir.dao.ProductDao;
import by.bsuir.dao.RefDao;
import by.bsuir.dao.UserDao;
import by.bsuir.entity.domain.*;
import by.bsuir.entity.domain.key.BasketId;
import by.bsuir.entity.dto.basket.BasketBooleanDto;
import by.bsuir.entity.dto.product.*;
import by.bsuir.entity.dto.product.catalog.CatalogListDto;
import by.bsuir.entity.dto.product.edit.EditProductDto;
import by.bsuir.entity.dto.product.my.AboutMyProductDto;
import by.bsuir.entity.dto.product.my.ProductListDto;
import by.bsuir.exception.ProductIsNotLinkedException;
import by.bsuir.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private UserDao userDao;
    private RefDao refDao;
    private BasketItemDao basketItemDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, UserDao userDao, RefDao refDao, BasketItemDao basketItemDao){
        this.productDao = productDao;
        this.userDao = userDao;
        this.refDao = refDao;
        this.basketItemDao = basketItemDao;
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

    @Override
    public AboutMyProductDto getMyProductInfo(Integer userId, Optional<String> productId) {
        log.info("Request for getMyProductInfo endpoint");
        checkProductId(productId);
        Product product = productDao.findById(Integer.parseInt(productId.get()));
        if(!product.getUser().getUserId().equals(userId)){
            throw new ProductIsNotLinkedException(HttpStatus.NOT_FOUND);
        }
        return AboutMyProductDto.of(product);
    }

    @Override
    public ProductIdDto editMyProduct(Integer userId, EditProductDto editProductDto) {
        Product product = productDao.findById(editProductDto.getId());
        if(!product.getUser().getUserId().equals(userId)){
            throw new ProductIsNotLinkedException(HttpStatus.BAD_REQUEST);
        }
        Product updateProduct = editProduct(product, editProductDto);
        productDao.save(updateProduct);
        return ProductIdDto.of(updateProduct.getProductId());
    }

    @Override
    public CatalogListDto getProductByPage(Integer page) {
        List<Product> products = productDao.findByPage(page);
        return CatalogListDto.of(products);
    }

    @Override
    public BasketBooleanDto changeBasket(Integer userId, ProductIdDto productIdDto) {
        User user = userDao.findById(userId);
        Product product = productDao.findById(productIdDto.getProductId());
        BasketId hardId = new BasketId();

        hardId.setUser(user.getUserId());
        hardId.setProduct(product.getProductId());

        Optional<BasketItem> basketItem = basketItemDao.findById(hardId);
        if(basketItem.isPresent()){
            basketItemDao.removeById(hardId);
            return BasketBooleanDto.builder()
                    .isAdded(false)
                    .build();
        }

        basketItemDao.save(BasketItem.builder()
                .basketId(hardId)
                .build());

        return BasketBooleanDto.builder()
                .isAdded(true)
                .build();
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

    private Product editProduct(Product product, EditProductDto editProductDto){
        product.setProductName(editProductDto.getProductName());
        product.setDescription(editProductDto.getDescription());
        product.setPrice(editProductDto.getPrice());
        product.setProductStatus(refDao.findWaitingToApproveStatus());
        product.setCategory(refDao.findCategoryByName(editProductDto.getCategory()));
        product.setCity(refDao.findCityByName(editProductDto.getCity()));
        return product;
    }

    private void checkProductId(Optional<String> productId){
        if(productId.isEmpty()){
            throw new ProductIsNotLinkedException(HttpStatus.NOT_FOUND);
        }
        String id = productId.get();
        try {
            Integer.parseInt(id);
        }catch (NumberFormatException e){
            throw new ProductIsNotLinkedException(HttpStatus.NOT_FOUND);
        }
    }
}
