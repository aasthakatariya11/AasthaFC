package com.example.nick.foodapp;

/**
 * Created by aasthakatariya on 4/24/2018.
 */

public class PreviousOrder {

    int id;
    String date_added;
    int table;
    int covers;
    Double total;
    String items_json;
    String comment;

    public PreviousOrder(int id,int table,int covers,String date_added,Double total,String items_json,String comment)
    {
        this.id=id;
        this.table=table;
        this.covers=covers;
        this.date_added=date_added;
        this.total=total;
        this.items_json=items_json;
        this.comment=comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public int getTable() {
        return table;
    }

    public int getCovers() {
        return covers;
    }

    public void setCovers(int covers) {
        this.covers = covers;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public Double getTotal() {
        return total;
    }

    public String getItems_json() {
        return items_json;
    }

    public void setItems_json(String items_json) {
        this.items_json = items_json;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
