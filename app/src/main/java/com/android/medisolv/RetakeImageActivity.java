package com.android.medisolv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RetakeImageActivity extends AppCompatActivity implements View.OnClickListener {
Button homebutton,backbutton,retakebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retake_image);

        homebutton=(Button)findViewById(R.id.retakescanhomebutton);
        backbutton=(Button)findViewById(R.id.retakebackbutton);
        retakebutton=(Button)findViewById(R.id.retakebutton);

        homebutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        retakebutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.retakescanhomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.retakebackbutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.retakebutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            startActivity(intent);
        }

    }
}
