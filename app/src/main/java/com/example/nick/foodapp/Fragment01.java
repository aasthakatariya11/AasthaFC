package com.example.nick.foodapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Fragment01 extends Fragment {

    ArrayList<foodSubItem> orderArrayList;
    Button orderButton;
    ListView lv;

    public Fragment01() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderArrayList=new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_fragment01,container,false);
        return  rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv=getActivity().findViewById(R.id.listView1);
        orderButton=getActivity().findViewById(R.id.orderButton);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!orderArrayList.isEmpty())
                {

                    orderFood orderDialog=new orderFood();

                    orderDialog.show(getActivity().getFragmentManager(),"orderDialog");

                }

            }
        });


    }

    public void orderList(foodSubItem fsi)
    {
        int index=-1;
        for(int i=0;i<orderArrayList.size();i++)
        {
            if(fsi.getTitle().equals(orderArrayList.get(i).getTitle()))
            {
                index=i;
            }
        }
        Log.v("fragment3", String.valueOf(index));

        if(index==-1)
        {
            orderArrayList.add(fsi);
        }
        else
        {
            orderArrayList.get(index).setQuantity(fsi.getQuantity());
        }
        orderItemAdapter adapter=new orderItemAdapter(getActivity(),orderArrayList);
        lv.setAdapter(adapter);
    }


    public void removeItemOrder(foodSubItem fsi)
    {
        for(int i=0;i<orderArrayList.size();i++)
        {
            if(fsi.getTitle().equals(orderArrayList.get(i).getTitle()))
            {
                orderArrayList.remove(i);
                break;
            }
        }
      //  orderArrayList.remove(fsi);
        orderItemAdapter adapter=new orderItemAdapter(getActivity(),orderArrayList);
        lv.setAdapter(adapter);
    }


    public class orderItemAdapter extends ArrayAdapter
    {

        public orderItemAdapter(Activity context, ArrayList al) {
            super(context,0,al);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View listItem=convertView;
            if(listItem==null)
            {
                listItem=LayoutInflater.from(getContext()).inflate(R.layout.orderlist_layout,parent,false);
            }

           final foodSubItem fs= (foodSubItem) getItem(position);

            TextView tv=listItem.findViewById(R.id.textView01);
            tv.setText(fs.getTitle());

            Button b=listItem.findViewById(R.id.cancelButton);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("listener",fs.getTitle());

                    orderArrayList.remove(fs);
                    Communicator com= (Communicator) getActivity();
                    com.cancelButton(fs);
                    orderItemAdapter adapter=new orderItemAdapter(getActivity(),orderArrayList);
                    lv.setAdapter(adapter);
                }
            });


            TextView tv2=listItem.findViewById(R.id.textView02);
            tv2.setText("Quantity: "+String.valueOf(fs.getQuantity()));

            TextView tv3=listItem.findViewById(R.id.textView03);
            tv3.setText("Price: "+String.valueOf((fs.getPrice())*(fs.getQuantity())));

            return listItem;
        }



    }


}
