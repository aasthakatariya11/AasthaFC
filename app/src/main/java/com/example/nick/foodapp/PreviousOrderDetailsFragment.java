package com.example.nick.foodapp;


import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousOrderDetailsFragment extends Fragment {



    TextView tv;
    String comment;
    ArrayList ordersDetailsList;
    ArrayList barordersList;

    public PreviousOrderDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_order_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv=(TextView)getActivity().findViewById(R.id.prev_total);

    }

    public void PreviousResponse(PreviousOrder order){
        Double total=order.getTotal();
        tv.setText("Total: "+total);

        comment=order.getComment();

        ordersDetailsList=new ArrayList();
        barordersList=new ArrayList();
        String items_json=order.getItems_json();
        String items_jsonFormattedString = items_json.replaceAll("//", "");
        Log.v("details",items_json);
        try {
            JSONObject previousjarray=new JSONObject(items_jsonFormattedString);
            //JSONObject previousjobj= (JSONObject) previousjarray.get(0);
            //Log.v("json", String.valueOf(previousjobj));

          //  JSONArray jarr=previousjarray.getJSONArray(0);
          //  Log.v("json", String.valueOf(jarr));

            JSONArray kitchen=previousjarray.getJSONArray("kitchen");
                    Log.v("json", String.valueOf(kitchen));


            for(int i=0;i<kitchen.length();i++)
            {
                JSONObject previousjobj=kitchen.getJSONObject(i);
                String title=previousjobj.getString("title");
                int quantity=previousjobj.getInt("quantity");
                Double price=previousjobj.getDouble("price");
              //  String comments=previousjobj.getString("comments");
                Log.v("order01",title);

                ordersDetailsList.add(new PreviousOrderDetails(quantity,price,title));
            }

            JSONArray bar=previousjarray.getJSONArray("bar");

            for(int i=0;i<bar.length();i++)
            {
                JSONObject previousbarobj=bar.getJSONObject(i);
                String title=previousbarobj.getString("title");
                int quantity=previousbarobj.getInt("quantity");
                Double price=previousbarobj.getDouble("price");
                //  String comments=previousjobj.getString("comments");
                Log.v("order01",title);
                barordersList.add(new PreviousOrderDetails(quantity,price,title));
            }

            TextView tvKitchen = getActivity().findViewById(R.id.Kitchen_text_view);

            PreviousDetailsAdapter previousdetailsadapter = new PreviousDetailsAdapter(getActivity(), ordersDetailsList);
            ListView lvpreviousdetails = getActivity().findViewById(R.id.prev_order_details);
            lvpreviousdetails.setAdapter(previousdetailsadapter);

            if(ordersDetailsList.isEmpty()) {

                tvKitchen.setVisibility(View.GONE);
            }
            else {
                tvKitchen.setVisibility(View.VISIBLE);

            }
            TextView tvbar = getActivity().findViewById(R.id.Bar_text_view);

            PreviousBarDetailsAdapter previousBarDetails = new PreviousBarDetailsAdapter(getActivity(), barordersList);
            ListView lvbarprevious = getActivity().findViewById(R.id.prev_order_bar_details);
            lvbarprevious.setAdapter(previousBarDetails);

            if(barordersList.isEmpty()) {
                tvbar.setVisibility(View.GONE);
            }


            else {
                tvbar.setVisibility(View.VISIBLE);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class PreviousDetailsAdapter extends ArrayAdapter
    {

        public PreviousDetailsAdapter(Activity context, ArrayList ordersDetailsList) {
            super(context, 0,ordersDetailsList);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem=convertView;

            if(listItem==null)
            {
                listItem=LayoutInflater.from(getContext()).inflate(R.layout.prev_detail_list_item,parent,false);
            }

            PreviousOrderDetails prevorder= (PreviousOrderDetails) getItem(position);

            TextView tv1=listItem.findViewById(R.id.previousTitle);
            String title=prevorder.getTitle();
            tv1.setText("Title: "+title);
            TextView tv2=listItem.findViewById(R.id.previousPrice);
            Double price=prevorder.getPrice();
            tv2.setText("Price: "+String.valueOf(price));
            TextView tv3=listItem.findViewById(R.id.previousQuantity);
            int quantity=prevorder.getQuantity();
            tv3.setText("Quantity: "+String.valueOf(quantity));

            TextView tv4 = listItem.findViewById(R.id.previousComments);
            if(!comment.equals("No Comment")) {

                tv4.setText(comment);
                tv4.setVisibility(View.VISIBLE);
            }
            else
            {
              tv4.setVisibility(View.GONE);
            }
            return listItem;

        }

    }

    public class PreviousBarDetailsAdapter extends ArrayAdapter
    {

        public PreviousBarDetailsAdapter(Activity context, ArrayList barordersList) {
            super(context, 0,barordersList);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem=convertView;

            if(listItem==null)
            {
                listItem=LayoutInflater.from(getContext()).inflate(R.layout.prev_detail_bar_list_item,parent,false);
            }

            PreviousOrderDetails prevorder= (PreviousOrderDetails) getItem(position);

            TextView tv1=listItem.findViewById(R.id.previousBarTitle);
            String title=prevorder.getTitle();
            tv1.setText("Title: "+title);
            TextView tv2=listItem.findViewById(R.id.previousBarPrice);
            Double price=prevorder.getPrice();
            tv2.setText("Price: "+String.valueOf(price));
            TextView tv3=listItem.findViewById(R.id.previousBarQuantity);
            int quantity=prevorder.getQuantity();
            tv3.setText("Quantity: "+String.valueOf(quantity));

         //   TextView tv02=listItem.findViewById(R.id.bar_)

            TextView tv4 = listItem.findViewById(R.id.previousBarComments);
            if(!comment.equals("No Comment")) {

                tv4.setText(comment);
                tv4.setVisibility(View.VISIBLE);
            }
            else
            {
                tv4.setVisibility(View.GONE);
            }


            return listItem;

        }

    }


}
