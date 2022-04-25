package by.bsuir.entity.dto.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketBooleanDto {
    private Boolean isAdded;

    public static BasketBooleanDto of(Boolean isAdded){
        return BasketBooleanDto.builder()
                .isAdded(isAdded)
                .build();
    }
}
