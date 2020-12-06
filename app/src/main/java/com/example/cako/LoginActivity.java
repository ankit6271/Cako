package com.example.cako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i=getIntent();
        final String action=i.getAction();

        EditText emailEditText = (EditText) findViewById(R.id.editTextForEmailAddressInLogin);
        EditText passwordEditText = (EditText) findViewById(R.id.editTextForPasswordInLogin);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

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

                assert action != null;
                //Intent received is from UserInfoActivity
                if(action.equals("UserInfoActivity")){
                    Intent i=new Intent(LoginActivity.this,UserInfoActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent intentToFirstActivity = new Intent(getApplicationContext(), FirstActivity.class);
                    startActivity(intentToFirstActivity);
                    finish();
                }

            }
        });
    }
}

