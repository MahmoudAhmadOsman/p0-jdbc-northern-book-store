package com.revature.northern.daos;

import com.revature.northern.helpers.OrderStatus;
import com.revature.northern.models.Order;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements CrudDAO<Order> {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";


    @Override
    public void save(Order obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, name, quantity, orderDate, status, user_id, book_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setInt(3, obj.getQuantity());
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.setString(5, OrderStatus.ORDER_CONFIRMED.toString());
            ps.setString(6, obj.getUser_id());
            ps.setString(7, obj.getBook_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring to save order into the database!!.");
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            // check if id exit in the database
            PreparedStatement id_exist = con.prepareStatement("SELECT * FROM orders WHERE id = ?");
            id_exist.setString(1, id);
            ResultSet exists = id_exist.executeQuery();
            if (exists.next()) {
                PreparedStatement ps = con.prepareStatement("DELETE FROM orders WHERE id = ?");
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println(RED + "Order has been cancelled successfully!" + RESET);
            } else {
                System.out.println(RED + "No id found!. Please try again." + RESET);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring cancel/delete an order.");
        }

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {

        List<Order> orderList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                Order order = new Order(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("orderDate").toLocalDate(),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("user_id"),
                        rs.getString("book_id"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to query data from the orders table.");
        }


        return orderList;
    }
}
