package com.codedifferently.addressbook;

import java.util.Objects;

public class User extends Contact{
    private String password;
    private AddressBook addressBook;

    public User(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email);
        this.password = password;
    }

    public Boolean validatePassword(String password){
        return this.password.equals(password);
    }

    public Boolean loadAddressBook(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password, addressBook);
    }
}
