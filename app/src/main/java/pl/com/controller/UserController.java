package pl.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.com.model.Role;
import pl.com.model.User;
import pl.com.service.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    @ResponseBody
    public ResponseEntity<User> user(@PathVariable String login) {
        User user = userService.findUserByLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<User> add(@RequestBody User inputUser) {
        inputUser.setRole(Role.USER);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        inputUser.setPassword(passwordEncoder.encode(inputUser.getPassword()));
        userService.addNewUser(inputUser);
        return new ResponseEntity<>(inputUser,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{login}")
    ResponseEntity<String> delete(@PathVariable String login) {
        userService.deleteUserByLogin(login);
        return new ResponseEntity<String>("User deleted!",HttpStatus.OK);
    }
}

