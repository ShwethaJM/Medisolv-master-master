package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultapatientActivity extends AppCompatActivity implements View.OnClickListener {
    Button home,back,addpatient,submit;
    EditText patient_id,patient_name;
    String patientID,patientName;
    TextView doctorId;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultapatient);

        bundle = getIntent().getExtras();
        doctorId = (TextView)findViewById(R.id.idfromdbtextview);
        String doctorIdValue= bundle.getString("id");
        doctorId.setText(doctorIdValue.trim());


        patient_id=(EditText)findViewById(R.id.consultpatientid);
        patient_name=(EditText)findViewById(R.id.consultpatientname);

        home=(Button)findViewById(R.id.consultpatienthomebutton);
        back=(Button)findViewById(R.id.consultpagebackbutton);

        home.setOnClickListener(this);
        back.setOnClickListener(this);
        addpatient=(Button)findViewById(R.id.consultpatientaddpatientbutton);
        addpatient.setOnClickListener(this);

        submit=(Button)findViewById(R.id.consultPatientSubmitButton);
        submit.setOnClickListener(this);

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
                Intent intent = new Intent(ConsultapatientActivity.this,WelcomdoctorActivity.class);
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
        if(v.getId()==R.id.consultpatienthomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.consultpagebackbutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.consultPatientSubmitButton)
        {
            patientID = patient_id.getText().toString().trim();
            patientName = patient_name.getText().toString().trim();

            if(patientID.equals("")&& patientName.equals("")){
                Toast.makeText(ConsultapatientActivity.this, "Please enter all the values.", Toast.LENGTH_LONG).show();
            }else {
                /*need to add code to validate entered patient id and name in DB or not*/
                Intent intent = new Intent(this, PatientDetailsActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("PatientId",patientID);
                startActivity(intent);
            }
        }
        else if(v.getId()==R.id.consultpatientaddpatientbutton)
        {
            /*Code to redirect to Adda a patient screen*/
        }

    }
}
