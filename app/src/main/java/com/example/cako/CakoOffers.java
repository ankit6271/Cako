package com.example.cako;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CakoOffers extends Fragment {
    String[] offer;
    Activity co;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_for_fragment_cako_offers, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewForCakoOffers);
        recyclerView.setAdapter(new RecyclerAdapterForOffers(co, offer));
        recyclerView.setLayoutManager(new LinearLayoutManager(co, LinearLayoutManager.VERTICAL, false));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offer = new String[]{"50% OFF", "10% OFF"};
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.co=(Activity) context;
    }
}

class RecyclerAdapterForOffers extends RecyclerView.Adapter<RecyclerAdapterForOffers.Holder> {
    Context co;
    String [] discountValues={};

    public RecyclerAdapterForOffers(Context context, String[] discount) {
        co = context;
        this.discountValues = discount;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(co).inflate(R.layout.layout_for_card_in_offers, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.discountText.setText(discountValues[position]);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(co, "yes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return discountValues.length;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        Button button;
        TextView discountText;

        public Holder(@NonNull View itemView) {
            super(itemView);
            discountText=(TextView)itemView.findViewById(R.id.textViewForDiscountPercentage) ;
            button =(Button)itemView.findViewById(R.id.borderlessButtonInCakoOffers);
        }
    }
}