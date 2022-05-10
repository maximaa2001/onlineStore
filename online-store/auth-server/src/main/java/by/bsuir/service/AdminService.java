package by.bsuir.service;

import by.bsuir.entity.dto.PagesDto;
import by.bsuir.entity.dto.ResultDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.ProductStatusDto;
import by.bsuir.entity.dto.product.catalog.CatalogListDto;

public interface AdminService {
    CatalogListDto findWaitingProducts(Integer page);
    PagesDto findWaitingPageCount();
    ResultDto approveProduct(ProductStatusDto productStatusDto);
}
