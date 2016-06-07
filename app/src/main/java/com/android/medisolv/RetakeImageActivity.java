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
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retake_image);
        bundle = getIntent().getExtras();

        Bitmap bimageView = (Bitmap)getIntent().getExtras().get("data");

        homebutton=(Button)findViewById(R.id.retakescanhomebutton);
        backbutton=(Button)findViewById(R.id.retakebackbutton);
        retakebutton=(Button)findViewById(R.id.retakebutton);
        imageView = (ImageView)findViewById(R.id.retakeImageView);
        imageView.setImageBitmap(bimageView);

        homebutton.setOnClickListener(this);
        backbutton.setOnClickListener(this);
       // retakebutton.setOnClickListener(this);
    }
    public void retakeImage(View view){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
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
        /*else if(v.getId()==R.id.retakebutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            startActivity(intent);
        }*/

    }
}
