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

    /**
     *     Catalog for unknow user
     */

    public static CatalogListDto of(List<Product> productsForPage){
        return CatalogListDto.builder()
                .products(productsForPage.stream().map(CatalogDto::of).collect(Collectors.toList()))
                .build();
    }

    /**
     *      Catalog for authorized user
     */

    public static CatalogListDto of(List<Product> productsForPage, List<Integer> userBasket){
        List<CatalogDto> catalog = productsForPage.stream().map(product -> {
            if (userBasket.contains(product.getProductId())) {
                return CatalogDto.of(product, true);
            }
            return CatalogDto.of(product, false);
        }).collect(Collectors.toList());
        return CatalogListDto.builder()
                .products(catalog)
                .build();
    }
}
