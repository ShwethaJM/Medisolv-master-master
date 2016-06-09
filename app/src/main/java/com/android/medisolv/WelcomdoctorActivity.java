package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
            /*Code to redirect to Adda a patient screen*/
            /*Intent intent = new Intent(this,PatientDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);*/
        }
        else if(v.getId()==R.id.addamedicinebutton)
        {
            Intent intent = new Intent(this,AddMedicineActivity.class);
            intent.putExtras(bundle);
            intent.putExtra("PatientId","");
            startActivity(intent);
        }
        else if(v.getId()==R.id.addscanbutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            intent.putExtras(bundle);
            intent.putExtra("PatientId","");
            startActivity(intent);
        }
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
                Intent intent = new Intent(WelcomdoctorActivity.this,LoginActivity.class);
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
}
