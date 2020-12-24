package com.example.cako;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.transition.AutoTransition;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class UserInfoActivity extends AppCompatActivity {
    CardView cardViewForAddress, cardViewForSettings, cardViewForAboutApp, cardViewForLogInLogout;
    ImageView button;
    ConstraintLayout constraintLayout;
    EditText houseEdit, landmarkEdit;
    TextView textViewForLogInLogout, nameOfUser, emailIdOfUser, phoneNoOfUser;
    Button saveAndProceed;
    String house, landmark;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Database database;
    SQLiteDatabase sqLiteDatabase;


    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        database = new Database(UserInfoActivity.this);
        sqLiteDatabase = database.getWritableDatabase();

        nameOfUser = (TextView) findViewById(R.id.nameOfUser);
        emailIdOfUser = (TextView) findViewById(R.id.emailIdofUser);
        phoneNoOfUser = (TextView) findViewById(R.id.phoneNoOfUser);
        textViewForLogInLogout = (TextView) findViewById(R.id.textViewForLogInLogout);

        preferences = getSharedPreferences("com.example.cako.SharedPreferenceInCako", MODE_PRIVATE);
        editor = preferences.edit();
        String s = preferences.getString("Name", "null");

        assert s != null;
        if (s.equals("null")) {
            nameOfUser.setText("User");
            emailIdOfUser.setText("No Email");
            phoneNoOfUser.setText("No PhoneNo.");
            textViewForLogInLogout.setText("LOGIN");
        } else {
            String name = preferences.getString("Name", "null");
            String email = preferences.getString("Email", "null");
            String phone = preferences.getString("Phone", "null");
            nameOfUser.setText(name);
            emailIdOfUser.setText(email);
            phoneNoOfUser.setText(phone);
            textViewForLogInLogout.setText("LOGOUT");
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.userInfo_menuitem_for_firstAct);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.home_menuitem_for_firstAct:
                        intent = new Intent(UserInfoActivity.this, FirstActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.cart_menuitem_for_firstAct:
                        intent = new Intent(UserInfoActivity.this, CartActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.loved_menuitem_for_firstAct:
                        intent = new Intent(UserInfoActivity.this, LovedActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.userInfo_menuitem_for_firstAct:
                        break;
                }
                return true;
            }
        });
        final TextView textForShippingAddressHard = (TextView) findViewById(R.id.textForShippingAddressHard);
        final TextView textViewForAddressSaved = (TextView) findViewById(R.id.textViewForAddressSaved);
        final TextView textViewForLandmark = (TextView) findViewById(R.id.textViewForLandmark);
        final TextView textViewForHouseAddress = (TextView) findViewById(R.id.textViewForHouseAddress);
        houseEdit = (EditText) findViewById(R.id.editTextForHouseAddress);
        landmarkEdit = (EditText) findViewById(R.id.editTextForLandmark);
        saveAndProceed = (Button) findViewById(R.id.saveAndProceed);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintForAdressVisibleOrNot);
        button = (ImageView) findViewById(R.id.arrowButtonInAddress);
        button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        if(AppCompatDelegate.MODE_NIGHT_YES==AppCompatDelegate.getDefaultNightMode()){
            DrawableCompat.setTint(Objects.requireNonNull(getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24)), Color.RED);
        }
        else{
            DrawableCompat.setTint(Objects.requireNonNull(getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24)), Color.RED);
        }
        cardViewForAddress = (CardView) findViewById(R.id.cardViewForAddress);
        cardViewForAddress.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (constraintLayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewForAddress, new AutoTransition());
                    button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    constraintLayout.setVisibility(View.VISIBLE);
                    String add = preferences.getString("Address", "null");
                    String email = preferences.getString("Email", "null");
                    assert add != null;
                    //For editText and textview for address to input data
                    if (add.equals("null")) {
                        assert email != null;
                        //to crosscheck if user is loggedIn or not
                        if (email.equals("null")) {
                            textForShippingAddressHard.setVisibility(View.VISIBLE);
                            textForShippingAddressHard.setText("PLEASE LOGIN FIRST");
                            textForShippingAddressHard.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                        } else {
                            houseEdit.setVisibility(View.VISIBLE);
                            landmarkEdit.setVisibility(View.VISIBLE);
                            textViewForHouseAddress.setVisibility(View.VISIBLE);
                            textViewForLandmark.setVisibility(View.VISIBLE);
                            saveAndProceed.setVisibility(View.VISIBLE);
                        }
                    }
                    //If address already there just show address
                    else {
                        textForShippingAddressHard.setVisibility(View.VISIBLE);
                        textViewForAddressSaved.setVisibility(View.VISIBLE);
                        textViewForAddressSaved.setText(add);
                    }

                } else {
                    TransitionManager.beginDelayedTransition(cardViewForAddress, new AutoTransition());
                    button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    constraintLayout.setVisibility(View.GONE);
                    houseEdit.setVisibility(View.GONE);
                    landmarkEdit.setVisibility(View.GONE);
                    textViewForHouseAddress.setVisibility(View.GONE);
                    textViewForLandmark.setVisibility(View.GONE);
                    saveAndProceed.setVisibility(View.GONE);
                    textViewForLandmark.setVisibility(View.GONE);
                    textViewForHouseAddress.setVisibility(View.GONE);
                }
            }
        });

        cardViewForSettings = (CardView) findViewById(R.id.cardViewForSettingsInUser);
        cardViewForSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserInfoActivity.this, SettingsActivity.class);
                i.putExtra("IntentGoing","UserInfoActivity");
                startActivity(i);
                finish();
            }
        });

        cardViewForAboutApp = (CardView) findViewById(R.id.cardViewForAboutInUser);
        cardViewForAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserInfoActivity.this, AboutApp.class);
                startActivity(i);
            }
        });

        cardViewForLogInLogout = (CardView) findViewById(R.id.cardViewForLogInLogout);
        cardViewForLogInLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = preferences.getString("Name", "null");
                assert s != null;
                if (s.equals("null")) {
                    Intent intentToFirstActivity = new Intent(view.getContext(), LoginActivity.class);
                    //In oredr to diffreentiate at logIn wheather it should call FirstActivity or UserInfoActivity.
                    // As there could be possibility that login is from launch
                    intentToFirstActivity.putExtra("Intent Check", "UserInfoActivity");
                    startActivity(intentToFirstActivity);
                    finish();

                } else {
                    editor.putString("Name", "null");
                    editor.putString("Phone", "null");
                    editor.putString("Email", "null");
                    editor.putString("Address", "null");
                    editor.apply();
                    recreate();
                }
            }
        });
        saveAndProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                house = houseEdit.getText().toString();
                landmark = landmarkEdit.getText().toString();
                String fullAddress = house + "," + landmark;
                //putting to get easy access when logging in or checking also
                editor.putString("Address", fullAddress);
                editor.apply();
                Log.i("E", fullAddress);
                //when clicked constraint layout to be closed so that with new start it can show the Address
                constraintLayout.setVisibility(View.GONE);
                houseEdit.setVisibility(View.GONE);
                landmarkEdit.setVisibility(View.GONE);
                textViewForHouseAddress.setVisibility(View.GONE);
                textViewForLandmark.setVisibility(View.GONE);
                saveAndProceed.setVisibility(View.GONE);

                ContentValues values = new ContentValues();
                values.put("Email", preferences.getString("Email", null));
                values.put("Address", fullAddress);
                sqLiteDatabase.insert("Address", null, values);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserInfoActivity.this, FirstActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();

    }
}