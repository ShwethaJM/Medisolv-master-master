package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    String patient_id;
    String patient_name;
    Button viewhistory,consultDoctor,addDependent,logout;
    Button viewPrescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Retrieve data from previous activity
        Bundle bundle = getIntent().getExtras();
        patient_id = bundle.getString("id");
        patient_name = bundle.getString("name");
        TextView patientID=(TextView)findViewById(R.id.patientid);
        TextView patientName=(TextView)findViewById(R.id.name);
        patientID.setText(patient_id.trim());
        patientName.setText(patient_name.trim());

        /*setting the listener when viewHistory button has clicked*/
        viewhistory=(Button)findViewById(R.id.viewhistory);
        viewhistory.setOnClickListener(this);

        /*setting the listener when viewPrescription button has clicked*/
        viewPrescription=(Button)findViewById(R.id.viewprescription);
        viewPrescription.setOnClickListener(this);

        consultDoctor = (Button)findViewById(R.id.consultdoctor);
        addDependent = (Button)findViewById(R.id.adddependent);
        logout = (Button)findViewById(R.id.logoutbutton);
        consultDoctor.setOnClickListener(this);
        addDependent.setOnClickListener(this);
        logout.setOnClickListener(this);

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
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
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

    /*Method to Redirect to corresponding page based on which button has clicked in the page*/
    @Override
    public void onClick(View v) {
        Intent intent;
        /*Redirecting to ViewHistory page when View History button has clicked*/
        if (v.getId() == R.id.viewhistory) {
            intent = new Intent(this, ViewHistoryActivity.class);
            intent.putExtra("name",patient_name);
            intent.putExtra("id",patient_id);
            startActivity(intent);
            finish();
        }
         /*Redirecting to ViewPrescription page when ViewPrescription button has clicked*/
        else if (v.getId() == R.id.viewprescription) {
            intent = new Intent(this, PriscriptionActivity.class);
            intent.putExtra("name",patient_name);
            intent.putExtra("id",patient_id);
            startActivity(intent);
            finish();
        }

        else if (v.getId() == R.id.consultdoctor)
        {
            intent = new Intent(this, ConsultDoctorActivity.class);
            intent.putExtra("name",patient_name);
            intent.putExtra("id",patient_id);
            startActivity(intent);
            finish();
        }
        else if (v.getId() == R.id.adddependent) {
            intent = new Intent(this, ConsultDoctorActivity.class);
            intent.putExtra("name",patient_name);
            intent.putExtra("id",patient_id);
            startActivity(intent);
            finish();
        }
        else if (v.getId() == R.id.logoutbutton) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
