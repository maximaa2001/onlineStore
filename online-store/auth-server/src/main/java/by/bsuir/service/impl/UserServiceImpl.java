package by.bsuir.service.impl;

import by.bsuir.dao.*;
import by.bsuir.entity.domain.*;
import by.bsuir.entity.domain.key.RatingKey;
import by.bsuir.entity.dto.*;
import by.bsuir.entity.dto.api.UserGoogleResponse;
import by.bsuir.entity.dto.user.CreateRatingDto;
import by.bsuir.entity.dto.user.UserInfoDto;
import by.bsuir.exception.*;
import by.bsuir.service.ApiService;
import by.bsuir.service.AuthService;
import by.bsuir.service.UserService;
import by.bsuir.service.mail.ActivationMail;
import by.bsuir.service.mail.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final UserRatingDao userRatingDao;
    private final RatingDao ratingDao;
    private final ProductDao productDao;
    private final Map<String, String> nonActiveEmails;

    @Autowired
    public UserServiceImpl(AuthService authService, EmailService emailService, CryptoServiceImpl cryptoService,
                           ApiService apiService, UserDao userDao, RefDao refDao, TokenDao tokenDao,
                           UserRatingDao userRatingDao, RatingDao ratingDao, ProductDao productDao){
        this.authService = authService;
        this.emailService = emailService;
        this.cryptoService = cryptoService;
        this.apiService = apiService;
        this.userDao = userDao;
        this.refDao = refDao;
        this.tokenDao = tokenDao;
        this.userRatingDao = userRatingDao;
        this.ratingDao = ratingDao;
        this.productDao = productDao;
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
    public ResultDto logout(String token) {
        User user = userDao.findById(authService.getUserIdByToken(token));
        tokenDao.addTokenToBlackList(Token.builder()
                .token(token)
                .endDate(authService.getExpirationTimeByToken(token))
                .user(user).build());
        return ResultDto.builder()
                .isSuccess(true)
                .build();
    }

    @Transactional
    @Override
    public ResultDto registration(RegDto regDto) {
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
            return ResultDto.builder().isSuccess(true).build();
        }
        return ResultDto.builder().isSuccess(false).build();
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

    @Override
    public PhoneDto getPhone(Integer userId) {
        User user = userDao.findById(userId);
        return PhoneDto.of(user.getUserPhone());
    }

    @Override
    public PhoneDto updatePhoneNumber(Integer userId, PhoneDto phoneDto) {
        User user = userDao.findById(userId);
        user.setUserPhone(phoneDto.getPhoneNumber());
        Optional<User> savedUser = userDao.save(user);
        return PhoneDto.of(savedUser.get().getUserPhone());
    }

    @Override
    public ResultDto changePassword(Integer userId, PasswordDto passwordDto) {
        log.info("Request for changePassword endpoint");
        User user = userDao.findById(userId);
        if(!cryptoService.checkPassword(passwordDto.getPassword(), user.getUserHashPass())){
            throw new IncorrectPasswordException(HttpStatus.BAD_REQUEST);
        }
        if(!passwordDto.getNewPassword().equals(passwordDto.getRepeatNewPassword())){
            throw new IncorrectPasswordException(HttpStatus.BAD_REQUEST);
        }
        String hash = cryptoService.encodePassword(passwordDto.getNewPassword());
        user.setUserHashPass(hash);
        Optional<User> saveUser = userDao.save(user);
        if(saveUser.isPresent()){
            return saveUser.get().getUserHashPass().equals(hash) ? ResultDto.builder().isSuccess(true).build()
                    : ResultDto.builder().isSuccess(false).build();
        }

        return ResultDto.builder().isSuccess(false).build();
    }

    @Override
    public UserInfoDto getUserInfo(Integer userId, Integer aboutUserId) {
        User aboutUser = userDao.findById(aboutUserId);
        List<Integer> ratingForUser = userRatingDao.getRatingForUser(aboutUserId);
        Optional<UserRating> myMark = userRatingDao.findById(RatingKey.builder().setUserId(userId).getUserId(aboutUserId).build());
        Integer mark = 0;
        if(myMark.isPresent()){
            mark = myMark.get().getRating().getRatingNumber();
        }
        return UserInfoDto.of(aboutUser,
                mark, calcRating(ratingDao.findMarksByIds(ratingForUser).stream()
                        .map(Rating::getRatingNumber)
                        .collect(Collectors.toList())));
    }

    @Override
    public ResultDto createRating(Integer userId, CreateRatingDto ratingDto) {
        Rating rating = ratingDao.findByNumber(ratingDto.getRating());
        RatingKey key = RatingKey.builder()
                .setUserId(userId)
                .getUserId(ratingDto.getGetId())
                .build();
        Optional<UserRating> findRating = userRatingDao.findById(key);
        if(findRating.isPresent()){
            throw new RatingAlsoExistsException(HttpStatus.BAD_REQUEST);
        }
        UserRating savedUserRating = userRatingDao.save(UserRating.builder()
                .key(key)
                .rating(rating)
                .build());
        return savedUserRating == null ? ResultDto.builder().isSuccess(false).build()
                : ResultDto.builder().isSuccess(true).build();
    }

    private Float calcRating(List<Integer> ratings){
        if(ratings.size() == 0){
            return 0f;
        }
        Float sum = 0f;
        for (int i = 0; i < ratings.size(); i++) {
            sum += ratings.get(i);
        }
        return sum / ratings.size();
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
