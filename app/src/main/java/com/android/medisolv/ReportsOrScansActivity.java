package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReportsOrScansActivity extends AppCompatActivity implements View.OnClickListener {

    Button view1;
    Button share1;
    Button home , back;
    String patient_id,patient_name;
    Bundle bundle;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_or_scans);

        bundle = getIntent().getExtras();
        patient_id = bundle.getString("id");
        patient_name = bundle.getString("name");

        id = (TextView)findViewById(R.id.reportPatientIdValue);
        id.setText(patient_id.trim());

        /*setting the listener when View button has clicked*/
        view1 = (Button)findViewById(R.id.viewButton1);
        view1.setOnClickListener(this);

        /*setting the listener when Share button has clicked*/
        share1 = (Button)findViewById(R.id.shareButton1) ;
        share1.setOnClickListener(this);

        /*setting the listener when Home button has clicked*/
        home= (Button)findViewById(R.id.reportHomeButton);
        home.setOnClickListener(this);

        /*setting the listener when Back button has clicked*/
        back= (Button)findViewById(R.id.reportBackButton);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId() == R.id.viewButton1){
         intent = new Intent(this,ViewReportsOrScansActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(view.getId() == R.id.shareButton1){
           intent = new Intent(this,ShareReportsOrScansActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(view.getId() == R.id.reportHomeButton){
            intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

         /*Redirecting to Back page when Back button has clicked*/
        else if(view.getId() == R.id.reportBackButton){
            intent = new Intent(this,ViewHistoryActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
