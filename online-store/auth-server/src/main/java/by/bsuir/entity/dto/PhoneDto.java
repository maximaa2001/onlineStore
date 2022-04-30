package by.bsuir.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PhoneDto {
    private String phoneNumber;

    public static PhoneDto of(String phone){
        return PhoneDto.builder()
                .phoneNumber(phone)
                .build();
    }
}
