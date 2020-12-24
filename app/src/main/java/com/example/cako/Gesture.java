package com.example.cako;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

class Gesture extends GestureDetector.SimpleOnGestureListener implements View.OnDragListener {
    RecyclerView recyclerView;
    View view;
    Context context;
    int positionForAdapter;
    GestureDetector gestureDetector;
    Integer id;
    String name, price, image, detail;

    Gesture(final Context co, View v, Integer id, String name, String price, String image, String detail) {
        this.view = v;
        context = co;
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.detail = detail;

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        View.DragShadowBuilder builder = new View.DragShadowBuilder(view);
        view.startDrag(null, builder, view, 0);
        builder.getView().setOnDragListener(this);
    }

    @Override
    public boolean onDrag(View itemView, DragEvent dragEvent) {
        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED) {
            Intent intent = new Intent(context, OnDragActivity.class);
            intent.putExtra("Id", id);
            intent.putExtra("Picture", image);
            intent.putExtra("ItemName", name);
            intent.putExtra("PieceInfo", detail);
            intent.putExtra("Price", price);
            context.startActivity(intent);
        }
        return true;
    }

}
