package com.revature.northern.models;

import com.revature.northern.helpers.OrderStatus;

import java.time.LocalDate;

public class Order {
    private String id;
    private String name;
    private int quantity;
    private LocalDate orderDate;
    private OrderStatus status;
    private String user_id;
    private String book_id;

    public Order() {
    }

    public Order(String id, String name, int quantity, LocalDate orderDate, OrderStatus status, String user_id, String book_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
        this.user_id = user_id;
        this.book_id = book_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", user_id='" + user_id + '\'' +
                ", book_id='" + book_id + '\'' +
                '}';
    }
}
