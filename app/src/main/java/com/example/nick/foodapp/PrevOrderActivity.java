package com.example.nick.foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PrevOrderActivity extends AppCompatActivity implements PreviousCommunicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_order);

        Log.v("previous","hello");
    }

    @Override
    public void PreviousResponse(PreviousOrder order) {
        PreviousOrderDetailsFragment frag2= (PreviousOrderDetailsFragment) getFragmentManager().findFragmentById(R.id.previousOrderDetailsFragment);
        frag2.PreviousResponse(order);

    }

}
