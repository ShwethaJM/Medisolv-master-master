package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConsultDoctorActivity extends AppCompatActivity implements View.OnClickListener{

    Bundle bundle;
    TextView patientId,patientName;
    String patientIdValue,patientNameValue;
    Button home,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_doctor);

        bundle = getIntent().getExtras();
        patientIdValue=bundle.getString("id");
        patientNameValue = bundle.getString("name");
        patientId = (TextView)findViewById(R.id.consultDoctorPatientIDValue);
        patientId.setText(patientIdValue.trim());
        patientName = (TextView)findViewById(R.id.consultDoctorPatientNameValue);
        patientName.setText(patientNameValue.trim());

        home=(Button)findViewById(R.id.consultDoctorhomebutton);
        back=(Button)findViewById(R.id.consultDoctorbackbutton);

        home.setOnClickListener(this);
        back.setOnClickListener(this);
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
                Intent intent = new Intent(ConsultDoctorActivity.this,WelcomeActivity.class);
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
    public void onClick(View v)
    {
        if(v.getId()==R.id.consultDoctorhomebutton)
        {
            Intent intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.consultDoctorbackbutton)
        {
            Intent intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
