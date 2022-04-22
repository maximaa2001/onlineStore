package by.bsuir.service.impl;

import by.bsuir.dao.RefDao;
import by.bsuir.dao.TokenDao;
import by.bsuir.dao.UserDao;
import by.bsuir.entity.domain.Token;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.dto.*;
import by.bsuir.entity.dto.api.UserGoogleResponse;
import by.bsuir.exception.*;
import by.bsuir.service.ApiService;
import by.bsuir.service.AuthService;
import by.bsuir.service.UserService;
import by.bsuir.service.mail.ActivationMail;
import by.bsuir.service.mail.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final AuthService authService;
    private final EmailService emailService;
    private final CryptoServiceImpl cryptoService;
    private final ApiService apiService;
    private final UserDao userDao;
    private final RefDao refDao;
    private final TokenDao tokenDao;
    private final Map<String, String> nonActiveEmails;

    @Autowired
    public UserServiceImpl(AuthService authService, EmailService emailService, CryptoServiceImpl cryptoService,
                           ApiService apiService, UserDao userDao, RefDao refDao, TokenDao tokenDao){
        this.authService = authService;
        this.emailService = emailService;
        this.cryptoService = cryptoService;
        this.apiService = apiService;
        this.userDao = userDao;
        this.refDao = refDao;
        this.tokenDao = tokenDao;
        nonActiveEmails = new HashMap<>();
    }

    @Override
    public JwtDto login(AuthDto authDto) {
        Optional<User> byEmailAndStatus = userDao.findByEmailAndStatus(authDto.getEmail(), refDao.findNonActiveUserStatus());
        if(byEmailAndStatus.isPresent()){
            throw new EmailIsNotActivatedException(HttpStatus.FORBIDDEN);
        }
        User user = userDao.findByEmailAndStatus(authDto.getEmail(), refDao.findActiveUserStatus()).orElseThrow(() -> new UserIsNotRegisteredException(HttpStatus.UNAUTHORIZED));
        return JwtDto.builder()
                .jwt(authService.generateJwt(user))
                .build();
    }

    @Override
    public JwtDto loginByGoogle(GoogleDto googleDto){
        UserGoogleResponse userGoogleResponse = apiService.checkGoogleAccessToken(googleDto.getAccessToken());
        JwtDto jwtDto = new JwtDto();
        if(userGoogleResponse.getVerifiedEmail()){
            userDao.findByEmailAndStatus(userGoogleResponse.getEmail(), refDao.findGoogleActiveStatus())
                    .ifPresentOrElse(user -> {
                        if(userGoogleResponse.getUserId().equals(user.getUserGoogleId())){
                            jwtDto.setJwt(authService.generateJwt(user));
                        }
                    }, () -> jwtDto.setJwt(authService.generateJwt(registerGoogleUser(userGoogleResponse))));
        }
        return jwtDto;
    }

    @Override
    public JwtDto auth(String token) {
        log.info("Request for auth endpoint");
        User user = userDao.findById(authService.getUserIdByToken(token));
        return JwtDto.builder()
                .jwt(authService.generateJwt(user))
                .build();
    }

    @Override
    public void logout(String token) {
        User user = userDao.findById(authService.getUserIdByToken(token));
        tokenDao.addTokenToBlackList(Token.builder()
                .token(token)
                .endDate(authService.getExpirationTimeByToken(token))
                .user(user).build());
    }

    @Transactional
    @Override
    public RegResultDto registration(RegDto regDto) {
        log.info("Request for registration endpoint");
        userDao.findByEmailAndStatus(regDto.getEmail(), refDao.findNonActiveUserStatus()).ifPresent((user) -> {
            throw new SuchEmailAlsoRegistredException(HttpStatus.CONFLICT);
        });

        userDao.findByEmailAndStatus(regDto.getEmail(), refDao.findActiveUserStatus()).ifPresent((user) -> {
            throw new SuchEmailAlsoRegistredException(HttpStatus.CONFLICT);
        });

        if(!regDto.getPassword().equals(regDto.getRepeatPassword())){
            throw new RepeatPasswordIsNotSameException(HttpStatus.FORBIDDEN);
        }

        User defaultUser = createNonActiveUser(regDto);
        Optional<User> user = userDao.save(defaultUser);
        if(user.isPresent()){
            String key = cryptoService.createActivationKey(user.get().getUserEmail());
            nonActiveEmails.put(key, user.get().getUserEmail());
            emailService.sendHtml(new ActivationMail(user.get().getUserEmail(), key));
            return RegResultDto.builder().isSuccess(true).build();
        }
        return RegResultDto.builder().isSuccess(false).build();
    }

    @Override
    public void activateEmail(String key) {
        if(!nonActiveEmails.containsKey(key) || !nonActiveEmails.get(key).equals(cryptoService.getEmailFromActivationKey(key))){
            throw new ActivationKeyIsNotExist(HttpStatus.FORBIDDEN);
        }
        String email = nonActiveEmails.get(key);
        Optional<User> user = userDao.findByEmailAndStatus(email, refDao.findNonActiveUserStatus());
        user.ifPresent(value -> {
            value.setUserStatus(refDao.findActiveUserStatus());
            userDao.save(value);
            nonActiveEmails.remove(key);
        });
    }

    private User createNonActiveUser(RegDto regDto){
        return User.builder()
                .userEmail(regDto.getEmail())
                .userHashPass(cryptoService.encodePassword(regDto.getPassword()))
                .actualDate(LocalDateTime.now())
                .userPhone(regDto.getPhoneNumber())
                .userStatus(refDao.findNonActiveUserStatus())
                .userRole(refDao.findUserRole())
                .build();
    }

    private User registerGoogleUser(UserGoogleResponse userGoogleResponse){
        Optional<User> save = userDao.save(User.builder()
                .userEmail(userGoogleResponse.getEmail())
                .actualDate(LocalDateTime.now())
                .userGoogleId(userGoogleResponse.getUserId())
                .userRole(refDao.findUserRole())
                .userStatus(refDao.findGoogleActiveStatus())
                .build());
        return save.orElse(null);
    }
}
