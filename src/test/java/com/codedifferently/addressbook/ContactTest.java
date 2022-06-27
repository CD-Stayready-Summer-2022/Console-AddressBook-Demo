package com.codedifferently.addressbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    @DisplayName("Constructor test")
    public void constructorTest01(){
        // Given
        Contact contact = new Contact("Tariq", "Hook", "tariq@codedifferently.com");
        String expected = String.format("%d %s %s %s", 1, "Tariq", "Hook", "tariq@codedifferently.com");
        // When
        String actual = contact.toString();

        // Then
        Assertions.assertEquals(expected, actual);
    }
}
