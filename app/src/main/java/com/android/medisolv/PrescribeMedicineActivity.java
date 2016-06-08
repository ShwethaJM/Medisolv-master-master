package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class PrescribeMedicineActivity extends AppCompatActivity implements View.OnClickListener {
    Button homebutton,backbutton,addmedicine,submit;
    Bundle bundle;
    String patient_id,doctor_id;
    TextView doctor,patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityriscribe_medicine);

        bundle= getIntent().getExtras();
        patient_id = bundle.getString("PatientId");
        doctor_id = bundle.getString("id");
        doctor = (TextView)findViewById(R.id.databasedoctorid);
        patient = (TextView)findViewById(R.id.databasepatientid);

        doctor.setText(doctor_id.trim());
        patient.setText(patient_id.trim());

        homebutton=(Button)findViewById(R.id.prescribehomebutton);
        backbutton=(Button)findViewById(R.id.prescribebackbutton);
        addmedicine=(Button)findViewById(R.id.addMedicineButton);
        submit=(Button)findViewById(R.id.addMedicineSubmitButton);

        homebutton.setOnClickListener(this);
        addmedicine.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.prescribehomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.prescribebackbutton)
        {
            Intent intent = new Intent(this,PatientDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()== R.id.addMedicineSubmitButton){

            /*code to save the entered Medicine*/
        }
        else if(v.getId()==R.id.addMedicineButton)
        {
            Intent intent = new Intent(this,AddMedicineActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
