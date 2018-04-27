package com.example.nick.foodapp;


import android.app.Activity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment03 extends Fragment {

    ListView lv;
    ArrayList finalOrderList;
    ArrayList<foodSubItem> al;
    foodSubItem cancelItem=null;
    int cancelItemIndex=-1;


    public Fragment03() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_fragment03,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv=getActivity().findViewById(R.id.listView3);

    }

    public void updateList(foodItem fi,ArrayList finalOrderList)  {
        this.finalOrderList=new ArrayList();
        this.finalOrderList=finalOrderList;
        al=new ArrayList<>();
        String products=fi.getProducts();
        JSONArray jSonarr= null;
        try {
            jSonarr = new JSONArray(products);

        for(int i=0;i<jSonarr.length();i++)
        {
            JSONObject jSonObj=jSonarr.getJSONObject(i);
            String title=jSonObj.getString("title");
            double price=jSonObj.getDouble("price");
            al.add(new foodSubItem(title,price));
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        foodSubItemAdapter01 adapter=new foodSubItemAdapter01(getActivity(),al);
        lv.setAdapter(adapter);

    }


    public void cancelButtonUpdate(foodSubItem fs)
    {
       /* int index=-1;

        for(int i=0;i<al.size();i++)
        {
            if(al.get(i).getTitle().equals(fs.getTitle()))
            {
                index=i;
                break;
            }
        }

        al.get(index).setQuantity(0);
        */
       cancelItem=fs;
       foodSubItemAdapter01 adapter=new foodSubItemAdapter01(getActivity(),al);
        lv.setAdapter(adapter);
    }



    public class foodSubItemAdapter01 extends ArrayAdapter {

        View listItem;


        public foodSubItemAdapter01(Activity context, ArrayList al) {
            super(context,0,al);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

           // boolean isPresentInOrder=false;
        //    foodSubItem fs;

            listItem=convertView;
            if(listItem==null)
            {
                listItem= LayoutInflater.from(getContext()).inflate(R.layout.sublist_layout,parent,false);
            }

            final foodSubItem fi= (foodSubItem) getItem(position);

            TextView tv=listItem.findViewById(R.id.textView2);
            tv.setText(fi.title);

            TextView tv1=listItem.findViewById(R.id.textView3);
            tv1.setText((String.valueOf(fi.price)));



            final Button b=listItem.findViewById(R.id.addItemButton);
            final Button b1=listItem.findViewById(R.id.minusButton);
            final Button b2=listItem.findViewById(R.id.plusButton);
            final TextView tvcount=listItem.findViewById(R.id.textViewcount);

            for(int i=0;i<finalOrderList.size();i++)
            {
               foodSubItem fs= (foodSubItem) finalOrderList.get(i);
                Log.v("frag003",fi.getTitle()+" "+fs.getTitle());

                if (fi.getTitle().equals(fs.getTitle()))
                {
                    b.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    tvcount.setVisibility(View.VISIBLE);
                    tvcount.setText(String.valueOf(fs.getQuantity()));
                    fi.setQuantity(fs.getQuantity());

          //          isPresentInOrder=true;
                    Log.v("frag3",String.valueOf(fs.getQuantity()));
                    break;
                }

            }

            if(cancelItem!=null) {
                if (fi.getTitle() == cancelItem.getTitle()) {
               fi.setQuantity(0);
                }
            }

            if(fi.getQuantity()==0)
            {
                b.setVisibility(View.VISIBLE);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                tvcount.setVisibility(View.INVISIBLE);
            }


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    tvcount.setVisibility(View.VISIBLE);

                    fi.setQuantity(1);

                    Communicator com= (Communicator) getActivity();
                    com.updateOrderList(fi);
                }
            });

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String quantity= (String) tvcount.getText();
                    int counter=Integer.parseInt(quantity);
                    if(counter>1) {
                        counter--;
                        tvcount.setText(String.valueOf(counter));


                        fi.setQuantity(counter);

                        Communicator com= (Communicator) getActivity();
                        com.updateOrderList(fi);
                    }
                    else if(counter==1)
                    {
                        b.setVisibility(View.VISIBLE);
                        b1.setVisibility(View.INVISIBLE);
                        b2.setVisibility(View.INVISIBLE);
                        tvcount.setVisibility(View.INVISIBLE);

                        Communicator com= (Communicator) getActivity();
                        com.removeItemOrder(fi);
                    }
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String quantity= (String) tvcount.getText();
                    int counter=Integer.parseInt(quantity);
                    counter++;
                    Log.v("adapter", String.valueOf(counter));
                    tvcount.setText(String.valueOf(counter));

                    fi.setQuantity(counter);

                    Communicator com= (Communicator) getActivity();
                    com.updateOrderList(fi);

                }
            });

            return listItem;
        }
    }






}
