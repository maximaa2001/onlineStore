package by.bsuir.security;

import by.bsuir.dao.RefDao;
import by.bsuir.dao.UserDao;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.domain.UserRole;
import by.bsuir.repo.UserRepo;
import by.bsuir.service.AuthService;
import by.bsuir.service.UserService;
import by.bsuir.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityUserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RefDao refDao;
    private final JwtService jwtService;
    private final UserDao userDao;

    @Autowired
    public SecurityUserService(UserRepo userRepo, RefDao refDao, JwtService jwtService, UserDao userDao){
        this.userRepo = userRepo;
        this.refDao = refDao;
        this.jwtService = jwtService;
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Integer userIdByToken = Integer.parseInt(jwtService.getUserId(token));
        Optional<User> optional = userDao.finOpt(userIdByToken);
        if(optional.isEmpty()){
            throw new UsernameNotFoundException("Account is not exists");
        }
        User user = optional.get();
        return SecurityUser
                .builder()
                .email(user.getUserEmail())
                .password(user.getUserHashPass())
                .phone(user.getUserPhone())
                .userStatus(user.getUserStatus())
                .authorities(createAuthority(user.getUserRole()))
                .build();
    }

    private Collection<? extends GrantedAuthority> createAuthority(UserRole userRole){
        return List.of(new SimpleGrantedAuthority(userRole.getRoleName()));
    }
}
