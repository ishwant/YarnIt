package com.ahujafabrics.yarnit.Repository;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CartItem implements Parcelable {
    private String productType;
    private ArrayList<CartLineItem> cartLineItems;

    public CartItem(String productType, ArrayList<CartLineItem> cartLineItems){
        this.productType = productType;
        this.cartLineItems = cartLineItems;
    }

    protected CartItem(Parcel in) {
        productType = in.readString();
        if (in.readByte() == 0x01) {
            cartLineItems = new ArrayList<CartLineItem>();
            in.readList(cartLineItems, CartLineItem.class.getClassLoader());
        } else {
            cartLineItems = null;
        }
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public ArrayList<CartLineItem> getCartLineItems() {
        return cartLineItems;
    }

    public void setCartLineItems(ArrayList<CartLineItem> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }

    public void clearCart(){
        cartLineItems.clear();
    }

    public boolean deleteCartItem(String shade){
        //Add functionality here
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType);
        if (cartLineItems == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(cartLineItems);
        }
    }
}
