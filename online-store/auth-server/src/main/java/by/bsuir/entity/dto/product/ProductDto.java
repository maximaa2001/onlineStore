package by.bsuir.entity.dto.product;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String imageUri;

    public static ProductDto of(Product product){
        return ProductDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .price(product.getPrice())
                .imageUri((product.getImages().isEmpty() ? ""
                        : new ArrayList<>(product.getImages()).get(0).getImageUrl()))
                .build();
    }
}
