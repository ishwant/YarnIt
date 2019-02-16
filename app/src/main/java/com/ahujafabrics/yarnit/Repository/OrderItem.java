package com.ahujafabrics.yarnit.Repository;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable {
    private Integer orderLineItemID;
    private String shadeId;
    private Integer quantity;

    public OrderItem(Integer orderLineItemID, String shadeId, Integer quantity) {
        this.setOrderLineItemID(orderLineItemID);
        this.setShadeId(shadeId);
        this.setQuantity(quantity);
    }

    public OrderItem(){}

    protected OrderItem(Parcel in) {
        orderLineItemID = in.readByte() == 0x00 ? null : in.readInt();
        shadeId = in.readString();
        quantity = in.readByte() == 0x00 ? null : in.readInt();
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    public Integer getOrderLineItemID() {
        return orderLineItemID;
    }

    public void setOrderLineItemID(Integer orderLineItemID) {
        this.orderLineItemID = orderLineItemID;
    }

    public String getShadeId() {
        return shadeId;
    }

    public void setShadeId(String shadeId) {
        this.shadeId = shadeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (orderLineItemID == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(orderLineItemID);
        }

        dest.writeString(shadeId);
        if (quantity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(quantity);
        }
    }
}
