package com.example.cako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

         //To delay the launch screen by 3000ms
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentToFirstActivity=new Intent(getApplicationContext(),FirstActivity.class);
                startActivity(intentToFirstActivity);
                finish();
            }
        },1000);

    }
}