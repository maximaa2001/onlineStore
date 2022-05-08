package by.bsuir.service;

import by.bsuir.entity.dto.*;
import by.bsuir.entity.dto.user.CreateRatingDto;
import by.bsuir.entity.dto.user.UserInfoDto;

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
    ResultDto changePassword(Integer userId, PasswordDto passwordDto);
    UserInfoDto getUserInfo(Integer userId, Integer aboutUserId);
    ResultDto createRating(Integer userId, CreateRatingDto ratingDto);
}
