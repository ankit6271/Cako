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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LovedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        String[] x = {"Aaran", "Aaren", "Aarez","Aaran", "Aaren", "Aarez"};
        String[] image = {"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Aloo_Tikki_served_with_chutneys.jpg/180px-Aloo_Tikki_served_with_chutneys.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Aloo_Tikki_served_with_chutneys.jpg/180px-Aloo_Tikki_served_with_chutneys.jpg"
        };
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewForLoved);
        recyclerView.setAdapter(new RecyclerViewForLovedCard(this, x, image));
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
    String[] name;
    String[] image;

    RecyclerViewForLovedCard(Activity activity, String[] name, String[] image) {
        co = activity;
        this.name = name;
        this.image = image;
    }

    @NonNull
    @Override
    public LovedCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(co).inflate(R.layout.layout_for_act_loved, parent, false);
        return new LovedCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LovedCardHolder holder, int position) {
        holder.t1.setText(name[position]);
        Glide.with(co).load(image[position]).into(holder.i1);
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(co,"working add 2 cart",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public static class LovedCardHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3,t4;
        ImageView i1;
        Button b1;

        public LovedCardHolder(@NonNull View itemView) {
            super(itemView);
            i1 = (ImageView) itemView.findViewById(R.id.ImageForLoved);
            t1 = (TextView)itemView.findViewById(R.id.textViewForItemNameInLoved);
            t2 = (TextView)itemView.findViewById(R.id.textViewForPieceInfoInLoved);
            t3 = (TextView)itemView.findViewById(R.id.textViewForPriceInLoved);
            t4 = (TextView)itemView.findViewById(R.id.textViewForDynamicProductDetailInLoved);
            b1 = (Button)itemView.findViewById(R.id.buttonForAddToCartInLoved);
        }
    }

}