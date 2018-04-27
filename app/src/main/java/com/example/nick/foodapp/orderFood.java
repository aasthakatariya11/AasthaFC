package com.example.nick.foodapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class orderFood extends DialogFragment implements View.OnClickListener{

    Button confirmOrderButton;
    Button r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20;
    Spinner sp;
    View view;
    String tableNo;
    int coversPeople;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater=getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.table_layout,null);

        r1=view.findViewById(R.id.radioButton1);
        r2=view.findViewById(R.id.radioButton2);
        r3=view.findViewById(R.id.radioButton3);
        r4=view.findViewById(R.id.radioButton4);
        r5=view.findViewById(R.id.radioButton5);
        r6=view.findViewById(R.id.radioButton6);
        r7=view.findViewById(R.id.radioButton7);
        r8=view.findViewById(R.id.radioButton8);
        r9=view.findViewById(R.id.radioButton9);
        r10=view.findViewById(R.id.radioButton10);
        r11=view.findViewById(R.id.radioButton11);
        r12=view.findViewById(R.id.radioButton12);
        r13=view.findViewById(R.id.radioButton13);
        r14=view.findViewById(R.id.radioButton14);
        r15=view.findViewById(R.id.radioButton15);
        r16=view.findViewById(R.id.radioButton16);
        r17=view.findViewById(R.id.radioButton17);
        r18=view.findViewById(R.id.radioButton18);
        r19=view.findViewById(R.id.radioButton19);
        r20=view.findViewById(R.id.radioButton20);

          // r1.setChecked(false);

        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);
        r5.setOnClickListener(this);
        r6.setOnClickListener(this);
        r7.setOnClickListener(this);
        r8.setOnClickListener(this);
        r9.setOnClickListener(this);
        r10.setOnClickListener(this);
        r11.setOnClickListener(this);
        r12.setOnClickListener(this);
        r13.setOnClickListener(this);
        r14.setOnClickListener(this);
        r15.setOnClickListener(this);
        r16.setOnClickListener(this);
        r17.setOnClickListener(this);
        r18.setOnClickListener(this);
        r19.setOnClickListener(this);
        r20.setOnClickListener(this);


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Choose Table");

       // group=view.findViewById(R.id.radioGroup);
        sp=view.findViewById(R.id.spinner);
        String[] list={"1","2","3","4","5","6","7","8","9","9 plus"};
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,list);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView t= (TextView) view;
                coversPeople=Integer.parseInt((String) t.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                coversPeople=1;
            }
        });


        confirmOrderButton=view.findViewById(R.id.order);
        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tableNo==null)
                {
                    Toast.makeText(getActivity(),"Please selet table",Toast.LENGTH_SHORT);
                }
                else {
                    Communicator com = (Communicator) getActivity();
                    com.finalOrder(tableNo, coversPeople);
                }
            }
        });


        alertDialog.setView(view);

        Dialog dialog=alertDialog.create();

        return dialog;
    }


    @Override
    public void onClick(View v) {

      /*  r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);
        r4.setChecked(false);
        r5.setChecked(false);
        r6.setChecked(false);
        r7.setChecked(false);
        r8.setChecked(false);
        r9.setChecked(false);
        r10.setChecked(false);
        r11.setChecked(false);
        r12.setChecked(false);
        r13.setChecked(false);
        r14.setChecked(false);
        r15.setChecked(false);
        r16.setChecked(false);
        r17.setChecked(false);
        r18.setChecked(false);
        r19.setChecked(false);
        r20.setChecked(false);

        int id=v.getId();
        RadioButton r=view.findViewById(id);
        r.setChecked(true);
        tableNo= (String) r.getText();

       */

    }

    }

