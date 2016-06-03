package com.android.medisolv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPatientIDActivity extends AppCompatActivity {
    EditText forgotMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_patient_id);
        forgotMobileNo = (EditText)findViewById(R.id.enterMobileNoEdit);
        forgotMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNo= forgotMobileNo.getText().toString();
                if(mobileNo.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter valid Mobile No", Toast.LENGTH_LONG).show();
                }
                else if(mobileNo.length()<10){
                    Toast.makeText(getApplicationContext(), "Mobile Number should be 10 digits", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
