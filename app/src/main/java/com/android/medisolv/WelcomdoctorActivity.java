package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomdoctorActivity extends AppCompatActivity implements View.OnClickListener {
    Button consultpatient,addpatient,addmedicine,addscan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomdoctor);

        consultpatient=(Button)findViewById(R.id.consultapatientbutton);
        addpatient=(Button)findViewById(R.id.consultpatientaddpatientbutton);
        addmedicine=(Button)findViewById(R.id.addamedicinebutton);
        addscan=(Button)findViewById(R.id.addscanbutton);

        consultpatient.setOnClickListener(this);
        addpatient.setOnClickListener(this);
        addmedicine.setOnClickListener(this);
        addscan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.consultapatientbutton)
        {
            Intent intent = new Intent(this,ConsultapatientActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.consultpatientaddpatientbutton)
        {
            Intent intent = new Intent(this,AddPatientActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addamedicinebutton)
        {
            Intent intent = new Intent(this,AddMedicineActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addscanbutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            startActivity(intent);
        }
    }
}
