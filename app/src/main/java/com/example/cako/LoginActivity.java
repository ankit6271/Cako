package com.example.cako;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Database data;
    SQLiteDatabase database;
    String email, password, passwordRec, name, phone;
    TextView incorrect;
    SharedPreferences preferences;
    EditText emailEditText, passwordEditText;
    SharedPreferences.Editor editor;
    String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();
        action = i.getStringExtra("Intent Check");

        emailEditText = (EditText) findViewById(R.id.editTextForEmailAddressInLogin);
        passwordEditText = (EditText) findViewById(R.id.editTextForPasswordInLogin);
        incorrect = (TextView) findViewById(R.id.textViewForIncorrect);

        Button buttonForSignupInLogin = (Button) findViewById(R.id.buttonForSignupInLogin);
        buttonForSignupInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button buttonForLoginInLogin = (Button) findViewById(R.id.buttonForLoginInLogin);
        buttonForLoginInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEditText.getText().toString();
                Log.e("Email", email);

                password = passwordEditText.getText().toString();
                Log.e("Email", password);

                data = new Database(LoginActivity.this);
                database = data.getReadableDatabase();
                if (email .equals("") && password.equals("")) {
                    incorrect.setText("MISSING FIELDS");
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            incorrect.setText("");
                        }
                    },5000);
                }
                else {
                    @SuppressLint("Recycle")
                    Cursor cursor = database.rawQuery("Select name,phone,password from signup where email=?", new String[]{email});
                    //value for email exist but password can be true or not
                    if (cursor.moveToFirst()) {
                        Log.e("Email", "count not zero");

                        passwordRec = cursor.getString(2);
                        Log.e("Email", passwordRec);

                        phone = cursor.getString(1);
                        Log.e("Email", phone);

                        name = cursor.getString(0);
                        Log.e("Email", name);

                        if (passwordRec.equals(password)) {
                            //Seting prefernce for to check login
                            preferences = getSharedPreferences("com.example.cako.SharedPreferenceInCako", MODE_PRIVATE);
                            editor = preferences.edit();
                            editor.putString("Name", name);
                            editor.putString("Phone", phone);
                            editor.putString("Email", email);
                            //to check whether address exist in Address table or not
                            @SuppressLint("Recycle") Cursor cursor1 = database.rawQuery("Select Address from Address where email=?", new String[]{email});
                            if (!cursor1.moveToFirst()) {
                                editor.putString("Address", "null");
                            } else {
                                String s = cursor1.getString(0);
                                editor.putString("Address", s);
                            }
                            editor.apply();

                            switch (action) {
                                //Intent received is from UserInfoActivity
                                case "UserInfoActivity":
                                    Intent i = new Intent(LoginActivity.this, UserInfoActivity.class);
                                    startActivity(i);
                                    finish();
                                case "CartActivity":
                                    Intent cart = new Intent(LoginActivity.this, CartActivity.class);
                                    startActivity(cart);
                                    finish();
                                case "LovedActivity":
                                    Intent loved = new Intent(LoginActivity.this, LovedActivity.class);
                                    startActivity(loved);
                                    finish();
                                    //Intent is from launch Activity or any other like signup
                                default:
                                    Intent intentToFirstActivity = new Intent(LoginActivity.this, FirstActivity.class);
                                    startActivity(intentToFirstActivity);
                                    finish();
                            }
                        } else {
                            incorrect.setText("INCORRECT PASSWORD");
                        }
                    }
                    //If cursor==Null means email wont exist
                    else {
                        incorrect.setText("E-MAIL ID DOESN'T EXIST");
                    }
                }

            }

        });
    }
}

