package pl.com.service;

import pl.com.model.User;
import pl.com.model.VerificationToken;

public interface IUserService {

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);



}