package by.bsuir.dao.impl;

import by.bsuir.dao.ProductDao;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;
import by.bsuir.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private ProductRepo productRepo;

    @Autowired
    public ProductDaoImpl(ProductRepo productRepo){
        this.productRepo = productRepo;
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
    public List<Product> findAllProducts(User user) {
        return productRepo.findByUser(user);
    }
}
