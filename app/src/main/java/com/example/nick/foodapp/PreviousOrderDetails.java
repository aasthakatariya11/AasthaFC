package com.example.nick.foodapp;

/**
 * Created by aasthakatariya on 4/29/2018.
 */

public class PreviousOrderDetails {
    int quantity;
    Double price;
    String title;


    public PreviousOrderDetails(int quantity,Double price, String title){
        this.quantity=quantity;
        this.price=price;
        this.title=title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}
