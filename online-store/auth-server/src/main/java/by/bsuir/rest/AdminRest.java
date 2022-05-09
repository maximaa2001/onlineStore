package by.bsuir.rest;

import by.bsuir.entity.dto.product.catalog.CatalogListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AdminRest {

    @GetMapping("/admin")
    public CatalogListDto findWaitingApproveProduct(){
        System.out.println("ok");
        return null;
    }

}
