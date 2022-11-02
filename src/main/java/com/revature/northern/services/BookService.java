package com.revature.northern.services;

import com.revature.northern.daos.BookDAO;
import com.revature.northern.models.Book;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class BookService {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    //1. inject bookDAO first with parametrized constructor
    private final BookDAO bookDAO;


    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    //Add a new book
    public void addNewBook(Book book) {
        bookDAO.save(book);
    }


    //Get all the books method
    public List<Book> getAllBooks() {
        return bookDAO.getAll();
    }

    //Delete a book
    public void deleteABook(String id) {
        bookDAO.delete(id);
    }


    //Update a book
    public void updatABook(Book book) {
        bookDAO.update(book);
    }

    public Book getABookById(String id) {
        return bookDAO.getById(id);
    }


    /******* BookService Validation Methods *********/

    public boolean isValidISBN(String isbn) {
        if (isbn.isEmpty()) throw new InvalidUserException(RED + "\nISBN is required!" + RESET);
        if (isbn.length() < 5) throw new InvalidUserException(RED + "\nISBN must be more than 5 characters. " + RESET);
        return true;
    }

    //Validate title
    public boolean isValidTitle(String title) {
        if (title.isEmpty()) throw new InvalidUserException(RED + "\nBook title is required!" + RESET);
        if (title.length() < 3)
            throw new InvalidUserException(RED + "\nTitle must be more than 3 characters. " + RESET);
        return true;
    }


    //Validate author
    public boolean isValidAuthorName(String author) {
        if (author.isEmpty()) throw new InvalidUserException(RED + "\nAuthor name is required!" + RESET);
        if (author.length() < 3)
            throw new InvalidUserException(RED + "\nAuthor name must be more than 3 characters." + RESET);
        return true;
    }

    //Validate published year
    public boolean isValidPublishedDate(String publishedDate) {
        if (publishedDate.isEmpty())
            throw new InvalidUserException(RED + "\nBook published year or date  is required!" + RESET);
        if (publishedDate.length() < 3)
            throw new InvalidUserException(RED + "\nPublished year  must be 4 digits, ex. [2020]. " + RESET);
        return true;
    }

    //Validate price
    public boolean isValidPrice(double price) {
        if (price < 0) throw new InvalidUserException(RED + "\nBook price is required!" + RESET);
        if (price < 2) throw new InvalidUserException(RED + "\nPrice  must be more than 2 digits. " + RESET);
        return true;
    }

    //Validate publisher
    public boolean isValidPublisher(String publisher) {
        if (publisher.isEmpty()) throw new InvalidUserException(RED + "\nBook publisher name is required!" + RESET);
        if (publisher.length() < 3)
            throw new InvalidUserException(RED + "\nPublisher name must be more than 3 characters!. " + RESET);
        return true;
    }


}


