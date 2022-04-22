package by.bsuir.entity.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdDto {
    private Integer productId;

    public static ProductIdDto of(Integer id){
        return ProductIdDto.builder()
                .productId(id)
                .build();
    }
}
