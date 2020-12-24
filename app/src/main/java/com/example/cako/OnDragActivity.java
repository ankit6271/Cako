package com.example.cako;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OnDragActivity extends AppCompatActivity {
    ArrayList<Integer> cursorValues;
    SQLiteDatabase sqLiteDatabase;
    Database database;
    CardView cardView;
    ConstraintLayout constraintLayout;
    ImageView imageView;
    int id;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_for_on_drag_in_first_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForLong);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra("Id",0);
        String picture = getIntent().getStringExtra("Picture");
        String PieceInfo = getIntent().getStringExtra("PieceInfo");
        String ItemName = getIntent().getStringExtra("ItemName");
        String price = getIntent().getStringExtra("Price");

        ImageView imageForLongPressedAct = (ImageView) findViewById(R.id.imageForLongPressedAct);
        Glide.with(OnDragActivity.this).load(picture).into(imageForLongPressedAct);

        TextView textForCakeInLong = (TextView) findViewById(R.id.textForCakeInLong);
        textForCakeInLong.setText(ItemName);

        TextView textForCakePriceInLong = (TextView) findViewById(R.id.textForCakePriceInLong);
        textForCakePriceInLong.setText(price);

        TextView textForCakeDetailInLong = (TextView) findViewById(R.id.textForCakeDetailInLong);
        textForCakeDetailInLong.setText(PieceInfo);

        database = new Database(OnDragActivity.this);
        sqLiteDatabase = database.getWritableDatabase();

        ImageButton buttonForLovedInLong = (ImageButton) findViewById(R.id.buttonForLovedInLong);
        buttonForLovedInLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorValues=new ArrayList<>();
                ContentValues values = new ContentValues();
                String s = getSharedPreferences("com.example.cako.SharedPreferenceInCako", Context.MODE_PRIVATE).getString("Email", "null");
                assert s != null;
                Log.e("Email", s);
                if (!s.equals("null")) {
                    Log.e("Email", s);
                    @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("Select CakeId from Loved where Email=?", new String[]{s});
                    if (cursor != null && cursor.moveToFirst()) {
                        int t =id;
                        do {
                            Log.e("Email", s);
                            int value = cursor.getInt(0);
                            cursorValues.add(value);
                            Log.e("Email","value added to arraylist"+String.valueOf(value));
                        }while (cursor.moveToNext());
                        if(!cursorValues.contains(t)){
                            Log.e("Email", "cake id" + id);
                            values.put("CakeID", id);
                            values.put("Email", s);
                            sqLiteDatabase.insert("Loved", null, values);
                            Toast.makeText(OnDragActivity.this, "Added to Loved", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(OnDragActivity.this, "Already in Loved", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        values.put("CakeID", id);
                        values.put("Email", s);
                        sqLiteDatabase.insert("Loved", null, values);
                        Toast.makeText(OnDragActivity.this, "Added to Loved", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Button but = (Button) findViewById(R.id.buttonForAddToCartInOnDrag);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorValues=new ArrayList<>();
                ContentValues values = new ContentValues();
                String s = getSharedPreferences("com.example.cako.SharedPreferenceInCako", Context.MODE_PRIVATE).getString("Email", "null");
                assert s != null;
                Log.e("Email", s);
                if (!s.equals("null")) {
                    Log.e("Email", s);
                    @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("Select CakeId from Cart where Email=?", new String[]{s});
                    if (cursor != null && cursor.moveToFirst()) {
                        int t =id;
                        do {
                            int value = cursor.getInt(0);
                            cursorValues.add(value);
                            Log.e("Email","value added to arraylist"+String.valueOf(value));
                        }while (cursor.moveToNext());
                        if(!cursorValues.contains(t)){
                            Log.e("Email", "cake id" + id);
                            values.put("CakeID", id);
                            values.put("Email", s);
                            sqLiteDatabase.insert("Cart", null, values);
                            Toast.makeText(OnDragActivity.this, "Added to Cart", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(OnDragActivity.this, "Already in Cart", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        values.put("CakeID", id);
                        values.put("Email", s);
                        sqLiteDatabase.insert("Cart", null, values);
                        Toast.makeText(OnDragActivity.this, "Added to Cart", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        imageView = (ImageView)

                findViewById(R.id.arrowButtonInLong);
        imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        constraintLayout = (ConstraintLayout)

                findViewById(R.id.constraintForCakeDetailVisibleOrNotInLong);

        cardView = (CardView)

                findViewById(R.id.cardViewForLong);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (constraintLayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    constraintLayout.setVisibility(View.VISIBLE);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    constraintLayout.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(OnDragActivity.this, FirstActivity.class);
        startActivity(i);
        finish();
    }
}
