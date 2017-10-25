package pl.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import pl.com.emailVerify.OnRegistrationCompleteEvent;
import pl.com.model.Role;
import pl.com.model.User;
import pl.com.service.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    UserController(UserServiceImpl userService,ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{login}")
    @ResponseBody
    public ResponseEntity<User> user(@PathVariable String login) {
        User user = userService.findUserByLogin(login);
        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<User> add(@RequestBody User inputUser) {
        inputUser.setRole(Role.ROLE_USER);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        inputUser.setPassword(passwordEncoder.encode(inputUser.getPassword()));
        userService.addNewUser(inputUser);
        return ResponseEntity.ok().body(inputUser);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{login}")
    ResponseEntity<String> delete(@PathVariable String login) {
        userService.deleteUserByLogin(login);
        return ResponseEntity.ok().body("User deleted!");
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<String> registerUserAccount(@RequestBody User account, WebRequest request) {
        User registered = userService.addNewUser(account);
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
            registered.setEnabled(true);
            userService.updateUser(registered);
        } catch (Exception me) {
            return  ResponseEntity.status(500).body(me.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("Success!");
    }
}

