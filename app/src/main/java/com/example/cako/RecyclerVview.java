package com.example.cako;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

class RecyclerVview extends RecyclerView.Adapter<RecyclerVview.Holder> {
    @SuppressLint("StaticFieldLeak")
    static Activity co;
    ArrayList<Integer> id,cursorValues;
    ArrayList<String> name,price,image,detail;
    SQLiteDatabase sqLiteDatabase;

    public RecyclerVview(Context applicationContext,ArrayList<Integer> id, ArrayList<String> name,ArrayList<String> price,ArrayList<String> image,ArrayList<String> detail,Database database) {
        co = (Activity) applicationContext;
        this.id=id;
        this.name = name;
        this.price=price;
        this.image = image;
        this.detail=detail;
        sqLiteDatabase=database.getWritableDatabase();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(co).inflate(R.layout.layout_for_horizontal_recyclerview, parent, false);
        return new Holder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        holder.textViewForItemName.setText(name.get(position));
        holder.textViewForPrice.setText(price.get(position));
        Glide.with(co).load(image.get(position)).into(holder.image);
        holder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Recycle")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                cursorValues=new ArrayList<>();
                ContentValues values = new ContentValues();
                String s = co.getSharedPreferences("com.example.cako.SharedPreferenceInCako", Context.MODE_PRIVATE).getString("Email", "null");
                assert s != null;
                Log.e("Email", s);
                if (!s.equals("null")) {
                    Log.e("Email", s);
                    @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("Select CakeId from Cart where Email=?", new String[]{s});
                    if (cursor != null && cursor.moveToFirst()) {
                        int t =id.get(position);
                        do {
                            Log.e("Email", s);
                            Integer value = cursor.getInt(0);
                            cursorValues.add(value);
                        }while (cursor.moveToNext());
                        if(!cursorValues.contains(t)){
                            Log.e("Email", "cake id" + id);
                            values.put("CakeID", id.get(position));
                            values.put("Email", s);
                            sqLiteDatabase.insert("Cart", null, values);
                            Toast.makeText(co, "Added to Cart", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(co, "Already in Cart", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        values.put("cakeID", id.get(position));
                        values.put("Email", s);
                        sqLiteDatabase.insert("Cart", null, values);
                        Toast.makeText(co, "Added to Cart", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        holder.textViewForPrice.setText(price.get(position));
        holder.textViewForPieceInfo.setText("1Kg/2Kg/4Kg at S.Prc");
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                GestureDetector gestureDetector=new GestureDetector(co,new Gesture(co,view,id.get(position),name.get(position),price.get(position),image.get(position),detail.get(position)));
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView textViewForItemName;
        FloatingActionButton floatingActionButton;
        TextView textViewForPrice;
        TextView textViewForPieceInfo;

        @SuppressLint("ClickableViewAccessibility")
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


