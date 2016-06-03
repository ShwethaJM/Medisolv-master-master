package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistredDependentActivity extends AppCompatActivity implements View.OnClickListener {
Button homebutton,backbutton,submitbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registred_dependent);

        homebutton=(Button)findViewById(R.id.registeredhomebutton);
        backbutton=(Button)findViewById(R.id.regdepbackbutton);
        submitbutton=(Button)findViewById(R.id.regdepsubmitbutton);

        homebutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        submitbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.registeredhomebutton)
        {
            Intent intent = new Intent(this,WelcomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PatientID", "Dummy");
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.regdepbackbutton)
        {
            Intent intent = new Intent(this,AddDependentActivity.class);
            startActivity(intent);
        }
    }
}
