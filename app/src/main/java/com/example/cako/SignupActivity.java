package com.example.cako;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText emailEditText=(EditText)findViewById(R.id.editTextForEmailAddressInSignup);
        EditText passwordEditText=(EditText)findViewById(R.id.editTextForPasswordInSignup);
        EditText mobileEditText=(EditText)findViewById(R.id.editTextForPhoneNoInSignup);


        String email=emailEditText.getText().toString();
        String mobile=mobileEditText.getText().toString();
        String password=passwordEditText.getText().toString();



        Button buttonForSignupInSignup = (Button) findViewById(R.id.buttonForSignupInSignup);
        buttonForSignupInSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}