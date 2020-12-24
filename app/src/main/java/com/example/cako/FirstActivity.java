package com.example.cako;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ArrayList<String> nameForExclusiveOffers, priceForExclusiveOffers, imageForExclusiveOffers, detailForExclusiveOffers, nameForBestSelling, priceForBestSelling, imageForBestSelling, detailForBestSelling;
    Database database;
    ArrayList<Integer> idForBestSelling, idForExclusiveOffers;
    String[] typeOfCakes, imageForCakeTypes;
    SharedPreferences preferences;

    @SuppressLint({"UseCompatLoadingForDrawables", "ClickableViewAccessibility"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_activity_first);
        database = new Database(FirstActivity.this);

        typeOfCakes = new String[]{"Wedding", "Birthday", "Postered", "Chocolate", "Baked", "Cupcakes", "Designer"};
        imageForCakeTypes = new String[]{"https://images.pexels.com/photos/2226/food-couple-sweet-married.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2531546/pexels-photo-2531546.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://cdn.igp.com/f_auto,q_auto,t_prodm/products/p-delicious-chocolate-personalised-photo-cake-half-kg--108300-m.jpg",
                "https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/3992206/pexels-photo-3992206.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/913136/pexels-photo-913136.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/433527/pexels-photo-433527.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        };
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_open_24);
        setSupportActionBar(toolbar);

        recyclerViewForCakeType();
        recyclerViewForBestSelling();
        recyclerViewForOffers();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        View v = navigationView.getHeaderView(0);
        //To set image for user by default we are using circle image view reference
        final ImageView i = (ImageView) v.findViewById(R.id.circularImageViewForNavView);
        Glide.with(FirstActivity.this).load("https://i.pinimg.com/originals/e2/7c/87/e27c8735da98ec6ccdcf12e258b26475.png").into(i);

        //To add text into header of navigation
        preferences = getSharedPreferences("com.example.cako.SharedPreferenceInCako", MODE_PRIVATE);
        final TextView name=(TextView)v.findViewById(R.id.textView) ;
        final TextView email=(TextView)v.findViewById(R.id.textView2);
        final TextView kudos=(TextView)v.findViewById(R.id.noOfKudos) ;

        //For every order just add number of kudos
        final TextView noOfKudos=(TextView)v.findViewById(R.id.noOfKudos) ;
        String nameFromPref=preferences.getString("Name","null");
        assert nameFromPref != null;
        if(nameFromPref.equals("null")){
            name.setText("User");
            email.setText("No Email");
            kudos.setText("No Kudos Available");
        }
        else{
            String emailFromPref = preferences.getString("Email", "null");
            name.setText(nameFromPref);
            email.setText(emailFromPref);
        }

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
                    case R.id.settings_nav_view:
                        Intent intent = new Intent(FirstActivity.this, SettingsActivity.class);
                        intent.putExtra("IntentGoing", "FirstActivity");
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.helpCenter_nav_view:
                        Intent intent1 = new Intent(FirstActivity.this, FaqActivity.class);
                        intent1.putExtra("IntentGoing", "FirstActivity");
                        startActivity(intent1);
                        finish();
                        break;
//                        To be added when payment gateway working
//                    case R.id.orderHistory_nav_view:
//                    case R.id.invite_nav_view:

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

    @SuppressLint("UseCompatLoadingForDrawables")
    void recyclerViewForCakeType() {
        //recy View for cake types 1 row
        RecyclerView recyclerViewForCakeTypes = (RecyclerView) findViewById(R.id.recyclerViewForCakeTypes);
        recyclerViewForCakeTypes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewForCakeTypes.setAdapter(new RecyclerVviewForDessertTypes(this, typeOfCakes, imageForCakeTypes));
        //Wanted to add space between REcycler view elements
        DividerItemDecoration dividerItemDecorationForDessert = new DividerItemDecoration(this, RecyclerView.HORIZONTAL);
        dividerItemDecorationForDessert.setDrawable(getResources().getDrawable(R.drawable.divider_recycler_vertical_direction));
        recyclerViewForCakeTypes.addItemDecoration(dividerItemDecorationForDessert);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    void recyclerViewForOffers() {
        //offers recy view and get
        Cursor cursor = database.getDataInExclusiveOffersRecyclerView();
        idForExclusiveOffers = new ArrayList<>();
        nameForExclusiveOffers = new ArrayList<>();
        priceForExclusiveOffers = new ArrayList<>();
        imageForExclusiveOffers = new ArrayList<>();
        detailForExclusiveOffers = new ArrayList<>();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                idForExclusiveOffers.add(cursor.getInt(0));
                nameForExclusiveOffers.add(cursor.getString(1));
                priceForExclusiveOffers.add(cursor.getString(2));
                imageForExclusiveOffers.add(cursor.getString(3));
                detailForExclusiveOffers.add(cursor.getString(4));
            }
        } else {
            Toast.makeText(FirstActivity.this, "Cursor null", Toast.LENGTH_LONG).show();
        }
        RecyclerView horizontalrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHorizontal);
        horizontalrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontalrecyclerView.setAdapter(new RecyclerVview(this, idForExclusiveOffers, nameForExclusiveOffers, priceForExclusiveOffers, imageForExclusiveOffers, detailForExclusiveOffers, database));
//Wanted to add space between REcycler view elements
        DividerItemDecoration dividerItemDecorationHorizontal = new DividerItemDecoration(this, RecyclerView.HORIZONTAL);
        dividerItemDecorationHorizontal.setDrawable(getResources().getDrawable(R.drawable.divider_recycler_vertical_direction));
        horizontalrecyclerView.addItemDecoration(dividerItemDecorationHorizontal);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    void recyclerViewForBestSelling() {
        //Best Selling recy view
        Cursor cursor1 = database.getDataInBestSellingRecyclerView();
        idForBestSelling = new ArrayList<>();
        nameForBestSelling = new ArrayList<>();
        priceForBestSelling = new ArrayList<>();
        imageForBestSelling = new ArrayList<>();
        detailForBestSelling = new ArrayList<>();
        while (cursor1.moveToNext()) {
            idForBestSelling.add(cursor1.getInt(0));
            nameForBestSelling.add(cursor1.getString(1));
            priceForBestSelling.add(cursor1.getString(2));
            imageForBestSelling.add(cursor1.getString(3));
            detailForBestSelling.add(cursor1.getString(4));
        }
        RecyclerView verticalrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewVertical);
        verticalrecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        verticalrecyclerView.setAdapter(new RecyclerVview(this, idForBestSelling, nameForBestSelling, priceForBestSelling, imageForBestSelling, detailForBestSelling, database));
        //Wanted to add space between REcycler view elements
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_recycler_horizontal_direction));
        verticalrecyclerView.addItemDecoration(dividerItemDecoration);

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


