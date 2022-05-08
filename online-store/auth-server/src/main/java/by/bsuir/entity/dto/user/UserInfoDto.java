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
    private Integer id;
    private String email;
    private Integer myMark;
    private Float commonRating;
    private List<ProductDto> products;

    public static UserInfoDto of(Integer id, String email, Integer myMark,  Float commonRating, List<Product> products){
        return UserInfoDto.builder()
                .id(id)
                .email(email)
                .myMark(myMark)
                .commonRating(commonRating)
                .products(products.stream().map(ProductDto::of).collect(Collectors.toList()))
                .build();
    }
}
