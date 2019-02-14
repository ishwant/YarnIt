package com.ahujafabrics.yarnit.Repository;

import android.os.Parcel;
import android.os.Parcelable;

public class CartLineItem implements Parcelable {
    private String shade;
    private int qty;

    public CartLineItem(String shade, Integer qty){
        this.shade = shade;
        this.qty = qty;
    }

    protected CartLineItem(Parcel in) {
        shade = in.readString();
        qty = in.readInt();
    }

    public static final Creator<CartLineItem> CREATOR = new Creator<CartLineItem>() {
        @Override
        public CartLineItem createFromParcel(Parcel in) {
            return new CartLineItem(in);
        }

        @Override
        public CartLineItem[] newArray(int size) {
            return new CartLineItem[size];
        }
    };

    public String getShade() {
        return shade;
    }

    public void setShade(String shade) {
        this.shade = shade;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shade);
        dest.writeInt(qty);
    }
}