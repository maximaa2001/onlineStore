package by.bsuir.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordDto {
    private String password;
    private String newPassword;
    private String repeatNewPassword;
}
