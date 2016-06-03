package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*code to set custom title bar*/
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /*Method to direct to Registration Page*/
    public void openRegistrationPage(View view){

        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
    //Method to direct to Login page
    public void openLoginPage(View view){

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
