package com.ahujafabrics.yarnit.Repository;

import java.util.List;

public class Order {
    private String orderID;
    private String userID;
    private long creationDate;
    private List<OrderItem> orderLineItems;
    private OrderStatus orderStatus;


    public enum OrderStatus { Submitted , Delivered};

    public Order(String orderID, String userID, long creationDate, List<OrderItem> orderLineItems, OrderStatus orderStatus) {
        this.setOrderID(orderID);
        this.setUserID(userID);
        this.setCreationDate(creationDate);
        this.setOrderLineItems(orderLineItems);
        this.orderStatus = orderStatus;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
