package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddPatientActivity extends AppCompatActivity implements View.OnClickListener {
    Button home,back,history,prescribe,testorscan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        home=(Button)findViewById(R.id.addpatienthomebutton);
        back=(Button)findViewById(R.id.addpatientbackbutton);
        history=(Button)findViewById(R.id.view_historybutton);
        prescribe=(Button)findViewById(R.id.priscribebutton);
        testorscan=(Button)findViewById(R.id.addtestscanbutton);

        home.setOnClickListener(this);
        history.setOnClickListener(this);
        back.setOnClickListener(this);
        prescribe.setOnClickListener(this);
        testorscan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.addpatienthomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addpatientbackbutton)
        {
            Intent intent = new Intent(this,ConsultapatientActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.view_historybutton)
        {
            Intent intent = new Intent(this,ViewHistoryActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.priscribebutton)
        {
            Intent intent = new Intent(this,PrescribeMedicineActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addtestscanbutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            startActivity(intent);
        }

    }
}
