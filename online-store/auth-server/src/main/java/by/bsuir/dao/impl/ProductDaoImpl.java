package by.bsuir.dao.impl;

import by.bsuir.constant.ref.ProductStatusRef;
import by.bsuir.dao.ProductDao;
import by.bsuir.dao.RefDao;
import by.bsuir.entity.domain.Category;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductStatus;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.dto.PagesDto;
import by.bsuir.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private ProductRepo productRepo;
    private RefDao refDao;

    @Value("${product.catalog.size}")
    private Integer pageSize;

    @Autowired
    public ProductDaoImpl(ProductRepo productRepo, RefDao refDao){
        this.productRepo = productRepo;
        this.refDao = refDao;
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product findById(Integer productId) {
        return productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Cannot find product with id " + productId));
    }

    @Override
    public List<Product> findAllProductsByUser(User user) {
        return productRepo.findByUser(user);
    }

    @Override
    public List<Product> findProductsByUserAndStatus(User user, ProductStatus productStatus) {
        return productRepo.findByUserAndProductStatus(user, productStatus);
    }

    @Override
    public List<Product> findByPage(Integer page) {
        Pageable pageRequest = PageRequest.of(page, pageSize);
        return productRepo.findByProductStatus(refDao.findProductStatusByName(ProductStatusRef.APPROVED.getName()),
                pageRequest).getContent();
    }

    @Override
    public PagesDto getCatalogPages() {
        Pageable pageRequest = PageRequest.of(0, pageSize);
        return PagesDto.of(productRepo.findByProductStatus(refDao.findProductStatusByName(ProductStatusRef.APPROVED.getName()),
                pageRequest).getTotalPages());
    }

    @Override
    public List<Product> findByPageAndCategory(Integer page, Category category) {
        Pageable pageRequest = PageRequest.of(page, pageSize);
        return productRepo.findByProductStatusAndCategory(refDao.findApprovedStatus(), category, pageRequest).getContent();
    }

    @Override
    public PagesDto getCatalogPagesByCategory(Category category) {
        Pageable pageRequest = PageRequest.of(0, pageSize);
        return PagesDto.of(productRepo.findByProductStatusAndCategory(refDao.findApprovedStatus(), category,
                pageRequest).getTotalPages());
    }
}
