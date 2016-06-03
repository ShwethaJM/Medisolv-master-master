package com.android.medisolv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class AddScansActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    Button scan,homebutton,backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scans);

        imageView = (ImageView) this.findViewById(R.id.scanimageview);
        scan = (Button)findViewById(R.id.scanbutton);
        homebutton = (Button)findViewById(R.id.addscanhomebutton);
        backbutton=(Button)findViewById(R.id.addscanbackbutton);


        homebutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        scan.setOnClickListener(this);

}

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.addscanhomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addscanbackbutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.scanbutton)
        {
            Intent intent = new Intent(this,RetakeImageActivity.class);
            startActivity(intent);
        }


    }
}
