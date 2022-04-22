package by.bsuir.dao.impl;

import by.bsuir.dao.ProductImageDao;
import by.bsuir.entity.domain.ProductImage;
import by.bsuir.repo.ProductImageRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductImageDaoImpl implements ProductImageDao {
    private ProductImageRepo productImageRepo;

    public ProductImageDaoImpl(ProductImageRepo productImageRepo){
        this.productImageRepo = productImageRepo;
    }

    @Override
    public void saveAll(List<ProductImage> productImages) {
        productImageRepo.saveAll(productImages);
    }
}
