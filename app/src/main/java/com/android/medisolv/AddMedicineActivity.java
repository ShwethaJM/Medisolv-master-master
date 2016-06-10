package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddMedicineActivity extends AppCompatActivity implements View.OnClickListener {
    Button homebutton,backbutton;

    TextView doctorId,patientId;
    Bundle bundle;
    String doctorIdValue,patientIdValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        bundle = getIntent().getExtras();
        doctorId = (TextView)findViewById(R.id.databasedoctorid);
        doctorIdValue= bundle.getString("id");
        doctorId.setText(doctorIdValue.trim());

        patientId = (TextView)findViewById(R.id.databasepatientid);
        patientIdValue=bundle.getString("PatientId");
        patientId.setText(patientIdValue.trim());


        homebutton=(Button)findViewById(R.id.addmedicinehomebutton);
        backbutton=(Button)findViewById(R.id.addmedicinebackbutton);

        homebutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
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
                Intent intent = new Intent(AddMedicineActivity.this,WelcomdoctorActivity.class);
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

        if(v.getId()==R.id.addmedicinehomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        }
        else if(v.getId()==R.id.addmedicinebackbutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        }
    }
}
