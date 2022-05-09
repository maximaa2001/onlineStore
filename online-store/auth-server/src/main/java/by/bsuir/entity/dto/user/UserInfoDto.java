package by.bsuir.entity.dto.user;

import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;
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
    private String phoneNumber;
    private Float commonRating;

    public static UserInfoDto of(User user, Integer myMark, Float commonRating){
        return UserInfoDto.builder()
                .id(user.getUserId())
                .email(user.getUserEmail())
                .phoneNumber(user.getUserPhone())
                .myMark(myMark)
                .commonRating(commonRating)
                .build();
    }
}
