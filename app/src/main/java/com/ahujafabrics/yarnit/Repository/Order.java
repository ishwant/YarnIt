package com.ahujafabrics.yarnit.Repository;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {
    private String orderID;
    private String userID;
    private String creationDate;
    private String productType;
    private List<OrderItem> orderLineItems;
    private OrderStatus orderStatus;


    public enum OrderStatus { Submitted , Delivered};

    public Order(String orderID, String userID, String creationDate,
                 List<OrderItem> orderLineItems, OrderStatus orderStatus, String productType) {
        this.setOrderID(orderID);
        this.setUserID(userID);
        this.setCreationDate(creationDate);
        this.setOrderLineItems(orderLineItems);
        this.orderStatus = orderStatus;
        this.productType = productType;
    }

    public Order(){}

    protected Order(Parcel in) {
        orderID = in.readString();
        userID = in.readString();
        creationDate = in.readString();
        if (in.readByte() == 0x01) {
            orderLineItems = new ArrayList<OrderItem>();
            in.readList(orderLineItems, OrderItem.class.getClassLoader());
        } else {
            orderLineItems = null;
        }
        orderStatus = (OrderStatus) in.readValue(OrderStatus.class.getClassLoader());
        productType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderID);
        dest.writeString(userID);
        dest.writeString(creationDate);
        if (orderLineItems == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(orderLineItems);
        }
        dest.writeValue(orderStatus);
        dest.writeString(productType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
