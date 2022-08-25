package com.revature.northern.ui;

import com.revature.northern.daos.BookDAO;
import com.revature.northern.daos.OrderDAO;
import com.revature.northern.daos.UserDAO;
import com.revature.northern.helpers.OrderStatus;
import com.revature.northern.models.Book;
import com.revature.northern.models.Order;
import com.revature.northern.models.User;
import com.revature.northern.services.BookService;
import com.revature.northern.services.OrderService;
import com.revature.northern.services.UserService;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu implements IMenu {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    //Inject: 1. User |  2. UserService 3. | BookService 4.  | OrderService
    private final User user;

    //1st. first, inject the BookService - that BookDAD as a param
    private BookService bookService = new BookService(new BookDAO());
    private UserService userService = new UserService(new UserDAO());

    //2nd, inject order service
    private OrderService orderService = new OrderService(new OrderDAO());

    public MainMenu(User user) {
        this.user = user;

    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        //Create an exit
        exit:
        {
            System.out.println(CYAN + "Hello " + user.getName() + "," + " welcome to Northern Book Store!" + RESET);


            Scanner sc = new Scanner(System.in);
            /*Start of client while loop*/
            while (true) {
                System.out.println();
                System.out.println(GREEN + "** Book Menu - Dashboard **");

                System.out.println("\n1. Show all books" + "\n2. Buy a book - submit an order" + "\n3. Search a book by its ISBN number " + "\n4. View all orders" + "\n5. Exit" + RESET);
                System.out.println("Please enter an option");


                int choice = sc.nextInt();
                switch (choice) {

                    case 1:

                        System.out.println(GREEN + "List of all available books" + RESET);
                        //call getAll method
                        //  orderService.getAll(book);
                        List<Book> allBooks = bookService.getAllBooks();
//                        for (Book bookList: allBooks) {
//                            System.out.println(bookList.toString());
//                        }
                        System.out.printf("\n----------------------------------------------------------------------------------------------------------------------------------------------------%n");
                        System.out.format(CYAN + "%-10s | %-10s | %-10s | %-10s  | %-7s | %-5s  | %n", "ISBN", "TITLE", "AUTHOR NAME", "PUBLISH YEAR", "PRICE", "PUBLISHER NAME" + RESET);
                        System.out.printf("\n----------------------------------------------------------------------------------------------------------------------------------------------------%n");

                        allBooks.stream().map(aBook ->
                                //System.out.format
                                String.format("%-10s | %-10s | %-10s | %-10s  | %-7s | %-5s | %n %n",
                                        aBook.getIsbn(),
                                        aBook.getTitle(),
                                        aBook.getAuthor(),
                                        aBook.getPublishedDate(),
                                        "$" + aBook.getPrice(),
                                        aBook.getPublisher())

                        ).forEach(System.out::println);
                        System.out.println();

                        break;
                    case 2:
                        System.out.println(GREEN + "Please provide book details" + RESET);
                        //buy a book using isbn - number
                        System.out.println("Enter book ISBN number: ");
                        String isbn = sc.next();

                        if (isbn != null) {
                            //1st, get the isbn first
                            Book getBookISBN = bookService.getABookById(isbn); //use the getABookById method

                            if (getBookISBN.getIsbn() != null) {//1st check if the book isbn - number exist
                                System.out.println("book: " + getBookISBN + " " + getBookISBN.getIsbn());

                                System.out.println("Enter the quantity: ");
                                int quantity = sc.nextInt();
                                Order order = new Order();
                                order.setId(UUID.randomUUID().toString());
                                order.setName(getBookISBN.getTitle());
                                order.setQuantity(quantity);
                                order.setStatus(OrderStatus.ORDER_CONFIRMED); //order status
                                order.setUser_id(this.user.getId());
                                order.setBook_id(getBookISBN.getId());
                                //orderService.submitOrder(order); //call the submitOrder method
                                // System.out.println(GREEN + "A new order has been submitted successfully!!" + RESET);


                                /*Start of confirmExit*/
                                confirmExit:
                                { // added on 8/22
                                    while (true) {
                                        System.out.println(RED + "\nIs this correct? (y/n):" + RESET);

                                        System.out.println(GREEN + "\nOrder ID: " + order.getId() + "\nBook ISBN: " + getBookISBN.getIsbn() + "\nBook Name: " + order.getName() + "\nOrder Quantity: " + order.getQuantity() + "\nOrder Date: " + order.getOrderDate().now() + "\nUser ID: " + order.getUser_id() + "\nBook ID: " + order.getBook_id() + RESET);

                                        System.out.print("\nEnter: "); //enter for an answer

                                        switch (scan.nextLine().toLowerCase()) {
                                            case "y":
                                                orderService.submitOrder(order);
                                                System.out.println(GREEN + "A new order has been submitted successfully!!" + RESET);
                                            case "n":
                                                System.out.println("\nRedirecting...");
                                                break confirmExit;
                                            default:
                                                System.out.println("\nInvalid input!");
                                                break;
                                        }
                                    }
                                }

                                /*End of confirmExit*/


                            } else {
                                System.out.println(RED + "No ISBN found!!!" + RESET);
                            }
                        } else {

                            break;
                        }
                        break;

                    case 3:
                        System.out.print("Enter book ISBN number: ");
                        String isbNumber = sc.next();
                        bookService.getABookById(isbNumber); //get a book by its isbn
                        //Book seachBook = bookService.getABookById(isbNumber); //get a book by its isbn
                        break;
                    case 4:

                        System.out.println();
                        List<Order> allOrders = orderService.getAllOrders();

                        System.out.println("**** YOUR RECENT ORDERS ****");
                        System.out.printf("\n------------------------------------------------------------------------------------------------------------------------------------------------------%n");
                        System.out.format(CYAN + "%-25s | %-15s | %-15s | %-15s  | %-15s | %-15s | %-15s   %n", "ORDER ID", "NAME", "QUANTITY", "STATUS", "ORDER DATE", "USER ID", "BOOK ID" + RESET);
                        System.out.printf("\n------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                        allOrders.stream().map(aOrder ->
                                //System.out.println()
                                //String.format() is similar to System.out.println
                                String.format("%-25s | %-15s | %-15s | %-15s  | %-15s | %-15s | %-15s  %n",
                                        //aOrder.getId(),
                                        aOrder.getId(),
                                        aOrder.getName(),
                                        aOrder.getQuantity(),
                                        aOrder.getStatus(),
                                        aOrder.getOrderDate(),
                                        aOrder.getUser_id(),
                                        aOrder.getBook_id())

                        ).forEach(System.out::println);
                        System.out.println();

                        System.out.println("**** MANAGER YOUR ORDERS ****");
                        /*Start of cancelOrderExit*/
                        cancelOrderExit:
                        { // added on 8/23
                            while (true) {
                                System.out.print(RED + "\n1. Cancel Order");
                                System.out.print("\n2. Exit!" + RESET);
                                System.out.print(GREEN + "\nEnter: " + RESET);

                                switch (scan.nextLine().toLowerCase()) {
                                    case "1":
                                        System.out.print(RED + "Enter order ID #: " + RESET);
                                        String order = sc.next();
                                        orderService.deleteOrder(order);
                                    case "2":
                                        System.out.println("\n< back to main options...");
                                        break cancelOrderExit;
                                    default:
                                        System.out.println(RED + "\nInvalid input!" + RESET);
                                        break;
                                }
                            }
                        }
                        break;
                    /*End of cancelOrderExit*/


                    case 5:
                        System.out.println(RED + "5 " + RESET);
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








