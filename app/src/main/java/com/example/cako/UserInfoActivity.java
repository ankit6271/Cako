package com.example.cako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserInfoActivity extends AppCompatActivity {
    CardView cardViewForAddress, cardViewForSettings, cardViewForAboutApp;
    ImageView button;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

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

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintForAdressVisibleOrNot);
        button = (ImageView) findViewById(R.id.arrowButtonInAddress);
        button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        cardViewForAddress = (CardView) findViewById(R.id.cardViewForAddress);
        cardViewForAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (constraintLayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewForAddress, new AutoTransition());
                    button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    constraintLayout.setVisibility(View.VISIBLE);

//                    button.setBackgroundResource(0);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewForAddress, new AutoTransition());
                    button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    constraintLayout.setVisibility(View.GONE);
                }
            }
        });

        cardViewForSettings = (CardView) findViewById(R.id.cardViewForSettingsInUser);
        cardViewForSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserInfoActivity.this, SettingsActivity.class);
                startActivity(i);
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
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserInfoActivity.this, FirstActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();

    }
}