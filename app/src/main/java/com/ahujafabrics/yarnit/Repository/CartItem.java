package com.ahujafabrics.yarnit.Repository;

import java.util.Map;

public class CartItem {
    private String productType;
    private Map<String,Integer> cartLineItems;

    public CartItem(String productType, Map cartLineItems){
        this.productType = productType;
        this.cartLineItems = cartLineItems;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Map<String, Integer> getCartLineItems() {
        return cartLineItems;
    }

    public void setCartLineItems(Map<String, Integer> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }

    public void clearCart(){
        cartLineItems.clear();
    }

    public boolean deleteCartItem(String shade){
        if(cartLineItems.remove(shade)!= null)
            return true;

        return false;
    }

}
