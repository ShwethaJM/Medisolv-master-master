package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewHistoryActivity extends AppCompatActivity implements View.OnClickListener {
    String patient_id,patient_name;
    Button home,back;
    TextView id;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        bundle = getIntent().getExtras();
        patient_id = bundle.getString("id");
        patient_name = bundle.getString("name");

        id = (TextView)findViewById(R.id.txt);
        id.setText(patient_id.trim());

        home=(Button)findViewById(R.id.homebutton);
        back=(Button)findViewById(R.id.backbutton);
        home.setOnClickListener(this);
        back.setOnClickListener(this);
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
                Intent intent = new Intent(ViewHistoryActivity.this,WelcomeActivity.class);
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
        if(v.getId()==R.id.homebutton)
        {
            Intent intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.backbutton)
        {
            Intent intent = new Intent(this,WelcomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    public void openReportsOrScanActivity(View view){
        Intent intent = new Intent(this,ReportsOrScansActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void openPrescriptionActivity(View view){
        Intent intent = new Intent(this,PriscriptionActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
