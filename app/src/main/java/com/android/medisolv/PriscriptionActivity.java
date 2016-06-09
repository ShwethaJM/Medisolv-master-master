package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Are you sure you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
                Intent intent = new Intent(PriscriptionActivity.this,WelcomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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
