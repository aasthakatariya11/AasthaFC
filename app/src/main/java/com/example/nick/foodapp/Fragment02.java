package com.example.nick.foodapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;


public class Fragment02 extends Fragment {

    ListView lv;

    public Fragment02() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_fragment02,container,false);

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new jsonParser().execute();
    }

    private class jsonParser extends AsyncTask<String,String,String>
    {
        StringBuilder sb;
        String jsonstr;
        String line;
        String userName="test";
        String password="test1234@";
        ArrayList<foodItem> al=new ArrayList<>();
        String[] categories;

        @Override
        protected String doInBackground(String... strings) {
            try {
             //   Authenticator.setDefault(new passwordAuthenticator());
                URL url=new URL(" ");
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

            jsonstr= String.valueOf(sb);
            Log.v("MainActivity",jsonstr);
             return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray jarr= null;
            try {
                jarr = new JSONArray(jsonstr);
                for(int i=0;i<jarr.length();i++)
                {
                    JSONObject jsonObj = jarr.getJSONObject(i);
                    String title=jsonObj.getString("title");
                  //  ArrayList products=new ArrayList();

                    JSONArray jarray=jsonObj.getJSONArray("products");
                    if(jarray.length()!=0)
                    {
                    String products=jarray.toString();



                        al.add(new foodItem(title, products));
                      //  Log.v("main", products);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




            foodItemAdapter adapter=new foodItemAdapter(getActivity(),al);
            lv=getActivity().findViewById(R.id.listView2);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Communicator c= (Communicator) getActivity();
                    c.response(al.get(position));
                }
            });
        }
    }

}
