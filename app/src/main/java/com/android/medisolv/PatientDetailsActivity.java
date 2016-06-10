package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    Button home,back,history,prescribe,testorscan;
    Bundle bundle;
    String patient_id,doctor_id;
    TextView doctor,patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        bundle= getIntent().getExtras();
        patient_id = bundle.getString("PatientId");
        doctor_id = bundle.getString("id");

        doctor = (TextView)findViewById(R.id.databasedoctorid);
        patient = (TextView)findViewById(R.id.databasepatientid);

        doctor.setText(doctor_id.trim());
        patient.setText(patient_id.trim());

        home=(Button)findViewById(R.id.addpatienthomebutton);
        back=(Button)findViewById(R.id.addpatientbackbutton);
        history=(Button)findViewById(R.id.view_historybutton);
        prescribe=(Button)findViewById(R.id.priscribebutton);
        testorscan=(Button)findViewById(R.id.addtestscanbutton);

        home.setOnClickListener(this);
        history.setOnClickListener(this);
        back.setOnClickListener(this);
        prescribe.setOnClickListener(this);
        testorscan.setOnClickListener(this);
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
                Intent intent = new Intent(PatientDetailsActivity.this,ConsultapatientActivity.class);
                intent.putExtras(bundle);
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
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.addpatienthomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.addpatientbackbutton)
        {
            Intent intent = new Intent(this,ConsultapatientActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.view_historybutton)
        {
            Intent intent = new Intent(this,ViewHistoryActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.priscribebutton)
        {
            Intent intent = new Intent(this,PrescribeMedicineActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.addtestscanbutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

    }
}
