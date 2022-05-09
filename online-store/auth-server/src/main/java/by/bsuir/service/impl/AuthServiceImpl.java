package by.bsuir.service.impl;

import by.bsuir.dao.RefDao;
import by.bsuir.dao.TokenDao;
import by.bsuir.entity.domain.Token;
import by.bsuir.entity.domain.User;
import by.bsuir.security.SecurityUser;
import by.bsuir.security.SecurityUserService;
import by.bsuir.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final TokenDao tokenDao;
    private final RefDao refDao;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.expiration}")
    private String expiration;

    @Autowired
    public AuthServiceImpl(JwtService jwtService, TokenDao tokenDao, UserDetailsService userDetailsService,
                           RefDao refDao){
        this.jwtService = jwtService;
        this.tokenDao = tokenDao;
        this.refDao = refDao;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String generateJwt(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put("role", user.getUserRole().getRoleName());
        claims.put("email", user.getUserEmail());
        claims.put("google_id", user.getUserGoogleId());
        return jwtService.generateToken(user.getUserId(), claims, Long.parseLong(expiration));
    }

    @Override
    public boolean isValidToken(String token) {
        Optional<Token> jwt = tokenDao.findByToken(token);
        if(jwt.isPresent()){
            return false;
        }
        return jwtService.isValidTokenByExpiration(token);
    }

    @Override
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(token);
        SecurityUser securityUser = (SecurityUser) userDetails;
        if(securityUser.getUserStatus().equals(refDao.findActiveUserStatus()) || securityUser.getUserStatus().equals(refDao.findGoogleActiveStatus())){
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                    userDetails.getPassword(), userDetails.getAuthorities());
        }
        return null;
    }

    @Override
    public Integer getUserIdByToken(String token) {
        return Integer.parseInt(jwtService.getUserId(token));
    }


    @Override
    public Date getExpirationTimeByToken(String token) {
        return jwtService.getExpirationDate(token);
    }
}
