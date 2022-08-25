package com.revature.northern.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainScanner {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        //Generate user library login
        double accountNumber = Math.random() * 25000;
        double accountPinNumber = Math.random() * 5000;


        // cast  double to whole number
        int libNumber = (int) accountNumber;
        int pinNum = (int) accountPinNumber;

        //Scanner - ask the user his/her ATM & PIN-Number
        System.out.println("Welcome to Northern Book Store. Please use below library credentials.");
        System.out.println("Library account # is: " + libNumber + " | " + " Library pin Number is: " + pinNum);
        System.out.println();

        //Ask the user for input, using Scanner class
        Scanner input = new Scanner(System.in);
        System.out.print("Enter you NorthernBookStore number: ");
        int atmNumber = input.nextInt();
        System.out.print("Enter your pin number: ");
        int atmPinNumber = input.nextInt();

        /*Start of while loop 1*/
        try {
            if ((libNumber == atmNumber & pinNum == atmPinNumber)) {
                while (true) {
                    System.out.println();
                    System.out.println(ANSI_GREEN + "1. View all books\n"
                            + "2. Purchase a book\n" +
                            "3. Add a book\n" +
                            "4. View Account Activities\n" +
                            "5. Exit");
                    System.out.println(ANSI_BLUE + "Please choose an option\n ");

                    int choice = input.nextInt();
                    if (choice == 1) {
                        System.out.println("View all books - option: " + choice);
//                        operation.viewBook();
                        System.out.println(" ");

                    } else if (choice == 2) {
                        System.out.println("Purchase a book - option: " + choice);
                        System.out.println(ANSI_RED + "Enter the book title you want to buy! ");
                        double buyABook = input.nextDouble();
//                        operation.buyBook(buyBook);

                    } else if (choice == 3) {
                        System.out.println("Add a book - option: " + choice);
                        System.out.println("Enter the book title");
                        String addBook = input.nextLine();
//                        operation.viewBook();
//                        operation.addBook(addBook);


                    } else if (choice == 4) {
                        System.out.println("===== Account Activities - option =====");
//                        operation.viewMiniStatement();
                    } else if (choice == 5) {
                        System.out.println("============ Thank you ============");
                        System.out.println(ANSI_RED + "See you soon!");
                        System.exit(0);

                    } else {
                        System.out.println(ANSI_RED + "Invalid option! Please choose valid option ");
                    }//end of main if statement


                }//end while

            } else {
                System.out.println(ANSI_RED + "Invalid input!");
                System.out.println(ANSI_RED + "Please, enter valid credentials!");

            }
        } catch (InputMismatchException e) {
            System.out.println("Only digits or numbers are allowed when selecting an option! ");

        }

        input.close();





        /*End of while loop 1*/


    }
}
