package pl.com.service;

import pl.com.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserByLogin(String login);

    User addNewUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    void deleteUserByLogin(String login);
}
