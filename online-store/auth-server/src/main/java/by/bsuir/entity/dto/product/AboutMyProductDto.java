package by.bsuir.entity.dto.product;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AboutMyProductDto {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long actualDate;
    private String category;
    private String city;
    private String productStatus;
    private List<String> imageUris;

    public static AboutMyProductDto of(Product product){
        return AboutMyProductDto.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .actualDate(Timestamp.valueOf(product.getActualDate()).getTime())
                .category(product.getCategory().getCategoryName())
                .city(product.getCity().getCityName())
                .productStatus(product.getProductStatus().getProductStatusName())
                .imageUris(product.getImages().stream().map(ProductImage::getImageUrl).collect(Collectors.toList()))
                .build();

    }
}
