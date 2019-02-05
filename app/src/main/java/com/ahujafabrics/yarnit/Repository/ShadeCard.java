package com.ahujafabrics.yarnit.Repository;

public class ShadeCard {
    private String shade;
    private String qty;
    private int position;

    public ShadeCard(String shade, int position){
        this.shade = shade;
        this.qty="";
        this.position = position;
    }
    public String getShade(){ return shade; }
    public String getQty(){ return qty; }

    public void setQty(String qty) {
        this.qty = qty;
    }
    public void setShade(String shade) {
        this.shade = shade;
    }

    public int getPosition(){ return position; }
    public void setPosition(int position){ this.position = position;}
}