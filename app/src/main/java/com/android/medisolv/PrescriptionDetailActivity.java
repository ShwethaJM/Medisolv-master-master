package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrescriptionDetailActivity extends AppCompatActivity implements View.OnClickListener{

    Button back;
    Button home;
    Button buy;
    String patient_id,patient_name;
    Bundle bundle;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_detail);

        bundle = getIntent().getExtras();
        patient_id = bundle.getString("id");
        patient_name = bundle.getString("name");

        id = (TextView)findViewById(R.id.prescriptionDetailPatientIdValue);
        id.setText(patient_id.trim());

        back = (Button)findViewById(R.id.prescriptionDetailBackButton);
        back.setOnClickListener(this);

        home= (Button)findViewById(R.id.prescriptionDetailHomeButton);
        home.setOnClickListener(this);

        buy = (Button)findViewById(R.id.prescriptionDetailBuyButton);
        buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId() == R.id.prescriptionDetailHomeButton){
            System.out.println("View Report Home button clicked");
            intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else  if(view.getId() == R.id.prescriptionDetailBuyButton){
            intent = new Intent(this,BuyMedicinesActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(view.getId() == R.id.prescriptionDetailBackButton){
            System.out.println("View Report Back button clicked");
            intent = new Intent(this,ViewHistoryActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
