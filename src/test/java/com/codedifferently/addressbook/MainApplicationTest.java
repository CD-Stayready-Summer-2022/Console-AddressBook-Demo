package com.codedifferently.addressbook;

import com.codedifferently.addressbook.exceptions.UserCredentialsInvalidException;
import com.codedifferently.addressbook.exceptions.UserDoesNotExistException;
import com.codedifferently.addressbook.exceptions.UserExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MainApplicationTest {

    private Map<String, User> users;
    private String email;
    private String password;
    private User user;

    @BeforeEach
    public void setup(){
        users = new HashMap<>();
        email = "tariq@codedifferently.com";
        password = "CleanCode1234";
        user = new User("Tariq", "Hook", email, password);
        users.put(email, user);
    }

    @Test
    @DisplayName("Sign-in test - successful")
    public void signInTest01() throws UserDoesNotExistException,UserCredentialsInvalidException {
        //given
        MainApplication mainApplication = new MainApplication(users);
        // when
        User actualUser = mainApplication.signIn(email, password);
        //then
        Assertions.assertEquals(user, actualUser);
    }

    @Test
    @DisplayName("Sign-in test - fail user not found")
    public void signInTest02() {
        Assertions.assertThrows(UserDoesNotExistException.class, ()->{
            MainApplication mainApplication = new MainApplication();
            mainApplication.signIn(email, password);
        });
    }

    @Test
    @DisplayName("Sign-in test - fail password incorrect")
    public void signInTest03() {
        Assertions.assertThrows(UserCredentialsInvalidException.class, ()->{
            MainApplication mainApplication = new MainApplication(users);
            mainApplication.signIn(email, "password");
        });
    }

    @Test
    @DisplayName("SignUp test - success")
    public void signUpTest01() throws UserExistException {
        MainApplication mainApplication = new MainApplication();
        String expectedEmail = email;
        User user1 = mainApplication.signUp("Tariq", "Hook", email, password);
        String actualEmail = user1.getEmail();
        Assertions.assertEquals(expectedEmail, actualEmail);
    }
}
