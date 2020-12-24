package com.example.cako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class LovedActivity extends AppCompatActivity {
    ArrayList<String> cakeNames, cakePrices, cakeImage, cakeDetail;
    ArrayList<Integer> cakeId;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        imageView = (ImageView) findViewById(R.id.imageViewForLovedEmpty);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loved);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForLovedActivity);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.loved_menuitem_for_firstAct);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.home_menuitem_for_firstAct:
                        intent = new Intent(LovedActivity.this, FirstActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.cart_menuitem_for_firstAct:
                        intent = new Intent(LovedActivity.this, CartActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.loved_menuitem_for_firstAct:
                        break;
                    case R.id.userInfo_menuitem_for_firstAct:
                        intent = new Intent(LovedActivity.this, UserInfoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }
                return true;
            }
        });
        cakeId = new ArrayList<>();
        cakeDetail = new ArrayList<>();
        cakeNames = new ArrayList<>();
        cakePrices = new ArrayList<>();
        cakeImage = new ArrayList<>();
        Database data = new Database(LovedActivity.this);
        SQLiteDatabase sqLiteDatabase = data.getReadableDatabase();
        String s = getSharedPreferences("com.example.cako.SharedPreferenceInCako", Context.MODE_PRIVATE).getString("Email", "null");
        assert s != null;
        if (!s.equals("null")) {
            @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("select CakeID from Loved where Email=?", new String[]{s});
            if (cursor != null && cursor.moveToFirst()) {
//                imageView = (ImageView) findViewById(R.id.imageViewForLovedEmpty);
//                if (imageView.getVisibility()==View.VISIBLE) {
//                    imageView.setVisibility(View.GONE);
//                }
                do {
                    cakeId.add(cursor.getInt(0));
                } while (cursor.moveToNext());
            }
//            else {
//                imageView = (ImageView) findViewById(R.id.imageViewForLovedEmpty);
//                if (imageView.getVisibility()==View.GONE) {
//                    imageView.setVisibility(View.VISIBLE);
//                }
//                Glide.with(LovedActivity.this).load("https://c8.alamy.com/comp/RECHC5/cute-sad-cartoon-animal-with-heart-shaped-balloon-inscription-im-sorry-forgive-me-please-isolated-on-white-background-eps8-RECHC5.jpg").into(imageView);
//            }
        } else {
            Intent intent = new Intent(LovedActivity.this, LoginActivity.class);
            intent.putExtra("Intent Check", "LovedActivity");
            startActivity(intent);
            finish();
        }

        if (cakeId.size() != 0) {
            for (int i = 0; i < cakeId.size(); i++) {
                String id = String.valueOf(cakeId.get(i));
                {
                    @SuppressLint("Recycle") Cursor c = sqLiteDatabase.rawQuery("Select Name,Price,Image,Detail from CakesDetail where cakeId=?", new String[]{id});
                    if (c != null && c.moveToFirst()) {
                        cakeNames.add(c.getString(0));
                        cakePrices.add(c.getString(1));
                        cakeImage.add(c.getString(2));
                        cakeDetail.add(c.getString(3));
                    }

                }
            }
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewForLoved);
        recyclerView.setAdapter(new RecyclerViewForLovedCard(this, cakeDetail, cakeId, cakeNames, cakePrices, cakeImage));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LovedActivity.this, FirstActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}

class RecyclerViewForLovedCard extends RecyclerView.Adapter<RecyclerViewForLovedCard.LovedCardHolder> {
    Activity co;
    ArrayList<String> cakeNames, cakePrices, cakeImage, cakeDetail;
    ArrayList<Integer> cakeId,cursorValues;

    SQLiteDatabase sqLiteDatabase;
    Database database;
    String sharedPref;

    RecyclerViewForLovedCard(Activity activity, ArrayList<String> cakeDetail, ArrayList<Integer> cakeId, ArrayList<String> cakeNames, ArrayList<String> cakePrices, ArrayList<String> cakeImage) {
        co = activity;
        this.cakeId = cakeId;
        this.cakeNames = cakeNames;
        this.cakePrices = cakePrices;
        this.cakeImage = cakeImage;
        this.cakeDetail = cakeDetail;
        sharedPref = co.getSharedPreferences("com.example.cako.SharedPreferenceInCako", Context.MODE_PRIVATE).getString("Email", "null");
        database=new Database(co);
        sqLiteDatabase=database.getWritableDatabase();

    }

    @NonNull
    @Override
    public LovedCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(co).inflate(R.layout.layout_for_act_loved, parent, false);
        return new LovedCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LovedCardHolder holder, final int position) {
        holder.t1.setText(cakeNames.get(position));
        holder.t3.setText(cakePrices.get(position));
        holder.t4.setText(cakeDetail.get(position));
        Glide.with(co).load(cakeImage.get(position)).into(holder.i1);
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursorValues=new ArrayList<>();
                ContentValues values = new ContentValues();
                String s = sharedPref;
                assert s != null;
                Log.e("Email", s);
                if (!s.equals("null")) {
                    Log.e("Email", s);
                    @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("Select CakeId from Cart where Email=?", new String[]{s});
                    if (cursor != null && cursor.moveToFirst()) {
                        int t =cakeId.get(position);
                        do {
                            Log.e("Email", s);
                            Integer value = cursor.getInt(0);
                            cursorValues.add(value);
                        }while (cursor.moveToNext());
                        if(!cursorValues.contains(t)){
                            Log.e("Email", "cake id" + cakeId);
                            values.put("CakeID", cakeId.get(position));
                            values.put("Email", s);
                            sqLiteDatabase.insert("Cart", null, values);
                            Toast.makeText(co, "Added to Cart", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(co, "Already in Cart", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        values.put("cakeID", cakeId.get(position));
                        values.put("Email", s);
                        sqLiteDatabase.insert("Cart", null, values);
                        Toast.makeText(co, "Added to Cart", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cakeNames.size();
    }

    public static class LovedCardHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3, t4;
        ImageView i1;
        Button b1;

        public LovedCardHolder(@NonNull View itemView) {
            super(itemView);
            i1 = (ImageView) itemView.findViewById(R.id.ImageForLoved);
            t1 = (TextView) itemView.findViewById(R.id.textViewForItemNameInLoved);
            t2 = (TextView) itemView.findViewById(R.id.textViewForPieceInfoInLoved);
            t3 = (TextView) itemView.findViewById(R.id.textViewForPriceInLoved);
            t4 = (TextView) itemView.findViewById(R.id.textViewForDynamicProductDetailInLoved);
            b1 = (Button) itemView.findViewById(R.id.buttonForAddToCartInLoved);
        }
    }

}