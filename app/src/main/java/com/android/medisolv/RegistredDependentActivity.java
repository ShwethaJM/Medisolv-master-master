package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistredDependentActivity extends AppCompatActivity implements View.OnClickListener {

    Button homebutton,backbutton,submitbutton;
    Bundle bundle;
    TextView patientId;
    String patientIdValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registred_dependent);

        bundle = getIntent().getExtras();
        patientIdValue=bundle.getString("id");
        patientId = (TextView)findViewById(R.id.databasedependentid);
        patientId.setText(patientIdValue.trim());

        homebutton=(Button)findViewById(R.id.registeredhomebutton);
        backbutton=(Button)findViewById(R.id.regdepbackbutton);
        submitbutton=(Button)findViewById(R.id.regdepsubmitbutton);

        homebutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        submitbutton.setOnClickListener(this);
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
                Intent intent = new Intent(RegistredDependentActivity.this,WelcomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
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
        if(v.getId()==R.id.registeredhomebutton)
        {
            Intent intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.regdepbackbutton)
        {
            Intent intent = new Intent(this,AddDependentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.regdepsubmitbutton)
        {
            /*Code to save*/
        }
    }
}
