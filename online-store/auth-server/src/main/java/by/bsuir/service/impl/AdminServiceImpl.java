package by.bsuir.service.impl;

import by.bsuir.dao.ProductDao;
import by.bsuir.dao.RefDao;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.dto.PagesDto;
import by.bsuir.entity.dto.ResultDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.ProductStatusDto;
import by.bsuir.entity.dto.product.catalog.CatalogListDto;
import by.bsuir.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private ProductDao productDao;
    private RefDao refDao;

    public AdminServiceImpl(ProductDao productDao, RefDao refDao){
        this.productDao = productDao;
        this.refDao = refDao;
    }

    @Override
    public CatalogListDto findWaitingProducts(Integer page) {
        return CatalogListDto.of(productDao.findWaitingProductByPage(page));
    }

    @Override
    public PagesDto findWaitingPageCount() {
        return productDao.findWaitingProductPageCount();
    }

    @Override
    public ResultDto approveProduct(ProductStatusDto productStatusDto) {
        Product product = productDao.findById(productStatusDto.getProductId());
        product.setProductStatus(refDao.findProductStatusById(productStatusDto.getProductStatusId()));
        Product save = productDao.save(product);
        if(save.getProductStatus().equals(refDao.findProductStatusById(productStatusDto.getProductStatusId()))){
            return ResultDto.builder()
                    .isSuccess(true)
                    .build();
        }
        return ResultDto.builder()
                .isSuccess(false)
                .build();
    }
}
