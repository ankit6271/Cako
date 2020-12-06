package com.example.cako;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity {
    CardView cardViewForDarkMode, cardViewForFaq;
    String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbarForSettingActivity);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        cardViewForDarkMode = (CardView) findViewById(R.id.cardViewForDarkMode);
        cardViewForDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theme ="Dark";
                Log.e("RAM","Theme==DArk in opening");
                String[] mode = {"Dark Mode", "Light Mode"};
                final Dialog dialog = new Dialog(SettingsActivity.this);
                final AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                alert.setTitle("Select Theme");
                alert.setSingleChoiceItems(mode, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                theme = "Dark";
                                Log.e("RAM","Theme==DArk choice sel");
                                break;
                            case 1:
                                theme = "Light";
                                Log.e("RAM","Theme==Light choice sel");
                                break;
                        }
                    }
                });
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (theme.toString()) {
                            case "Dark":
                                Log.e("RAM","Theme==DArk OK");
                                Toast.makeText(SettingsActivity.this, "DArk", Toast.LENGTH_SHORT).show();
                                theme="";
                                break;
                            case "Light":
                                Log.e("RAM","Theme==Light OK");
                                Toast.makeText(SettingsActivity.this, "Light", Toast.LENGTH_SHORT).show();
                                theme="";
                                break;
                        }

                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                alert.show();
            }
        });

        cardViewForFaq = (CardView) findViewById(R.id.cardViewForFaq);
        cardViewForFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingsActivity.this, FaqActivity.class);
                startActivity(i);
            }
        });


    }
}