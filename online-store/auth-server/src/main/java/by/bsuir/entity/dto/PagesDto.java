package by.bsuir.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagesDto {
    private Integer count;

    public static PagesDto of(Integer count){
        return PagesDto.builder()
                .count(count)
                .build();
    }
}
