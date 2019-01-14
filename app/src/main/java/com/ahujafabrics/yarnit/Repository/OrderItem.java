package com.ahujafabrics.yarnit.Repository;

public class OrderItem {
    private String orderLineItemID;
    private String productType;
    private String shadeId;
    private Integer quantity;

    public OrderItem(String orderLineItemID, String productType, String shadeId, Integer quantity) {
        this.setOrderLineItemID(orderLineItemID);
        this.setProductType(productType);
        this.setShadeId(shadeId);
        this.setQuantity(quantity);
    }

    public String getOrderLineItemID() {
        return orderLineItemID;
    }

    public void setOrderLineItemID(String orderLineItemID) {
        this.orderLineItemID = orderLineItemID;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
}