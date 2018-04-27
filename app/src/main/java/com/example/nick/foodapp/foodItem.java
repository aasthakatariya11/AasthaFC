package com.example.nick.foodapp;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nick on 11-04-2018.
 */

public class foodItem {

    private String title;
    private String slug;
    boolean active;
    Date timestamp;
    private int id;
    private String products;


    public foodItem(String title, String products)
    {
        this.title=title;
        this.products=products;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String  products) {
        this.products = products;
    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }
}
