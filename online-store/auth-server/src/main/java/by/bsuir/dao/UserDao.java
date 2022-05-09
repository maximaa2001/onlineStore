package by.bsuir.dao;

import by.bsuir.entity.domain.User;
import by.bsuir.entity.domain.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndStatus(String email, UserStatus userStatus);
    User findById(Integer id);
    Optional<User> finOpt(Integer id);
    Optional<User> save(User user);
}
