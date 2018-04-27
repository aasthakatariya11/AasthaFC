package com.example.nick.foodapp;

import java.util.ArrayList;

/**
 * Created by Nick on 22-04-2018.
 */

public class finalOrder {

    ArrayList<foodSubItem> finalOrderList;
    String tableNo;
    int coversPeople;

    public finalOrder()
    {
        this.finalOrderList=finalOrderList;

    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public int getCoversPeople() {
        return coversPeople;
    }

    public void setCoversPeople(int coversPeople) {
        this.coversPeople = coversPeople;
    }

    public ArrayList<foodSubItem> getFinalOrderList() {
        return finalOrderList;
    }

    public void setFinalOrderList(ArrayList<foodSubItem> finalOrderList) {
        this.finalOrderList = finalOrderList;
    }
}
