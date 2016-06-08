package com.android.medisolv;

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
