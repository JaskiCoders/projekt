package pl.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import pl.com.model.User;
import pl.com.repository.UserRepository;
import pl.com.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User addNewUser(User user){return userRepository.save(user);}

    public User updateUser(User user){return userRepository.save(user);}

    public void deleteUser(User user){userRepository.delete(user);}

    public void deleteUserById(Long id){userRepository.delete(id);}
}
