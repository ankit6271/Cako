package com.example.cako;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.GestureDescription;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class FirstActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_activity_first);

        String[] x = {"Aaran", "Aaren", "Aarez", "Aarman", "Aaron", "Aaron-James", "Aarron", "Aaryan", "Abhinav", "Ankit"};
        String[] image = {"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Aloo_Tikki_served_with_chutneys.jpg/180px-Aloo_Tikki_served_with_chutneys.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Aloo_Mattar.jpg/180px-Aloo_Mattar.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Aloo_Methi_%28Aaloo_Methi%29.JPG/180px-Aloo_Methi_%28Aaloo_Methi%29.JPG",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Spicy_alloo_with_tadka_mirchi.jpg/180px-Spicy_alloo_with_tadka_mirchi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Aloo_gobi.jpg/180px-Aloo_gobi.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Aloo_Tikki_served_with_chutneys.jpg/180px-Aloo_Tikki_served_with_chutneys.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Aloo_Mattar.jpg/180px-Aloo_Mattar.jpg"
        };
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_open_24);
        setSupportActionBar(toolbar);

        RecyclerView recyclerViewForCakeTypes = (RecyclerView) findViewById(R.id.recyclerViewForCakeTypes);
        recyclerViewForCakeTypes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewForCakeTypes.setAdapter(new RecyclerVviewForDessertTypes(this, x, image));
        //Wanted to add space between REcycler view elements
        DividerItemDecoration dividerItemDecorationForDessert = new DividerItemDecoration(this, RecyclerView.HORIZONTAL);
        dividerItemDecorationForDessert.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.divider_recycler_vertical_direction)));
        recyclerViewForCakeTypes.addItemDecoration(dividerItemDecorationForDessert);


        RecyclerView horizontalrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHorizontal);
        horizontalrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontalrecyclerView.setAdapter(new RecyclerVview(this, x, image));
        //Wanted to add space between REcycler view elements
        DividerItemDecoration dividerItemDecorationHorizontal = new DividerItemDecoration(this, RecyclerView.HORIZONTAL);
        dividerItemDecorationHorizontal.setDrawable((Objects.requireNonNull(getDrawable(R.drawable.divider_recycler_vertical_direction))));
        horizontalrecyclerView.addItemDecoration(dividerItemDecorationHorizontal);

        RecyclerView verticalrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewVertical);
        verticalrecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        verticalrecyclerView.setAdapter(new RecyclerVview(this, x, image));
        //Wanted to add space between REcycler view elements
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.divider_recycler_horizontal_direction)));
        verticalrecyclerView.addItemDecoration(dividerItemDecoration);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        View v = navigationView.getHeaderView(0);
        ImageView i = (ImageView) v.findViewById(R.id.circularImageViewForNavView);
        Glide.with(this).load(image[5]).into(i);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(FirstActivity.this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClosed);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_nav_view:
                        Log.i("CloseDrawe", "close");
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home_menuitem_for_firstAct);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menuitem_for_firstAct:
                        break;
                    case R.id.cart_menuitem_for_firstAct:
                        Intent intent = new Intent(FirstActivity.this, CartActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.loved_menuitem_for_firstAct:
                        Intent intent2 = new Intent(FirstActivity.this, LovedActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        finish();

                        break;
                    case R.id.userInfo_menuitem_for_firstAct:
                        Intent intent3 = new Intent(FirstActivity.this, UserInfoActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        finish();

                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_toolbar_first_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(FirstActivity.this, OfferActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

class RecyclerVviewForDessertTypes extends RecyclerView.Adapter<RecyclerVviewForDessertTypes.Holder> {
    Activity co;
    String[] name;
    String[] image;

    RecyclerVviewForDessertTypes(Activity activity, String[] name, String[] image) {
        co = activity;
        this.name = name;
        this.image = image;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(co).inflate(R.layout.layout_for_caketypes, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(name[position]);
        Glide.with(co).load(image[position]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return image.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textViewForDessertName);
            imageView = (ImageView) itemView.findViewById(R.id.imageForTypeOfDessert);
        }
    }
}


class RecyclerVview extends RecyclerView.Adapter<RecyclerVview.Holder> {
    Activity co;
    String[] name;
    String[] image;
    GestureDetector gestureDetector;

    public RecyclerVview(Context applicationContext, String[] x, String[] image) {
        co = (Activity) applicationContext;
        name = x;
        this.image = image;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(co).inflate(R.layout.layout_for_horizontal_recyclerview, parent, false);
        return new Holder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        holder.textViewForItemName.setText(name[position]);
        Glide.with(co).load(image[position]).into(holder.image);
        holder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(co, "yesWorking", Toast.LENGTH_SHORT).show();
                Log.i("yes", "log");
            }
        });
        holder.textViewForPrice.setText("99$");
        holder.textViewForPieceInfo.setText("Single Piece Only.");
        gestureDetector = new GestureDetector(co, new Gesture(co, holder.cardView));
        holder.cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView textViewForItemName;
        FloatingActionButton floatingActionButton;
        TextView textViewForPrice;
        TextView textViewForPieceInfo;

        public Holder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewForHorizontalInFirst);
            image = itemView.findViewById(R.id.imageView);
            textViewForItemName = itemView.findViewById(R.id.textViewForItem);
            floatingActionButton = itemView.findViewById(R.id.floatingActionButton);
            textViewForPieceInfo = itemView.findViewById(R.id.textViewForPieceInfo);
            textViewForPrice = itemView.findViewById(R.id.textViewforPrice);
        }
    }
}

