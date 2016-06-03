package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultapatientActivity extends AppCompatActivity implements View.OnClickListener {
    Button home,back,addpatient;
    EditText patient_id,patient_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultapatient);

        patient_id=(EditText)findViewById(R.id.consultpatientid);
        patient_name=(EditText)findViewById(R.id.consultpatientname);

        home=(Button)findViewById(R.id.consultpatienthomebutton);
        back=(Button)findViewById(R.id.consultpagebackbutton);

        home.setOnClickListener(this);
        back.setOnClickListener(this);
        if(patient_name.equals(null) || patient_id.equals(null))
        {
            Toast.makeText(getApplicationContext(),"Fill all the blocks",Toast.LENGTH_SHORT);
        }
        else
        {
            addpatient=(Button)findViewById(R.id.consultpatientaddpatientbutton);
            addpatient.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.consultpatienthomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.consultpagebackbutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.consultpatientaddpatientbutton)
        {
            Intent intent = new Intent(this,AddPatientActivity.class);
            startActivity(intent);
        }

    }
}
