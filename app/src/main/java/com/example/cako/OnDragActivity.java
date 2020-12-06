package com.example.cako;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class OnDragActivity extends AppCompatActivity {
    CardView cardView;
    ConstraintLayout constraintLayout;
    ImageView imageView;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_for_on_drag_in_first_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForLong);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.arrowButtonInLong);
        imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintForCakeDetailVisibleOrNotInLong);
        cardView = (CardView) findViewById(R.id.cardViewForLong);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(constraintLayout.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    constraintLayout.setVisibility(View.VISIBLE);
                }
                else{
                    TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                    imageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    constraintLayout.setVisibility(View.GONE);
                }
            }
        });

    }
}
