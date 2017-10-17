package pl.com.service;

import pl.com.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);

    List<User> findAllUsers();

    User findUserByLogin(String login);
}
