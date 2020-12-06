package com.example.cako;

import android.content.Context;
import android.content.Intent;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.cardview.widget.CardView;

class Gesture extends GestureDetector.SimpleOnGestureListener implements View.OnDragListener {
    CardView cardView;
    Context context;

    Gesture(Context co, CardView cardView) {
        this.cardView = cardView;
        context = co;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        View.DragShadowBuilder builder = new View.DragShadowBuilder(cardView);
        cardView.startDrag(null, builder, cardView, 0);
        builder.getView().setOnDragListener(this);
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        if (dragEvent.getAction() == DragEvent.ACTION_DRAG_ENDED) {
            Intent intent = new Intent(context, OnDragActivity.class);
            context.startActivity(intent);
        }
        return true;
    }
}
