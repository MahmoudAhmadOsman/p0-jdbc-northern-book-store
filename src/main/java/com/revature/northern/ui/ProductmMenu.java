package com.revature.northern.ui;

import com.revature.northern.daos.BookDAO;
import com.revature.northern.models.Book;
import com.revature.northern.models.User;
import com.revature.northern.services.BookService;
import com.revature.northern.services.UserService;

import java.io.IOException;
import java.util.Scanner;

public class ProductmMenu implements IMenu {
    //1st. Inject model here
    //private final User user; // user model
    private final Book book;

    private final BookDAO bookDAO = new BookDAO();

    public ProductmMenu(Book book) {
        this.book = book;
    }

    @Override
    public void start() throws IOException {
        System.out.println("\n=====** PRODUCTS PAGE **===== ");
//        System.out.println("\nWelcome " + user.getName() + ",");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        /*Start of client while loop*/
        while (true) {
            System.out.println("** Product Pages - Dashboard **");
            System.out.println("\n1. Add a book"
                    + "\n2. Show all books"
                    + "\n3. Get a book by its ISBN number "
                    + "\n4. Delete a book"
                    + "\n5. Update a book"
                    + "\n6. Exit");
            System.out.println("Please enter an option");


            int choice = sc.nextInt();
            switch (choice) {

                case 1:
                    System.out.println("Add a book");
                    System.out.println("Enter book ISBN number");
                    String isbn=sc.next();

                    System.out.println("Enter book title or name");
                    String name=sc.next();

                    System.out.println("Enter author name");
                    String author=sc.next();

                    System.out.println("Enter published date");
                    String publishedDate=sc.next();
                    System.out.println("Enter the price of the book");
                    double price=sc.nextDouble();
                    System.out.println("Enter the book publisher");
                    String publisher=sc.next();
                    bookDAO.save(book); // save the new input

                    break;

                case 2:
                    System.out.println("Show all books");
                    //call getAll method
                    break;
                case 3:
                    System.out.println("Get a book by its ISBN number");
                    //call getById method
                    break;
                case 4:
                    System.out.println("Delete a book");
                    //call delete method
                    break;
                case 5:
                    System.out.println("Update a book");
                    break;
                case 6:
                    System.out.println("Exit ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please choice an option");
            }


        }
        /*End of client while loop*/


    }
}
