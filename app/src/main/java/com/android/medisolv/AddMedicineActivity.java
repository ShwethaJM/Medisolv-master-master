package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddMedicineActivity extends AppCompatActivity implements View.OnClickListener {
    Button homebutton,backbutton;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);


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
            startActivity(intent);
        }
        else if(v.getId()==R.id.addmedicinebackbutton)
        {
            Intent intent = new Intent(this,PatientDetailsActivity.class);
            startActivity(intent);
        }
    }
}
