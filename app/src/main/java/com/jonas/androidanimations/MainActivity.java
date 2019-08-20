package com.jonas.androidanimations;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    private ConstraintSet constraintSetOld;
    private ConstraintSet constraintSetNew;
    private boolean altLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);

        constraintSetOld = new ConstraintSet();
        constraintSetNew = new ConstraintSet();

        constraintSetOld.clone(layout);
        constraintSetNew.clone(this, R.layout.activity_main2);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Transition transition = new ChangeBounds();
                    transition.setDuration(750);
                    transition.setInterpolator(new OvershootInterpolator());

                    TransitionManager.beginDelayedTransition(layout, transition);
                }

                if(!altLayout){
                    constraintSetNew.applyTo(layout);
                    altLayout = true;
                }else{
                    constraintSetOld.applyTo(layout);
                    altLayout = false;
                }
            }
        });
    }
}
