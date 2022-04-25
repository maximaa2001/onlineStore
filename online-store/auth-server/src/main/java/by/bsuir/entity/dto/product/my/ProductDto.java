package by.bsuir.entity.dto.product.my;

import by.bsuir.entity.domain.Product;
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
public class ProductDto {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String imageUri;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .price(product.getPrice())
                .imageUri((product.getImages().isEmpty() ? ""
                        : new ArrayList<>(product.getImages()).get(0).getImageUrl()))
                .build();
    }
}