package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddDependentActivity extends AppCompatActivity implements View.OnClickListener {

    Button homebutton,adddependent,registerdependent;
    Bundle bundle;
    TextView patientId;
    String patientIdValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dependent);

        bundle = getIntent().getExtras();
        patientIdValue=bundle.getString("id");
        patientId = (TextView)findViewById(R.id.adddependentidfromdb);
        patientId.setText(patientIdValue.trim());

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
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.adddependentbutton)
        {
            Intent intent = new Intent(this,RegistredDependentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.registerdependentbutton)
        {
            Intent intent = new Intent(this,DependentRegisterActivity.class);
            startActivity(intent);
        }
    }
}
