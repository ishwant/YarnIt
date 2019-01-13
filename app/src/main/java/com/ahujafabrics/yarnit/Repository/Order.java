package com.ahujafabrics.yarnit.Repository;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderID;
    private String userID;
    private Date creationDate;
    private List<OrderItem> orderLineItems;

    public Order(String orderID, String userID, Date creationDate, List<OrderItem> orderLineItems) {
        this.setOrderID(orderID);
        this.setUserID(userID);
        this.setCreationDate(creationDate);
        this.setOrderLineItems(orderLineItems);
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
