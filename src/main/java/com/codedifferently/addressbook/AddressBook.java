package com.codedifferently.addressbook;

import com.codedifferently.addressbook.exceptions.UserDoesNotExistException;

public interface AddressBook {
    void saveContact(Contact contact);
    String getAllContacts();
    String getByEmail(String email) throws UserDoesNotExistException;
    String getByLastName(String lastName);
    Boolean delete(String email);
}
