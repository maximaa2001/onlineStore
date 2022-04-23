package by.bsuir.entity.dto.product;

import by.bsuir.entity.domain.Product;
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
public class ProductListDto {
    private List<ProductDto> products;

    public static ProductListDto of(List<Product> products){
        return ProductListDto.builder()
                .products(products.stream().map(ProductDto::of).collect(Collectors.toList()))
                .build();
    }
}
