package com.revature.northern.ui;

import com.revature.northern.daos.BookDAO;
import com.revature.northern.daos.UserDAO;
import com.revature.northern.models.Book;
import com.revature.northern.models.User;
import com.revature.northern.services.BookService;
import com.revature.northern.services.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminMenu implements IMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    //1st. bring User model here
    private final User user;
    //2nd. inject UserService here
    private final UserService userService;


    //3rd. create a constructor for them
    private BookService bookService = new BookService(new BookDAO());

    public AdminMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {

        //Create an exit
        exit:
        {
            System.out.println("Hello " + user.getName() + "," + " welcome to Admin Dashboard!");


            Scanner sc = new Scanner(System.in);
            /*Start of client while loop*/
            while (true) {
                System.out.println();
                System.out.println(GREEN + "** Book Menu - Dashboard **");
                System.out.println("\n1. Add a book" +
                        "\n2. Show all books" +
                        "\n3. Search a book by its ISBN number "
                        + "\n4. Delete a book" +
                        "\n5. Update a book" +
                        "\n6. Exit" + RESET);
                System.out.println("Please enter an option");


                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Add a new book");

                            System.out.print("Enter book ISBN number: ");
                            String isbn = sc.next();
                            bookService.isValidISBN(isbn); //validate isbn

                            System.out.print("Enter book title or name: ");
                            String title = sc.next();
                            bookService.isValidTitle(title); //validate title

                            System.out.print("Enter author name: ");
                            String author = sc.next().trim();
                            bookService.isValidAuthorName(author); //validate author


                            System.out.print("Enter the published year: ");
                            String publishedDate = sc.next();
                            bookService.isValidPublishedDate(publishedDate); //validate published date

                            System.out.print("Enter price of the book: ");
                            double price = sc.nextDouble();
                            bookService.isValidPrice(price); //validate price

                            System.out.print("Enter the book publisher's name: ");
                            String publisher = sc.next();
                            bookService.isValidPublisher(publisher); //validate publisher


                            //1st. make a Book constructor that takes all the args that the constructor inside the Book model has
                            //2nd. create UUID like this: UUID.randomUUID().toString() and then pass all the Book constructor args in order
                            //like we did for User constructor

                            Book book = new Book(UUID.randomUUID().toString(), isbn, title, author, publishedDate, price, publisher);
                            bookService.addNewBook(book); //call the BookService and access through it the registerNewBook that is in the BookService class
                            System.out.println(GREEN + "A new book has been added successfully!!" + RESET);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }


                        break;

                    case 2:

                        System.out.println(GREEN + "List of all available books" + RESET);
                        //call getAll method
                        //  bookService.getAll(book);
                        List<Book> allBooks = bookService.getAllBooks();
//                        for (Book bk: allBooks) {
//                            System.out.println(bk.toString());
//                        }
                        System.out.printf("\n----------------------------------------------------------------------------------------------------------------------------------------%n");
                        System.out.format(CYAN + "%-25s  %-25s  %-25s  %-25s   %-25s  %-25s %-25s  %n", "ID", "ISBN", "TITLE", "AUTHOR NAME", "PUBLISH YEAR", "PRICE", "PUBLISHER NAME" + RESET);
                        System.out.printf("\n----------------------------------------------------------------------------------------------------------------------------------------%n");
                        allBooks.stream().map(aBook ->

                                        String.format("%-25s  %-25s  %-25s  %-25s   %-25s  %-25s %-25s   %n ",
                                                aBook.getId(),
                                                aBook.getIsbn(),
                                                aBook.getTitle(),
                                                aBook.getAuthor(),
                                                aBook.getPublishedDate(),
                                                "$" + aBook.getPrice(),
                                                aBook.getPublisher())

                        ).forEach(System.out::println);
                        System.out.println();

                        break;
                    case 3:
                        System.out.print("Enter book ISBN number: ");//call getById method
                        String bookIsbn = sc.next();
                        bookService.getABookById(bookIsbn); // get book by its isbn number
                        break;
                    case 4:
                        System.out.println("Delete a book");
                        System.out.print("Delete a book by its isbn number: ");
                        String id = sc.next();
                        bookService.deleteABook(id); //call delete method


                        break;
                    case 5:
                        System.out.println("Update a book");
                        System.out.print("Enter a book by its isbn number: ");
                        String isbNumber = sc.next();
                        Book getBook = bookService.getABookById(isbNumber);

                        if (getBook.getIsbn() == "") {
                            System.out.println("Book dose not exist ");
                        } else {
                            //  TODO just update isbn
                            System.out.println("Enter the new ISBN: ");
                            String newISBN = sc.next();
                            Book newBook = new Book(getBook.getId(),
                                    newISBN, getBook.getTitle(),
                                    getBook.getAuthor(),
                                    getBook.getPublishedDate(),
                                    getBook.getPrice(),
                                    getBook.getPublisher());

                            bookService.updatABook(newBook);
                        }
                        System.out.println(getBook.getTitle());
                        //bookService.updatABook(book);
                        break;
                    case 6:
                        System.out.println(RED + "6 " + RESET);
                        new LoginMenu(new UserService(new UserDAO())).start(); // if exited, redirect to log in
                        break;
                    default:
                        System.out.println("Invalid option! Please choice an option");
                }


            }
            /*End of client while loop*/


        }

    }
}
