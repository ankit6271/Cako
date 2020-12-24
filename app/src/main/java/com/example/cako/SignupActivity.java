package com.example.cako;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    String name, email, phone, password;
    Database data;
    SQLiteDatabase database;
    EditText nameEditText, emailEditText, passwordEditText, mobileEditText;
    TextView textViewForIncorrectInSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEditText = (EditText) findViewById(R.id.editTextForNameInSignup);
        emailEditText = (EditText) findViewById(R.id.editTextForEmailAddressInSignup);
        passwordEditText = (EditText) findViewById(R.id.editTextForPasswordInSignup);
        mobileEditText = (EditText) findViewById(R.id.editTextForPhoneNoInSignup);

        Button buttonForSignupInSignup = (Button) findViewById(R.id.buttonForSignupInSignup);
        buttonForSignupInSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = mobileEditText.getText().toString();
                password = passwordEditText.getText().toString();
                textViewForIncorrectInSignUp= (TextView) findViewById(R.id.textViewForIncorrectInSignUp);

                data = new Database(SignupActivity.this);
                database = data.getWritableDatabase();
                if (name .equals("") && password .equals("") && phone .equals("") && email .equals("")) {
                    textViewForIncorrectInSignUp.setText("MISSING FIELDS");
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textViewForIncorrectInSignUp.setText("");
                        }
                    },5000);
                } else {
                    @SuppressLint("Recycle") Cursor cursor = database.rawQuery("Select email from Signup where email=?", new String[]{email});
                    if (cursor != null && cursor.moveToFirst()) {
                        textViewForIncorrectInSignUp.setText("E-Mail Already Exist");
                    } else {
                        ContentValues values = new ContentValues();
                        values.put("Name", name);
                        Log.e("Email", name);

                        values.put("Email", email);
                        Log.e("Email", email);

                        values.put("Phone", phone);
                        Log.e("Email", phone);

                        values.put("Password", password);
                        Log.e("Email", password);

                        database.insert("Signup", null, values);

                        //intent to login as user needs to enter the details again so that he/she can enter the app
                        Intent intentToFirstActivity = new Intent(SignupActivity.this, LoginActivity.class);
                        intentToFirstActivity.putExtra("Intent Check", "SignupActivity");
                        startActivity(intentToFirstActivity);
                        finish();
                    }
                }
            }

        });
    }
}