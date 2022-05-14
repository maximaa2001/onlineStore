package by.bsuir.entity.dto.product.my;

import by.bsuir.entity.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ProductDto {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String imageUri;
    private Boolean isAuction;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .price(product.getPrice())
                .imageUri((product.getImages().isEmpty() ? ""
                        : new ArrayList<>(product.getImages()).get(0).getImageUrl()))
                .build();
    }

//    /**
//     * for display auction in my products
//     */
//    public static ProductDto of(Product product, Boolean isAuction) {
//        return ProductDto.builder()
//                .id(product.getProductId())
//                .name(product.getProductName())
//                .price(product.getPrice())
//                .imageUri((product.getImages().isEmpty() ? ""
//                        : new ArrayList<>(product.getImages()).get(0).getImageUrl()))
//                .isAuction(isAuction)
//                .build();
//    }
}
