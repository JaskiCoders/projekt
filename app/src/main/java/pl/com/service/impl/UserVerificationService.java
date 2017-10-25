package pl.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.service.IUserService;
import pl.com.model.User;
import pl.com.model.VerificationToken;
import pl.com.repository.UserRepository;
import pl.com.repository.VerificationTokenRepository;

@Component
public class UserVerificationService implements IUserService {

    private UserRepository userRepository;
    private VerificationTokenRepository tokenRepository;

    @Autowired
    public UserVerificationService(
            UserRepository userRepository,
            VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }


    @Override
    public User getUser(String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }


    @Override
    public void createVerificationTokenForUser(User user, String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

}
