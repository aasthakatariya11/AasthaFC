package com.example.nick.foodapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aasthakatariya on 4/24/2018.
 */


public class PreviousOrderAdapter extends ArrayAdapter implements View.OnClickListener{
    PreviousOrder order;

    public PreviousOrderAdapter(@NonNull Activity context, ArrayList<PreviousOrder> Prev_order_list ) {
        super(context, 0, Prev_order_list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View listView=convertView;

        if(listView==null)
        {
            listView= LayoutInflater.from(getContext()).inflate(R.layout.previous_list_item,parent,false);
        }
        order= (PreviousOrder) getItem(position);

        Button b1=listView.findViewById(R.id.covers);
        b1.setText("Covers:"+order.getCovers());


        Button b2=listView.findViewById(R.id.table);
        b2.setText("Table No. :"+order.getTable());

        Button b3=listView.findViewById(R.id.date_added);
        b3.setText("Added:"+order.getDate_added());

        TextView tv=listView.findViewById(R.id.Prev_order_id);
        tv.setText("Order ID:" + order.getId());

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        tv.setOnClickListener(this);
        return listView;

    }

    @Override
    public void onClick(View view) {
        //PreviousCommunicator prevcomm= getActivity();
        //prevcomm.PreviousResponse(order);

    }
}
