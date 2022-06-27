package com.codedifferently.addressbook;

import com.codedifferently.addressbook.exceptions.UserDoesNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

public class AddressBookImplTest {
    private Map<String, Contact> contacts;
    private AddressBook addressBook;
    @BeforeEach
    public void setUp(){
        contacts = new TreeMap<>();
        String email1 = "wu@tang.com";
        contacts.put(email1, new Contact("Wu", "Tang", email1));

        String email2 = "sho@lin.com";
        contacts.put(email2, new Contact("Sho", "Lin", email2));

        addressBook = new AddressBookImpl(contacts);
    }

    @Test
    @DisplayName("Get All Contacts")
    public void getAllContactsTest01(){
        String expected = "Lin, Sho, sho@lin.com\n"
               +"Tang, Wu, wu@tang.com\n";
        String actual = addressBook.getAllContacts();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get By Email - success")
    public void getByEmailTest01() throws UserDoesNotExistException {
        String expected = "Tang, Wu, wu@tang.com";
        String actual = addressBook.getByEmail("wu@tang.com");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get By Email - fail")
    public void getByEmailTest02() {
        Assertions.assertThrows(UserDoesNotExistException.class, ()-> {
            addressBook.getByEmail("boo@tang.com");
        });
    }

    @Test
    @DisplayName("Get By lastname")
    public void getByLastNameTest01(){
        addressBook.saveContact(new Contact("Boo", "Tang", "boo@tang.com"));
        String expected = "Tang, Boo, boo@tang.com\n" +
                "Tang, Wu, wu@tang.com\n";
        String actual = addressBook.getByLastName("Tang");
        Assertions.assertEquals(expected, actual);
    }

}
