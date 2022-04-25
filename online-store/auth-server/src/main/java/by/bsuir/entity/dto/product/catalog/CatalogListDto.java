package by.bsuir.entity.dto.product.catalog;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.dto.product.my.ProductDto;
import by.bsuir.entity.dto.product.my.ProductListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogListDto {
    private List<CatalogDto> products;

    public static CatalogListDto of(List<Product> products){
        return CatalogListDto.builder()
                .products(products.stream().map(CatalogDto::of).collect(Collectors.toList()))
                .build();
    }
}
