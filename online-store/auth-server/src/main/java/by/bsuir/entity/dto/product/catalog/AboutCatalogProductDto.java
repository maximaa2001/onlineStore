package by.bsuir.entity.dto.product.catalog;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AboutCatalogProductDto {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long actualDate;
    private String category;
    private String city;
    private List<String> imageUris;
    private Integer userId;
    private String email;

    public static AboutCatalogProductDto of(Product product) {
        return AboutCatalogProductDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .actualDate(Timestamp.valueOf(product.getActualDate()).getTime())
                .category(product.getCategory().getCategoryName())
                .city(product.getCity().getCityName())
                .imageUris(product.getImages().stream().map(ProductImage::getImageUrl).collect(Collectors.toList()))
                .userId(product.getUser().getUserId())
                .email(product.getUser().getUserEmail())
                .build();
    }
}
