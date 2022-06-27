package com.codedifferently.addressbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("Validate Password Test - pass")
    public void validatePasswordTest01(){
        User user = new User("Tariq", "Hook", "tariq@codedifferently.com", "CleanCode1234");
        Boolean actual = user.validatePassword("CleanCode1234");
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Validate Password Test - fail")
    public void validatePasswordTest02(){
        User user = new User("Tariq", "Hook", "tariq@codedifferently.com", "CleanCode1234");
        Boolean actual = user.validatePassword("CleanCode1");
        Assertions.assertFalse(actual);
    }
}
