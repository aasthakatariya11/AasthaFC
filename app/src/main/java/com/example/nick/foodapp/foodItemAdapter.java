package com.example.nick.foodapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nick on 12-04-2018.
 */

public class foodItemAdapter extends ArrayAdapter {
    public foodItemAdapter(Activity context, ArrayList al) {
        super(context, 0,al);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null)
        {
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.list_layout,parent,false);
        }

        foodItem fi= (foodItem) getItem(position);
        TextView tv=listItem.findViewById(R.id.textView);
        tv.setText(fi.getTitle().toUpperCase());

        return  listItem;

    }
}
