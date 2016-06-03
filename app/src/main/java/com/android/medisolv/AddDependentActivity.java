package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddDependentActivity extends AppCompatActivity implements View.OnClickListener {
Button homebutton,adddependent,registerdependent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dependent);

        homebutton=(Button)findViewById(R.id.dependenthomebutton);
        adddependent=(Button)findViewById(R.id.adddependentbutton);
        registerdependent=(Button)findViewById(R.id.registerdependentbutton);

        homebutton.setOnClickListener(this);
        adddependent.setOnClickListener(this);
        registerdependent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.dependenthomebutton)
        {
            Intent intent = new Intent(this,WelcomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PatientID", "Dummy");
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.adddependentbutton)
        {
            Intent intent = new Intent(this,RegistredDependentActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.registerdependentbutton)
        {
            Intent intent = new Intent(this,DependentRegisterActivity.class);
            startActivity(intent);
        }
    }
}
