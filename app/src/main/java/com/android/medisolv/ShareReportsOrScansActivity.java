package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShareReportsOrScansActivity extends AppCompatActivity implements View.OnClickListener {
    Button back;
    Button home;
    String patient_id,patient_name;
    Bundle bundle;
    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_reports_or_scans);

        bundle = getIntent().getExtras();
        patient_id = bundle.getString("id");
        patient_name = bundle.getString("name");

        id = (TextView)findViewById(R.id.shareReportPatientIdValue);
        id.setText(patient_id.trim());

        back = (Button)findViewById(R.id.shareReportBackButton);
        back.setOnClickListener(this);

        home= (Button)findViewById(R.id.shareReportHomeButton);
        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.shareReportHomeButton){
            System.out.println("View Report Home button clicked");
            Intent intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(view.getId() == R.id.shareReportBackButton){
            System.out.println("View Report Back button clicked");
            Intent intent = new Intent(this,ReportsOrScansActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
}
