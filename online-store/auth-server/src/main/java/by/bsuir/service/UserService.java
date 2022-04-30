package by.bsuir.service;

import by.bsuir.entity.dto.*;

import java.io.IOException;

public interface UserService {
    JwtDto login(AuthDto authDto);
    JwtDto loginByGoogle(GoogleDto authDto) throws IOException;
    JwtDto auth(String token);
    void logout(String token);
    ResultDto registration(RegDto regDto);
    void activateEmail(String key);
    PhoneDto getPhone(Integer userId);
    PhoneDto updatePhoneNumber(Integer userId, PhoneDto phoneDto);
}
