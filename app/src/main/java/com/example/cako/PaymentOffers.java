package com.example.cako;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentOffers extends Fragment {
    String[] offer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.layout_for_fragment_payment_offers, container, false);
        RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recyclerViewForPaymentOffers);
        recyclerView.setAdapter(new RecyclerAdapterForOffers(getContext(), offer));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offer = new String[]{"50% OFF On HDFC Cards", "20% OFF on IDBC Cards","FLAT 30 OFF on Amazon Pay","FLAT 30 OFF on Amazon Pay","FLAT 30 OFF on Amazon Pay"};
    }
}

