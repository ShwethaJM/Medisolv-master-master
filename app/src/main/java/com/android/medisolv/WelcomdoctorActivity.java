package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomdoctorActivity extends AppCompatActivity implements View.OnClickListener {
    Button consultpatient,addpatient,addmedicine,addscan;
    String patient_id;
    String patient_name;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomdoctor);

        bundle = getIntent().getExtras();
        patient_id = bundle.getString("id");
        patient_name = bundle.getString("name");
        TextView patientID=(TextView)findViewById(R.id.patientid);
        TextView patientName=(TextView)findViewById(R.id.name);
        patientID.setText(patient_id.trim());
        patientName.setText(patient_name.trim());


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
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.consultpatientaddpatientbutton)
        {
            Intent intent = new Intent(this,AddPatientActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addamedicinebutton)
        {
            Intent intent = new Intent(this,AddMedicineActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addscanbutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
