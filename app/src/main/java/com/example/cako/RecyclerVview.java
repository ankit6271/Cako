package com.example.cako;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

class RecyclerVview extends RecyclerView.Adapter<RecyclerVview.Holder> {
    static Activity co;
    String[] name;
    String[] image;

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

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        GestureDetector gestureDetector;
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

            gestureDetector = new GestureDetector(co, new Gesture(co,cardView));
            cardView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    gestureDetector.onTouchEvent(motionEvent);
                    return true;
                }
            });
        }
    }
}


