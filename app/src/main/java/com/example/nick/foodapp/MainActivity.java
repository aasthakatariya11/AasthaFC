package com.example.nick.foodapp;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements Communicator{


    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=(Button)findViewById(R.id.checkButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PrevOrderActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void response(foodItem fi) {
        Fragment03 fragment3= (Fragment03) getFragmentManager().findFragmentById(R.id.fragment3);
        Fragment01 fragment1= (Fragment01) getFragmentManager().findFragmentById(R.id.fragment1);
        fragment3.updateList(fi,fragment1.orderArrayList);
       // getFragmentManager().beginTransaction().replace(R.id.buttonContainer,new subItemFragment()).commit();
    }

    @Override
    public void updateOrderList(foodSubItem fsi)
    {
      //  Log.v("MainActivity",fsi.getTitle());
    //    Log.v("MainActivity", String.valueOf(fsi.getQuantity()));
        Fragment01 fragment01= (Fragment01) getFragmentManager().findFragmentById(R.id.fragment1);
        fragment01.orderList(fsi);

    }

    @Override
    public void removeItemOrder(foodSubItem fsi) {
        Fragment01 fragment01= (Fragment01) getFragmentManager().findFragmentById(R.id.fragment1);
        fragment01.removeItemOrder(fsi);
    }

    @Override
    public void finalOrder(String tableNo,int coversPeople) {

        Fragment01 f= (Fragment01) getFragmentManager().findFragmentById(R.id.fragment1);
        finalOrder order=new finalOrder();
        order.setFinalOrderList(f.orderArrayList);
        order.setCoversPeople(coversPeople);
        order.setTableNo(tableNo);

        Log.v("main", String.valueOf(order.getFinalOrderList()));
        Log.v("main",order.getTableNo());

    }

    @Override
    public void cancelButton(foodSubItem fs) {

        Fragment03 fragment03= (Fragment03) getFragmentManager().findFragmentById(R.id.fragment3);
        fragment03.cancelButtonUpdate(fs);

    }



}
