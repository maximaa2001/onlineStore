package by.bsuir.entity.dto.product.catalog;

import by.bsuir.entity.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogDto {
    private Integer id;
    private String name;
    private String category;
    private String city;
    private BigDecimal price;
    private String imageUri;
    private Boolean isAdded;
    private Boolean isAuction;

    /**
     *     Catalog for unknow user
     */

    public static CatalogDto of(Product product){
        return CatalogDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .category(product.getCategory().getCategoryName())
                .city(product.getCity().getCityName())
                .price(product.getPrice())
                .imageUri((product.getImages().isEmpty() ? ""
                        : new ArrayList<>(product.getImages()).get(0).getImageUrl()))
                .isAdded(false)
                .build();
    }

    /**
     *      Catalog for authorized user
     */

    public static CatalogDto of(Product product, Boolean isAdded){
        return CatalogDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .category(product.getCategory().getCategoryName())
                .city(product.getCity().getCityName())
                .price(product.getPrice())
                .imageUri((product.getImages().isEmpty() ? ""
                        : new ArrayList<>(product.getImages()).get(0).getImageUrl()))
                .isAdded(isAdded)
                .build();
    }
}
