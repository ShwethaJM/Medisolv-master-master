package com.android.medisolv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DependentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependents);
    }
    public void OpenDetails(View view)
    {
        Intent intent = new Intent(this,DependentsDetailActivity.class);
        startActivity(intent);
    }
}
