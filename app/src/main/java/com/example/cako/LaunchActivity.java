package com.example.cako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class LaunchActivity extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //To delay the launch screen by 1000ms
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pref = getSharedPreferences("com.example.cako.SharedPreferenceInCako", MODE_PRIVATE);
                String s=pref.getString("Name", "null");
                assert s != null;
                if(s.equals("null")){
                    Intent intentToFirstActivity = new Intent(getApplicationContext(), LoginActivity.class);
                    intentToFirstActivity.putExtra("Intent Check","LaunchActivity");
                    startActivity(intentToFirstActivity);
                    finish();
                }
                else{
                    Intent intentToFirstActivity = new Intent(getApplicationContext(), FirstActivity.class);
                    startActivity(intentToFirstActivity);
                    finish();
                }
            }
        }, 1500);
    }
}