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
import android.os.Handler;
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

public class CartActivity extends AppCompatActivity {
    int price = 0;
    RecyclerViewForCartCard recyclerViewForCartCard;
    ArrayList<String> cakeNames, cakePrices, cakeImage;
    ArrayList<Integer> cakeId;
    TextView textViewOnGoToCheckout;
    ImageView imageView;

    @SuppressLint("Recycle")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForCartActivity);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart_menuitem_for_firstAct);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.home_menuitem_for_firstAct:
                        intent = new Intent(CartActivity.this, FirstActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.cart_menuitem_for_firstAct:
                        break;
                    case R.id.loved_menuitem_for_firstAct:
                        intent = new Intent(CartActivity.this, LovedActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.userInfo_menuitem_for_firstAct:
                        intent = new Intent(CartActivity.this, UserInfoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }
                return true;
            }
        });
        imageView = (ImageView) findViewById(R.id.imageViewForCartEmpty);
        cakeId = new ArrayList<>();
        cakeNames = new ArrayList<>();
        cakePrices = new ArrayList<>();
        cakeImage = new ArrayList<>();
        Database data = new Database(CartActivity.this);
        SQLiteDatabase sqLiteDatabase = data.getReadableDatabase();
        String s = getSharedPreferences("com.example.cako.SharedPreferenceInCako", Context.MODE_PRIVATE).getString("Email", "null");
        assert s != null;
        if (!s.equals("null")) {
            Cursor cursor = sqLiteDatabase.rawQuery("select CakeID from Cart where Email=?", new String[]{s});
            if (cursor != null && cursor.moveToFirst()) {

                do {
                    cakeId.add(cursor.getInt(0));
                } while (cursor.moveToNext());
            }
//            add a image for empty cart
//            else {
//                imageView.setVisibility(View.VISIBLE);
//                Glide.with(CartActivity.this).load("https://www.redufy.com/img/png/empty-cart.png").into(imageView);
//            }

        } else {
            Intent intent = new Intent(CartActivity.this, LoginActivity.class);
            intent.putExtra("Intent Check", "CartActivity");
            startActivity(intent);
            finish();
        }

        if (cakeId.size() != 0) {
            for (int i = 0; i < cakeId.size(); i++) {
                String id = String.valueOf(cakeId.get(i));
                {
                    Cursor c = sqLiteDatabase.rawQuery("Select Name,Price,Image from CakesDetail where cakeId=?", new String[]{id});
                    if (c != null && c.moveToFirst()) {
                        cakeNames.add(c.getString(0));
                        cakePrices.add(c.getString(1));
                        cakeImage.add(c.getString(2));
                    }
                }
            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewForCart);
        recyclerViewForCartCard = new RecyclerViewForCartCard(this, cakeId, cakeNames, cakePrices, cakeImage);
        recyclerView.setAdapter(recyclerViewForCartCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Handler x = new Handler();
        x.postDelayed(new Runnable() {
            @Override
            public void run() {
                price = recyclerViewForCartCard.getPrice();
                String rupee = price + " Rs.";
                textViewOnGoToCheckout = (TextView) findViewById(R.id.textViewOnGoToCheckout);
                textViewOnGoToCheckout.setText(rupee);
            }
        }, 2000);

//        Thread thread=new Thread(this);
//        thread.start();
    }

    public void frameLayoutOnClick(View view) {
        recyclerViewForCartCard = new RecyclerViewForCartCard(this, cakeId, cakeNames, cakePrices, cakeImage);
        int price = recyclerViewForCartCard.getPriceAfterWork();
        Toast.makeText(CartActivity.this, "Payment Gateway for price of Rs " + price, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CartActivity.this, FirstActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

//    @Override
//    public void run() {
//
//        while (true){
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            price=recyclerViewForCartCard.getPriceAfterWork();
//            String rupee = price + " Rs.";
//            textViewOnGoToCheckout = (TextView) findViewById(R.id.textViewOnGoToCheckout);
//            textViewOnGoToCheckout.setText(rupee);
//        }
//
//
//    }
}


class RecyclerViewForCartCard extends RecyclerView.Adapter<RecyclerViewForCartCard.CartCardHolder> {
    Activity co;
    ArrayList<String> cakeNames, cakePrices, cakeImage;
    ArrayList<Integer> cakeId;
    static int count;
    static int price;

    public int getPrice() {
        price = 0;
        for (int i = 0; i < cakePrices.size(); i++) {
            price = price + Integer.parseInt(cakePrices.get(i));
        }
        return price;
    }

    public int getPriceAfterWork() {
        return price;
    }

    RecyclerViewForCartCard(Activity activity, ArrayList<Integer> cakeId, ArrayList<String> cakeNames, ArrayList<String> cakePrices, ArrayList<String> cakeImage) {
        co = activity;
        this.cakeId = cakeId;
        this.cakeNames = cakeNames;
        this.cakePrices = cakePrices;
        this.cakeImage = cakeImage;
    }

    @NonNull
    @Override
    public CartCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(co).inflate(R.layout.layout_for_act_cart, parent, false);
        return new CartCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartCardHolder holder, final int position) {
        int l = 0;
        holder.t1.setText(cakeNames.get(position));
        holder.t3.setText(cakePrices.get(position));
        Glide.with(co).load(cakeImage.get(position)).into(holder.i1);
        l = Integer.parseInt(cakePrices.get(position));
        Log.i("Value", String.valueOf(l) + "inbind");
//        this.setPrice(l);
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Integer.parseInt(holder.countText.getText().toString());
                if (count == 1) {
                    holder.countText.setText("1");
                } else {
                    count--;
                    String s = "" + count;
                    holder.countText.setText(s);
                    int c = Integer.parseInt(cakePrices.get(position));
                    price = price - c;
                    TextView textViewOnGoToCheckout = (TextView) co.findViewById(R.id.textViewOnGoToCheckout);
                    String text = price+" Rs.";
                    textViewOnGoToCheckout.setText(text);
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Integer.parseInt(holder.countText.getText().toString());
                if (count > 0) {
                    count++;
                    String s = "" + count;
                    int c = Integer.parseInt(cakePrices.get(position));
                    holder.countText.setText(s);
                    price = price + c;
                    TextView textViewOnGoToCheckout = (TextView) co.findViewById(R.id.textViewOnGoToCheckout);
                    String text = price+" Rs.";
                    textViewOnGoToCheckout.setText(text);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return cakeNames.size();
    }

    public static class CartCardHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3, countText;
        ImageView i1;
        Button minus, plus;

        public CartCardHolder(@NonNull View itemView) {
            super(itemView);
            minus = itemView.findViewById(R.id.MinusButton);
            plus = itemView.findViewById(R.id.PlusButton);
            i1 = itemView.findViewById(R.id.ImageForCart);
            t1 = itemView.findViewById(R.id.textViewForItemNameInCart);
            t2 = itemView.findViewById(R.id.textViewForPieceInfoInCart);
            t3 = itemView.findViewById(R.id.textViewForPriceInCart);
            countText = itemView.findViewById(R.id.textViewForPieceForCheckoutFromCart);
        }
    }

}