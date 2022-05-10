package by.bsuir.rest;

import by.bsuir.entity.dto.PagesDto;
import by.bsuir.entity.dto.ResultDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.entity.dto.product.ProductStatusDto;
import by.bsuir.entity.dto.product.catalog.CatalogListDto;
import by.bsuir.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static by.bsuir.constant.ApiPath.*;

@RestController
@Slf4j
public class AdminRest {
    private AdminService adminService;

    public AdminRest(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping(GET_WAITING_PRODUCTS)
    public CatalogListDto findWaitingApproveProduct(@RequestParam(name = "page") Integer page){
        return adminService.findWaitingProducts(page);
    }

    @GetMapping(GET_WAITING_PAGE_COUNT)
    public PagesDto findWaitingApproveProduct(){
        return adminService.findWaitingPageCount();
    }

    @PostMapping(APPROVE_PRODUCT)
    public ResultDto approveProduct(@RequestBody ProductStatusDto productStatusDto){
        return adminService.approveProduct(productStatusDto);
    }

}
