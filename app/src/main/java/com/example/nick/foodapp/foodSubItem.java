package com.example.nick.foodapp;

/**
 * Created by Nick on 13-04-2018.
 */

public class foodSubItem {

    String title;
    int id;
    double price;
    double tax;
    boolean customized;
    int defaultno;
    String customization_type;
    int quantity=0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public foodSubItem(String title,double price)
    {
        this.title=title;
        this.price=price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
