package com.android.medisolv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RetakeImageActivity extends AppCompatActivity implements View.OnClickListener {

    Button homebutton,backbutton,retakebutton;
    Bitmap mphoto;
    private static final int CAMERA_REQUEST = 1888;

    ImageView imageView;

    TextView doctorId,patientId;
    String doctorIdValue,patientIdValue;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retake_image);
        bundle = getIntent().getExtras();

        mphoto= (Bitmap)getIntent().getExtras().get("data");


        doctorIdValue= bundle.getString("id");
        patientId = (TextView)findViewById(R.id.idfromdbtextView);
        patientIdValue=bundle.getString("PatientId");
        patientId.setText(patientIdValue.trim());


        homebutton=(Button)findViewById(R.id.retakescanhomebutton);
        backbutton=(Button)findViewById(R.id.retakebackbutton);

        imageView = (ImageView)findViewById(R.id.retakeImageView);
        imageView.setImageBitmap(mphoto);

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
            mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
    }

    public void saveScannedImage(View view){
        /*Code to save the image (mphoto)*/
        Toast.makeText(RetakeImageActivity.this, "Your Image is saving...", Toast.LENGTH_LONG).show();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp+".jpg";
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Medicosolv";
        File storageDir =new File(filePath);
        if(!storageDir.exists()){
            storageDir.mkdir();
            Toast.makeText(RetakeImageActivity.this, "Directory got created", Toast.LENGTH_LONG).show();
        }

        try {
            File image = new File(storageDir, imageFileName);
            FileOutputStream out = new FileOutputStream(image);
            mphoto.compress(Bitmap.CompressFormat.JPEG, 85, out);
            out.flush();
            out.close();
            /*Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(image);
            mediaScanIntent.setData(contentUri);
            getApplicationContext().sendBroadcast(mediaScanIntent);*/
            Toast.makeText(RetakeImageActivity.this, "Your Image saved successfully.", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(RetakeImageActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.retakescanhomebutton)
        {
            Intent intent = new Intent(this,WelcomdoctorActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.retakebackbutton)
        {
            Intent intent = new Intent(this,AddScansActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

    }
}
