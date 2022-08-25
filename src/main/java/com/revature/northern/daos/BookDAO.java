package com.revature.northern.daos;

import com.revature.northern.models.Book;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements CrudDAO<Book> {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";


    @Override
    public void save(Book obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO books (id, isbn, title, author, publisheddate, price, publisher) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getIsbn());
            ps.setString(3, obj.getTitle());
            ps.setString(4, obj.getAuthor());
            ps.setString(5, obj.getPublishedDate());
            ps.setDouble(6, obj.getPrice());
            ps.setString(7, obj.getPublisher());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring to save book into the database.");
        }
    }

    @Override
    public void update(Book obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
//            System.out.println("BOOK" + obj);
//            System.out.println("BOOK-ISBN " + obj.getIsbn());
            // we need to use the getId and run another query to get the old isbn
            PreparedStatement oldBookISBN = con.prepareStatement("SELECT * FROM books where id =?");
            oldBookISBN.setString(1, obj.getId());
            ResultSet rs = oldBookISBN.executeQuery();

            if (rs.next()) {
//                System.out.println("OLD ISBN #: " + rs.getString("isbn"));
                PreparedStatement ps = con.prepareStatement("UPDATE books SET isbn = ?, title = ?, author = ?, publisheddate = ?, price = ?, publisher = ? WHERE isbn = ?");
                ps.setString(1, obj.getIsbn());
                ps.setString(2, obj.getTitle());
                ps.setString(3, obj.getAuthor());
                ps.setString(4, obj.getPublishedDate());
                ps.setDouble(5, obj.getPrice());
                ps.setString(6, obj.getPublisher());
                ps.setString(7, rs.getString("isbn"));
                ps.executeUpdate();
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("No book  with this isbn # found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring to save book to the database.");
        }

    }

    @Override
    public void delete(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            // check if id exit in the database
            PreparedStatement id_exist = con.prepareStatement("SELECT * FROM books WHERE id = ?");
            id_exist.setString(1, id);
            ResultSet exists = id_exist.executeQuery();
            if (exists.next()) {
                PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE id = ?");
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println(RED + "Deleted successfully!" + RESET);
            } else {
                System.out.println(RED + "No id found!. Please try again." + RESET);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring delete a book.");
        }
    }

    @Override
    public Book getById(String isbn) {

        Book getByItsId = new Book();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            // check if id exit in the database
            PreparedStatement id_exist = con.prepareStatement("SELECT * FROM books WHERE isbn = ?");
            id_exist.setString(1, isbn);

            ResultSet rs = id_exist.executeQuery();

            if (rs.next()) {
                System.out.println(BLUE + "Book with ISBN: " +
                        rs.getString("isbn") +
                        ", titled " + "[" + rs.getString("title") + "]" +
                        " is available!" + RESET
                );


//                getByItsId = new Book(rs.getString("id"),
//                        rs.getString("isbn"),
//                        rs.getString("title"),
//                        rs.getString("author"),
//                        rs.getString("publishedDate"),
//                        rs.getDouble("price"),
//                        rs.getString("publisher"));

                getByItsId.setId(rs.getString("id"));
                getByItsId.setIsbn(rs.getString("isbn"));
                getByItsId.setTitle(rs.getString("title"));
                getByItsId.setAuthor(rs.getString("author"));
                getByItsId.setPublishedDate(rs.getString("publishedDate"));
                getByItsId.setPrice(rs.getDouble("price"));
                getByItsId.setPublisher(rs.getString("publisher"));
            } else System.out.println(RED + "Book with this ISBN is not found!!!" + RESET); // if ISBN is not found

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring delete a book.");
        }

        return getByItsId;
    }

    @Override
    public List<Book> getAll() {
        //user List class to get the list
        List<Book> bookList = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM books");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("id"),
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publishedDate"),
                        rs.getDouble("price"),
                        rs.getString("publisher"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to query data from the database.");
        }


        return bookList; // return books
    }


}