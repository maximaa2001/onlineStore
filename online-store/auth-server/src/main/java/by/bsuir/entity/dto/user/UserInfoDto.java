package by.bsuir.entity.dto.user;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.dto.product.my.ProductDto;
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
public class UserInfoDto {
    private String email;
    private Float commonRating;
    private List<ProductDto> products;

    public static UserInfoDto of(String email, Float commonRating, List<Product> products){
        return UserInfoDto.builder()
                .email(email)
                .commonRating(commonRating)
                .products(products.stream().map(ProductDto::of).collect(Collectors.toList()))
                .build();
    }
}
