package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class PrescribeMedicineActivity extends AppCompatActivity implements View.OnClickListener {
    Button homebutton,backbutton,addmedicine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityriscribe_medicine);

        AutoCompleteTextView medicinename=(AutoCompleteTextView)findViewById(R.id.medicinenameTextView);

        homebutton=(Button)findViewById(R.id.prescribehomebutton);
        backbutton=(Button)findViewById(R.id.prescribebackbutton);
        addmedicine=(Button)findViewById(R.id.addmedicinebuttons);

        homebutton.setOnClickListener(this);
        addmedicine.setOnClickListener(this);
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.prescribehomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.prescribebackbutton)
        {
            Intent intent = new Intent(this,PatientDetailsActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addmedicinebuttons)
        {
            Intent intent = new Intent(this,AddMedicineActivity.class);
            startActivity(intent);
        }

    }
}
