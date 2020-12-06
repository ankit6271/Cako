package com.example.cako;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class FaqActivity extends AppCompatActivity {
    LinkedHashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        Toolbar toolbar = findViewById(R.id.toolbarForFaqActivity);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        hashMapForFaq();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewForFaq);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecyclerViewForFaq(this, hashMap));
    }

    public void hashMapForFaq() {
        hashMap = new LinkedHashMap<>();
        String k1 = "Where CAKO is based?";
        String v1 = "CAKO is build with a mindset to bring cake shop online.Launching online cake shop in 3 cities(Ambala,Kurukshetra,Chandigarh/Mohali) ";
        String k2 = "Why CAKO and what makes it cool?";
        String v2 = "CAKO is basically an initiative taken to provide customer with rich mouth watering cakes along with different variants";
        String k3 = "Delivery time and charges";
        String v3 = "We provide next day or same day delivery and also accepts Pre-Booking so that you need not go outside and spent your precious time on selecting a cake.Along with this we provide free home delievery on all cakes within a range of 10km and with every extra 5km we charge 5 rupees extra. ";
        String k4 = "Why you should trust us";
        String v4 = "Whenever a person needs to buy a cake he/she have to go outside and then choose the cake with less no. of designs being available at the shop at that time which can be very challenging for the customers who want more beautifully designed cakes to select one among the cake available at the shop. The main motive of CAKO is to bring the cake shop online on our mobile phones which we think can boost up the cake business and customers are provided with many variants and designs of cake which being not available at the shop";
        String k5 = "How to order cakes";
        String v5 = "Just select cake of your choice and add it to the cart and then click on GO TO CHECKOUT button and guess what you will get your cake at your doorstep.";
        String k6 = "Data privacy on CAKO";
        String v6 = "We keep your data safe with us and won't share it with anyone.We have a moto here at Cako YOUR SAFETY IS OUR SAFETY";
        String k7 = "Will you have adds";
        String v7 = "No";
        String k8 = "How to invite my friends and are we getting off on price after invitation";
        String v8 = "So far we are not aiming on invitation based system. May be next time you visit our shop to add a moment in your life we had that service too.";
        String k9 = "Which Payment Modes are acceptable ";
        String v9 = "So far we are accepting Credit Cards and Debit Cards only but are planning tobring Google Pay into our reach";
        String k10 = "Is COD(Cash On Delivery) available ";
        String v10 = "No";
        hashMap.put(k1, v1);
        hashMap.put(k2, v2);
        hashMap.put(k3, v3);
        hashMap.put(k4, v4);
        hashMap.put(k5, v5);
        hashMap.put(k6, v6);
        hashMap.put(k7, v7);
        hashMap.put(k8, v8);
        hashMap.put(k9, v9);
        hashMap.put(k10,v10);
    }
}

class RecyclerViewForFaq extends RecyclerView.Adapter<RecyclerViewForFaq.Holder> {
    Activity co;
    HashMap<String, String> hashMap;

    public RecyclerViewForFaq(Context applicationContext, HashMap<String, String> hashMap) {
        co = (Activity) applicationContext;
        this.hashMap = hashMap;
    }

    @NonNull
    @Override
    public RecyclerViewForFaq.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(co).inflate(R.layout.card_for_faq, parent, false);
        return new com.example.cako.RecyclerViewForFaq.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final com.example.cako.RecyclerViewForFaq.Holder holder, int position) {
        Set<String> set = hashMap.keySet();
        String key = (String) set.toArray()[position];
        holder.textViewForQuestion.setText(key);
        holder.textViewForAnswer.setText(hashMap.get(key));

    }

    @Override
    public int getItemCount() {
        return hashMap.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView textViewForQuestion;
        TextView textViewForAnswer;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textViewForQuestion = itemView.findViewById(R.id.textViewForQuestions);
            textViewForAnswer = itemView.findViewById(R.id.textViewForAnswers);
        }
    }
}
