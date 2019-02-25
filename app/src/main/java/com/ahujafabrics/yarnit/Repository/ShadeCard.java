package com.ahujafabrics.yarnit.Repository;

public class ShadeCard {
    private String shade;
    private String qty;

    public ShadeCard(String shade){
        this.shade = shade;
        this.qty="";
    }

    public ShadeCard(String shade, String qty){
        this.shade = shade;
        this.qty=qty;
    }
    public String getShade(){ return shade; }
    public String getQty(){ return qty; }

    public void setQty(String qty) {
        this.qty = qty;
    }
    public void setShade(String shade) {
        this.shade = shade;
    }

}