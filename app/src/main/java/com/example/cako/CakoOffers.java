package com.example.cako;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CakoOffers extends Fragment {
    Boolean x;
    ArrayList<String> code,offer,offerDetail;
    Activity co;
    Database database;
    Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(!x){
            View v = inflater.inflate(R.layout.layout_for_fragment_cako_offers, container, false);
            RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewForCakoOffers);
            recyclerView.setAdapter(new RecyclerAdapterForOffers(co, code,offer,offerDetail));
            recyclerView.setLayoutManager(new LinearLayoutManager(co, LinearLayoutManager.VERTICAL, false));
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
        cursor=database.getDataInCakoOffersRecyclerView();
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

class RecyclerAdapterForOffers extends RecyclerView.Adapter<RecyclerAdapterForOffers.Holder> {
    Context co;
    ArrayList<String> code,offer,offerDetail;

    public RecyclerAdapterForOffers(Context context, ArrayList<String> code,ArrayList<String> offer,ArrayList<String> offerDetail) {
        co = context;
        this.code = code;
        this.offer=offer;
        this.offerDetail=offerDetail;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(co).inflate(R.layout.layout_for_card_in_offers, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.code.setText(code.get(position));
        holder.discountText.setText(offer.get(position));
        holder.discountDetail.setText(offerDetail.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(co, "yes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return code.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        Button button;
        TextView discountText,code,discountDetail;

        public Holder(@NonNull View itemView) {
            super(itemView);

            code=(TextView)itemView.findViewById(R.id.cakoOfferName) ;
            discountText=(TextView)itemView.findViewById(R.id.textViewForDiscountPercentage) ;
            discountDetail=(TextView)itemView.findViewById(R.id.textViewForDiscountDetails) ;
            button =(Button)itemView.findViewById(R.id.borderlessButtonInCakoOffers);
        }
    }
}