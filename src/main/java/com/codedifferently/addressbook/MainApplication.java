package com.codedifferently.addressbook;

import com.codedifferently.addressbook.exceptions.UserCredentialsInvalidException;
import com.codedifferently.addressbook.exceptions.UserDoesNotExistException;
import com.codedifferently.addressbook.exceptions.UserExistException;

import java.util.*;

public class MainApplication {

    private Map<String, User> users;
    private User currentUser;
    private static final Scanner scanner = new Scanner(System.in);

    public MainApplication(){
        users = new TreeMap<>();
    }

    public MainApplication(Map<String, User> users){
        this.users = users;
    }

    public User signUp(String firstName, String lastname, String email, String password) throws UserExistException {
        if(users.containsKey(email))
            throw new UserExistException("User with email exists: " + email);
        User user = new User(firstName,lastname,email,password);
        users.put(email, user);
        return user;
    }

    public User signIn(String email, String password) throws UserDoesNotExistException,UserCredentialsInvalidException {
        if(!users.containsKey(email))
            throw new UserDoesNotExistException("User with email does not exist: " + email);
        User user = users.get(email);
        if(!user.validatePassword(password))
            throw new UserCredentialsInvalidException("User password is incorrect");
        return user;
    }

    public void run(){
        Boolean flag = true;
        while (flag){
            if(currentUser == null)
                flag = welcomeScreen();
            else{
                userOptionsScreen();
            }
        }
    }

    private Boolean userOptionsScreen(){
        String msg = String.format("Welcome %s, here are your options", currentUser.getFirstName());
        msg += "\nPress 3 to log out";
        System.out.println(msg);
        String selection = scanner.next();
        switch (selection){
            case "3":
                currentUser = null;
                return false;
            default:
                return true;
        }

    }
    private Boolean welcomeScreen(){
        Boolean flag = true;
        String output = "Welcome to Address Book. Please select from the following options."
                +"\nPress 1 to login"
                +"\nPress 2 to create new account"
                +"\nPress 3 to exit";
        System.out.println(output);
        String selection = scanner.next();
        switch (selection){
            case "1":
                attemptSignIn();
                break;
            case "2":
                attemptSignUp();
                break;
            case "3":
            default:
                System.out.println("Good Bye");
                flag = false;
        }
        return flag;
    }

    private void attemptSignIn(){
        try {
            System.out.println("Please enter valid email:");
            String email = scanner.next();
            System.out.println("Please enter valid password");
            String password = scanner.next();
            currentUser = signIn(email, password);
        } catch (UserCredentialsInvalidException e) {
            System.out.println("You entered the wrong password");
        } catch (UserDoesNotExistException e) {
            System.out.println("The password was incorrect");
        }

    }

    private void attemptSignUp(){
        try {
            System.out.println("Please enter first name");
            String firstName = scanner.next();
            System.out.println("Please enter last name");
            String lastName = scanner.next();
            System.out.println("Please enter email");
            String email = scanner.next();
            System.out.println("Please enter password");
            String password = scanner.next();
            currentUser = signUp(firstName, lastName, email, password);
        } catch (UserExistException e) {
            System.out.println("User with email all ready exist");
        }
    }

    public static void main(String[] args) {
        MainApplication mainApplication = new MainApplication();
        mainApplication.run();
    }
}
