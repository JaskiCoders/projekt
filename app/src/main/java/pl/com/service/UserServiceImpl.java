package pl.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import pl.com.model.User;
import pl.com.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id){
        return userRepository.findOne(id);
    }
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserBylogin(String login){
        return userRepository.findByLogin(login);
    }
}
