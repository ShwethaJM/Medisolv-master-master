package com.android.medisolv;

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
    public void onClick(View v)
    {

        if(v.getId()==R.id.addmedicinehomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addmedicinebackbutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
