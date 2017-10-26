package pl.com.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.WebRequest;
import pl.com.model.User;
import pl.com.model.VerificationToken;
import pl.com.service.impl.UserVerificationService;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mvc;
    @Mock
    private UserVerificationService service;
    @Mock
    private WebRequest request;
    @InjectMocks
    private RegistrationController registrationController;
    @Mock
    private VerificationToken vtoken;

    @Test
    public void shouldReturnBadToken(){
        when(service.getVerificationToken("test")).thenReturn(null);
        when(request.getLocale()).thenReturn(Locale.ENGLISH);
        ResponseEntity<String> response = registrationController.confirmRegistration(request,"test");
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }
    @Test
    public void shouldReturnBadReqest(){
        when(service.getVerificationToken("test")).thenReturn(vtoken);
        when(request.getLocale()).thenReturn(Locale.ENGLISH);
        when(vtoken.getExpiryDate()).thenReturn(new Date());
        ResponseEntity<String> response = registrationController.confirmRegistration(request,"test");
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }
    @Test
    @Rollback
    public void shouldReturnSucces(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        Date testDate = cal.getTime();
        when(service.getVerificationToken("test")).thenReturn(vtoken);
        when(request.getLocale()).thenReturn(Locale.ENGLISH);
        when(vtoken.getExpiryDate()).thenReturn(testDate);
        when(vtoken.getUser()).thenReturn(new User());
        ResponseEntity<String> response = registrationController.confirmRegistration(request,"test");
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }
}
