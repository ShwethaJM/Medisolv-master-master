package com.android.medisolv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddScansActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    Button scan,homebutton,backbutton;
    TextView doctorId,patientId;
    Bundle bundle;
    String doctorIdValue,patientIdValue;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scans);

        bundle = getIntent().getExtras();
        //doctorId = (TextView)findViewById(R.id.databasedoctorid);
        doctorIdValue= bundle.getString("id");
        //doctorId.setText(doctorIdValue.trim());

        patientId = (TextView)findViewById(R.id.idfromdbtextView);
        patientIdValue=bundle.getString("PatientId");
        patientId.setText(patientIdValue.trim());


        imageView = (ImageView) this.findViewById(R.id.scanimageview);
        scan = (Button)findViewById(R.id.scanbutton);
        homebutton = (Button)findViewById(R.id.addscanhomebutton);
        backbutton=(Button)findViewById(R.id.addscanbackbutton);


        homebutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        //scan.setOnClickListener(this);

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
                finish();
                Intent intent = new Intent(AddScansActivity.this,WelcomdoctorActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
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

    public void takeImage(View view){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            Intent intent = new Intent(this, RetakeImageActivity.class);
            intent.putExtras(data);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
        @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.addscanhomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId()==R.id.addscanbackbutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        /*else if(v.getId()==R.id.scanbutton)
        {
            Intent intent = new Intent(this,RetakeImageActivity.class);
            startActivity(intent);
        }*/


    }
}
