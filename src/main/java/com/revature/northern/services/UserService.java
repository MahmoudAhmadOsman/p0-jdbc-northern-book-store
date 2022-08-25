package com.revature.northern.services;

import com.revature.northern.daos.UserDAO;
import com.revature.northern.models.User;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    //User Registering method
    public void register(User user) {
        userDAO.save(user);
    }


    //Login check in validation that returns User pojo as a return type
    public User login(String username, String password) {
        User user = userDAO.getUserByUsernameAndPassword(username, password);
        if (user == null) throw new InvalidUserException(RED + "\nIncorrect username or password!" + RESET);
        return user;
    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$"))
            throw new InvalidUserException(RED + "\n Username must be is 8 more characters long." + RESET);
        return true;
    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
            throw new InvalidUserException(RED + "\n Password minimum  is eight characters long." + RESET);
        return true;
    }


    //Check for duplicate username based on username that is in the database
    public boolean isDuplicateUsername(String username) {
        if (userDAO.getUsername(username) != null)
            throw new InvalidUserException(RED + "\nSorry, this " + username + " already has been taken!" + RESET);
        return false;
    }


    public boolean isSamePassword(String password, String password2) {
        if (!password.equals(password2)) throw new InvalidUserException(RED + "\nPassword do not match!" + RESET);
        return true;
    }

    public boolean isValidEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            throw new InvalidUserException(RED + "\nInvalid email address. Please enter valid email." + RESET);
        return true;
    }


    //Check user full name - validation
    public boolean isValidName(String name) {
        if (name.isEmpty()) throw new InvalidUserException(RED + "\nName is required!" + RESET);
        if (name.length() < 5) throw new InvalidUserException(RED + "\nName must be more than 5 characters. " + RESET);
        return true;
    }


}