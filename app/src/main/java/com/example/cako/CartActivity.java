package com.example.cako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {

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

        String[] x = {"Aaran", "Aaren", "Aarez","Aaran", "Aaren", "Aarez"};
        String[] image = {"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Aloo_Tikki_served_with_chutneys.jpg/180px-Aloo_Tikki_served_with_chutneys.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Aloo_Tikki_served_with_chutneys.jpg/180px-Aloo_Tikki_served_with_chutneys.jpg"

        };
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewForCart);
        recyclerView.setAdapter(new RecyclerViewForCartCard(this, x, image));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void frameLayoutOnClick(View view) {
        Toast.makeText(this, "Payment Gateway", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CartActivity.this, FirstActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
class RecyclerViewForCartCard extends RecyclerView.Adapter<RecyclerViewForCartCard.CartCardHolder> {
    Activity co;
    String[] name;
    String[] image;

    RecyclerViewForCartCard(Activity activity, String[] name, String[] image) {
        co = activity;
        this.name = name;
        this.image = image;
    }

    @NonNull
    @Override
    public CartCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(co).inflate(R.layout.layout_for_act_cart, parent, false);
        return new CartCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartCardHolder holder, int position) {
        holder.t1.setText(name[position]);
        Glide.with(co).load(image[position]).into(holder.i1);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public static class CartCardHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3;
        ImageView i1;

        public CartCardHolder(@NonNull View itemView) {
            super(itemView);
            i1 = itemView.findViewById(R.id.ImageForCart);
            t1 = itemView.findViewById(R.id.textViewForItemNameInCart);
            t2 = itemView.findViewById(R.id.textViewForPieceInfoInCart);
            t3 = itemView.findViewById(R.id.textViewForPriceInCart);
        }
    }

}