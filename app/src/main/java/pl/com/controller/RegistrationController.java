package pl.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import pl.com.model.User;
import pl.com.model.VerificationToken;
import pl.com.service.IUserService;
import pl.com.service.impl.UserVerificationService;

import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping("/api/registration")
public class RegistrationController {

    private UserVerificationService service;
    private MessageSource messages;
    @Autowired
    public RegistrationController(UserVerificationService service, MessageSource messages){
        this.service = service;
        this.messages = messages;
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public ResponseEntity<String> confirmRegistration
            (WebRequest request, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            return  ResponseEntity.badRequest().body("Bad token!");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return  ResponseEntity.badRequest().body("Token expired!");
        }

        user.setEnabled(true);
        service.saveRegisteredUser(user);
        return  ResponseEntity.ok().body("Account registered!");
    }
}
