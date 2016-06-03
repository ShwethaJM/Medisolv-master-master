package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PriscriptionActivity extends AppCompatActivity implements View.OnClickListener {

    Button home,drug1;


    String patient_id,patient_name;
    Bundle bundle;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priscription);

        bundle = getIntent().getExtras();
        patient_id = bundle.getString("id");
        patient_name = bundle.getString("name");

        id = (TextView)findViewById(R.id.prescriptionPatientIdValue);
        id.setText(patient_id.trim());

        home= (Button)findViewById(R.id.prescriptionHomeButton);
        home.setOnClickListener(this);

        drug1=(Button)findViewById(R.id.drug1);
        drug1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        if(view.getId() == R.id.prescriptionHomeButton){
            System.out.println("Priscription Home button");
            intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(view.getId() == R.id.drug1){}
        System.out.println("Priscription Detail button");
        intent = new Intent(this,PrescriptionDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
