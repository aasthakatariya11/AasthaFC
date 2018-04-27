package com.example.nick.foodapp;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousOrderListFragment extends Fragment {

    ListView lvprevious;

    public PreviousOrderListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_order_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // lvprevious=getActivity().findViewById(R.id.Prev_order_list);
        new jsonFetch().execute();
    }

    private class jsonFetch extends AsyncTask<String,String,String>
    {
        StringBuilder sb;
        String jsonstring;
        String line;
        ListView lvprevious;
        String userName="test";
        String password="test1234@";
        ArrayList<PreviousOrder> previousorderlistal=new ArrayList<>();
        String[] results;

        @Override
        protected String doInBackground(String... strings) {
            try {
                //   Authenticator.setDefault(new passwordAuthenticator());
                URL url=new URL("http://35.154.164.138/api/orders/");
                HttpURLConnection con= (HttpURLConnection) url.openConnection();

                con.setRequestMethod( "GET" );
                con.setDoInput( true );
                String credential = Base64.encodeToString( (userName+":"+password).getBytes("UTF-8"), Base64.DEFAULT);
                con.setRequestProperty ("Authorization", "Basic " + credential.substring(0, credential.length()-1));                con.connect();
                Log.d("response option",con.getResponseCode()+"");

                InputStream in=new BufferedInputStream(con.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                sb=new StringBuilder();
                while((line=br.readLine())!=null)
                {
                    sb.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            jsonstring= String.valueOf(sb);
            Log.v("PrevOrderListFragment",jsonstring);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


          //  JSONArray jarr= null;
            try {
                JSONObject jsonObj = new JSONObject(jsonstring);
                JSONArray results = jsonObj.getJSONArray("results");
                Log.v("aasthapaastha",results.toString());


                for(int i=0;i<results.length();i++)
                {
                    JSONObject jsonobj1 = results.getJSONObject(i);
                    int id=jsonobj1.getInt("id");

                    Log.v("aasuzi",String.valueOf(id));
                    int covers=jsonobj1.getInt("covers");
                    String date_added=jsonobj1.getString("date_added");

                    int table=jsonobj1.getInt("table");
                    //  ArrayList products=new ArrayList();
                    Double total=jsonobj1.getDouble("total");

                    String items_json=jsonobj1.getString("items_json");



                        previousorderlistal.add(new PreviousOrder(id,table,covers,date_added,total,items_json));
                          Log.v("prevorder", date_added);
                    }
                } catch (JSONException e1) {
                e1.printStackTrace();
            }


            Log.v("aastha",previousorderlistal.toString());
            PreviousOrderAdapter01 previous_adapter=new PreviousOrderAdapter01(getActivity(),previousorderlistal);
            lvprevious= getActivity().findViewById(R.id.Prev_order_list);

            lvprevious.setAdapter(previous_adapter);




        }


    }

    public class PreviousOrderAdapter01 extends ArrayAdapter implements View.OnClickListener{
        PreviousOrder order;

        public PreviousOrderAdapter01(@NonNull Activity context, ArrayList<PreviousOrder> Prev_order_list ) {
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
            PreviousCommunicator prevcomm= (PreviousCommunicator) getActivity();
            prevcomm.PreviousResponse(order);

        }
    }


}
