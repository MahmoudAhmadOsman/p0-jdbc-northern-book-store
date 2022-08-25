package com.revature.northern.ui;

import com.revature.northern.daos.UserDAO;
import com.revature.northern.models.User;
import com.revature.northern.services.UserService;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;

import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class LoginMenu implements IMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    //1st,inject UserService class
    private final UserService userService;
    //2nd, create a constructor for it
    public LoginMenu(UserService userService) {
        this.userService = userService;

    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to Northern Book Store");

                System.out.println("[1] Login");
                System.out.println("[2] Signup");
                System.out.println("[x] Exit!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                /*================== USER LOGIN ================*/

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        userService.register(user); //if option 2 show signup menu options
                        //new MainMenu(user,  new Book(), new BookService(new BookDAO())).start();
                        new MainMenu(user).start();

                        break;
                    case "x":
                        System.out.println("\nSee you soon!");
                        break exit;
                    default:
                        System.out.println(RED + "\nInvalid input!. Please try again." + RESET);
                        break;
                }
            }
        }
    }


    //Login - if user is ADMIN, redirect to admin page
    private void login() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println(GREEN + "\nLogging in..." + RESET);

        exit:
        {
            while (true) {
                System.out.print("\nEnter your username: ");
                username = scan.nextLine().toLowerCase();

                System.out.print("\nEnter your password: ");
                password = scan.nextLine();

                try {
                    //if user is ADMIN, based on his username, then redirect to the ADMIN menu
                    //else another page
                    User user = userService.login(username, password);
                    if (user.getRole().equals("ADMIN")) new AdminMenu(user, new UserService(new UserDAO())).start();
                        //  else new MainMenu(user, new UserService(new UserDAO())).start();
                    else new MainMenu(user).start(); //DEFAULT user - correct on 8/19

                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }

    private User signup() {
        String username = "";
        String password = "";
        String password2 = "";
        String name = "";
        String email = "";

        User user;
        Scanner scan = new Scanner(System.in);

        System.out.println(GREEN + "\nCreating an account..." + RESET);

        exit:
        {
            while (true) {
                usernameExit:
                {
                    while (true) {
                        System.out.print("\nEnter a username: ");
                        username = scan.nextLine().toLowerCase();

                        try {
                            userService.isValidUsername(username);
                            userService.isDuplicateUsername(username);
                            break usernameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                passwordExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter your password: ");
                            password = scan.nextLine();

                            userService.isValidPassword(password);

                            System.out.print("\nRe-enter your password: ");
                            password2 = scan.nextLine();

                            userService.isSamePassword(password, password2);
                            break passwordExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                /*nameExit  */
                nameExit:

                {
                    while (true) {
                        try {
                            System.out.print("\nEnter your full name: ");
                            name = scan.nextLine();
                            userService.isValidName(name);
                            break nameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                /*emailExit  */
                emailExit:

                {
                    while (true) {
                        try {
                            System.out.print("\nEnter your email address: ");
                            email = scan.nextLine().toLowerCase();
                            userService.isValidEmail(email);
                            break emailExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                confirmExit:
                {
                    while (true) {
                        System.out.println(RED + "\nIs this correct? (y/n):" + RESET);

                        System.out.println("Username: " + username + "\nPassword: " + password + "\nName: " + name + "\nEmail: " + email);
                        System.out.print("\nEnter: ");

                        switch (scan.nextLine().toLowerCase()) {
                            case "y":
                                user = new User(UUID.randomUUID().toString(), username, password, name, email);
                                return user;

                            case "n":
                                System.out.println("\nRestarting...");
                                break confirmExit;
                            default:
                                System.out.println("\nInvalid input!");
                                break;
                        }
                    }
                }


            }
        }
    }

}
