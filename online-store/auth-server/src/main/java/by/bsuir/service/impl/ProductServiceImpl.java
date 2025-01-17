package by.bsuir.service.impl;

import by.bsuir.constant.ref.ProductStatusRef;
import by.bsuir.dao.AuctionDao;
import by.bsuir.dao.ProductDao;
import by.bsuir.dao.RefDao;
import by.bsuir.dao.UserDao;
import by.bsuir.entity.domain.*;
import by.bsuir.entity.dto.PagesDto;
import by.bsuir.entity.dto.basket.BasketBooleanDto;
import by.bsuir.entity.dto.product.*;
import by.bsuir.entity.dto.product.catalog.AboutCatalogProductDto;
import by.bsuir.entity.dto.product.catalog.CatalogListDto;
import by.bsuir.entity.dto.product.edit.EditProductDto;
import by.bsuir.entity.dto.product.my.AboutMyProductDto;
import by.bsuir.entity.dto.product.my.ProductDto;
import by.bsuir.entity.dto.product.my.ProductListDto;
import by.bsuir.exception.ProductIsNotExistException;
import by.bsuir.exception.ProductIsNotLinkedException;
import by.bsuir.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private UserDao userDao;
    private AuctionDao auctionDao;
    private RefDao refDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, UserDao userDao, AuctionDao auctionDao, RefDao refDao){
        this.productDao = productDao;
        this.userDao = userDao;
        this.auctionDao = auctionDao;
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
        ProductListDto productListDto = ProductListDto.of(products);
        List<Integer> idsOnAuction = auctionDao.findByUserActual(user).stream().map(auction -> auction.getProduct().getProductId()).collect(Collectors.toList());
        List<ProductDto> userProducts = productListDto.getProducts();
        userProducts.forEach(productDto -> {
            if(idsOnAuction.contains(productDto.getId())){
                productDto.setIsAuction(true);
            }
        });
        return productListDto;
    }

    @Override
    public ProductListDto getProductsByStatus(Integer userId, String statusName) {
        log.info("Request for getProductsByStatus endpoint");
        ProductStatus productStatus = refDao.findProductStatusByName(statusName.toUpperCase());
        User user = userDao.findById(userId);
        List<Product> products = productDao.findProductsByUserAndStatus(user, productStatus);
        if(!productStatus.getProductStatusId().equals(ProductStatusRef.APPROVED.getId())){
            return ProductListDto.of(products);
        }
        ProductListDto productListDto = ProductListDto.of(products);
        List<ProductDto> productDtos = productListDto.getProducts();
        List<Integer> idsOnAuction = auctionDao.findByUserActual(user).stream().map(auction -> auction.getProduct().getProductId()).collect(Collectors.toList());
        productDtos.forEach(productDto -> {
            if(idsOnAuction.contains(productDto.getId())){
                productDto.setIsAuction(true);
            }
        });
        return productListDto;
    }

    @Override
    public AboutMyProductDto getMyProductInfo(Integer userId, Optional<String> productId) {
        log.info("Request for getMyProductInfo endpoint");
        checkProductId(productId);
        Product product = productDao.findById(Integer.parseInt(productId.get()));
        if(!product.getUser().getUserId().equals(userId)){
            throw new ProductIsNotLinkedException(HttpStatus.NOT_FOUND);
        }
        Optional<Auction> auction = auctionDao.auctionIsExists(product);
        return AboutMyProductDto.of(product, (auction.map(Auction::getAuctionId).orElse(null)));
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
    public CatalogListDto getProductByPage(Integer userId, Integer page, String categoryName) {
        List<Product> productsForPage = null;
        if(categoryName == null){
            productsForPage = productDao.findByPage(page);
        } else {
            Category categoryByName = refDao.findCategoryByName(categoryName);
            productsForPage = productDao.findByPageAndCategory(page, categoryByName);
        }
        if(userId != null){
            User user = userDao.findById(userId);
            List<Integer> userBasket = user.getFavourite().stream().map(Product::getProductId).collect(Collectors.toList());
            CatalogListDto catalogListDto = CatalogListDto.of(productsForPage, userBasket);
            List<Auction> auctions = auctionDao.findAllActive();
            catalogListDto.getProducts().forEach(catalogDto -> {
                if(auctions.stream().map(auction -> auction.getProduct().getProductId()).collect(Collectors.toList())
                        .contains(catalogDto.getId())){
                    catalogDto.setIsAuction(true);
                }
            });
            return catalogListDto;
        }
        return CatalogListDto.of(productsForPage);
    }

    @Override
    public BasketBooleanDto changeBasket(Integer userId, ProductIdDto productIdDto) {
        User user = userDao.findById(userId);
        Product product = productDao.findById(productIdDto.getProductId());
        List<Integer> basket = user.getFavourite().stream().map(Product::getProductId).collect(Collectors.toList());

        if(basket.contains(product.getProductId())){
            user.getFavourite().removeIf(product1 -> product1.getProductId().equals(product.getProductId()));
            userDao.save(user);
            return BasketBooleanDto.builder()
                    .isAdded(false)
                    .build();
        }

        user.getFavourite().add(product);
        userDao.save(user);

        return BasketBooleanDto.builder()
                .isAdded(true)
                .build();
    }

    @Override
    public ProductListDto getProductsInCart(Integer userId) {
        User user = userDao.findById(userId);
        return ProductListDto.of(user.getFavourite());
    }

    @Override
    public AboutCatalogProductDto getProductFromCatalog(Integer userId, Integer productId) {
        Product product = productDao.findById(productId);
        if(!product.getProductStatus().equals(refDao.findProductStatusByName(ProductStatusRef.APPROVED.getName()))){
            throw new ProductIsNotExistException(HttpStatus.NOT_FOUND);
        }
        AboutCatalogProductDto catalogProductDto = AboutCatalogProductDto.of(product);
        if(userId.equals(product.getUser().getUserId())){
            catalogProductDto.setIsMyProduct(true);
        } else {
            catalogProductDto.setIsMyProduct(false);
            Optional<Auction> actualByProduct = auctionDao.findActualByProduct(product);
            actualByProduct.ifPresent(auction -> catalogProductDto.setAuctionId(auction.getAuctionId()));
        }
        return catalogProductDto;
    }

    @Override
    public PagesDto getPagesCount(String categoryName) {
        if(categoryName == null){
            return productDao.getCatalogPages();
        }
        Category categoryByName = refDao.findCategoryByName(categoryName);
        return productDao.getCatalogPagesByCategory(categoryByName);
    }

    @Override
    public CatalogListDto searchProductsByPage(Integer userId, Integer page, String name) {
        List<Product> productsForPage = null;
        if(userId != null){
            User user = userDao.findById(userId);
            List<Integer> userBasket = user.getFavourite().stream().map(Product::getProductId).collect(Collectors.toList());
            return CatalogListDto.of(productsForPage, userBasket);
        }
        return CatalogListDto.of(productDao.searchProductsByPage(page, name));
    }

    @Override
    public PagesDto searchProductsPageCount(String name) {
        return productDao.getSearchPages(name);
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
