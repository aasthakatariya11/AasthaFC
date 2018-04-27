package com.example.nick.foodapp;

/**
 * Created by Nick on 11-04-2018.
 */

public interface Communicator {

    void response(foodItem fi);

    void updateOrderList(foodSubItem fsi);

    void removeItemOrder(foodSubItem fsi);

    void finalOrder(String tableNo,int coversPeople);

    void cancelButton(foodSubItem fs);


    /**
     * Created by aasthakatariya on 4/26/2018.
     */

    interface PrevCommunicator
    {
        void PrevResponse();
    }
}
