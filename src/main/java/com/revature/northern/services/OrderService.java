package com.revature.northern.services;

import com.revature.northern.daos.OrderDAO;
import com.revature.northern.models.Order;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class OrderService {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";


    //1st, inject OrderDAO here
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    //POST
    public void submitOrder(Order order) {
        orderDAO.save(order);
    }


    //GET
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }


    //DELETE
    public void deleteOrder(String order) {
        orderDAO.delete(order);
    }


    //GET BY ID
    public void getOrderById(String id) {
        orderDAO.getById(id);
    }


    
    /******* Order Validations *********/

    boolean isValidName(String name) {
        if (name.isEmpty()) throw new InvalidUserException(RED + "\nOrder title is required!" + RESET);
        if (name.length() <= 3) throw new InvalidUserException(RED + "\nName must be more than 3 characters!" + RESET);
        return true;
    }

    boolean isValidQuantity(int quantity) {
        if (quantity <= 0) throw new InvalidUserException(RED + "\nQuantity must be greater than 0!!" + RESET);
        return true;
    }


}
