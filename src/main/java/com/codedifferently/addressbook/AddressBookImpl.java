package com.codedifferently.addressbook;

import com.codedifferently.addressbook.exceptions.UserDoesNotExistException;

import java.util.Map;
import java.util.TreeMap;

public class AddressBookImpl implements AddressBook{
    private Map<String,Contact> contacts;

    public AddressBookImpl() {
        this.contacts = new TreeMap<>();
    }

    public AddressBookImpl(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void saveContact(Contact contact) {
        contacts.put(contact.getEmail(), contact);
    }

    @Override
    public String getAllContacts() {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, Contact> contactSet: contacts.entrySet()){
            Contact contact = contactSet.getValue();
            String output = String.format("%s, %s, %s", contact.getLastName(), contact.getFirstName(), contact.getEmail());
            builder.append(output +"\n");
        }
        return builder.toString();
    }

    @Override
    public String getByEmail(String email) throws UserDoesNotExistException{
        if(!contacts.containsKey(email))
            throw new UserDoesNotExistException("User with email does not exist");
        Contact contact = contacts.get(email);
        return String.format("%s, %s, %s", contact.getLastName(), contact.getFirstName(), contact.getEmail(), contact.getId());
    }

    @Override
    public String getByLastName(String lastName) {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, Contact> contactSet: contacts.entrySet()){
            Contact contact = contactSet.getValue();
            if(contact.getLastName().equals(lastName)) {
                String output = String.format("%s, %s, %s", contact.getLastName(), contact.getFirstName(), contact.getEmail());
                builder.append(output + "\n");
            }
        }
        return builder.toString();
    }

    @Override
    public Boolean delete(String email) {
        if(contacts.containsKey(email)) {
            contacts.remove(email);
            return true;
        }
        return false;
    }
}
