package com.example.cako;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PaymentOffers extends Fragment {
    ArrayList<String> code,offer,offerDetail;
    Activity co;
    Database database;
    Cursor cursor;
    Boolean x;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(!x){
            View v= inflater.inflate(R.layout.layout_for_fragment_payment_offers, container, false);
            RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recyclerViewForPaymentOffers);
            recyclerView.setAdapter(new RecyclerAdapterForOffers(getContext(),  code,offer,offerDetail));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            return v;
        }
        else{
            View v= inflater.inflate(R.layout.layout_for_no_coupon_offers, container, false);
            ImageView image=(ImageView)v.findViewById(R.id.imageViewForNoCouponAvailable);
            Glide.with(co).load("https://www.iheartpublix.com/wp-content/uploads/2019/08/Sorry-NO-coupon-use-this-one.jpg").into(image);
            return v;
        }

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        database=new Database(co);
        super.onCreate(savedInstanceState);
        offer = new ArrayList<>();
        code = new ArrayList<>();
        offerDetail = new ArrayList<>();
        cursor=database.getDataInPaymentOffersRecyclerView();
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                code.add(cursor.getString(0));
                offer.add(cursor.getString(1));
                offerDetail.add(cursor.getString(2));
            }
        }
        else{
            x=true;
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.co=(Activity) context;
        x=false;
    }
}

