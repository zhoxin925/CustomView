package com.example.zx.customview.activity.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.zx.customview.R;
import com.example.zx.customview.animation.ArgbEvaluator;
import com.example.zx.customview.animation.view.ArgbColorView;

public class ColorAnimActivity extends AppCompatActivity {

    private String TAG = "Animation_ColorAnimActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        setContentView(R.layout.activity_animation_color);

        final ArgbColorView view = findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ObjectAnimator animator = ObjectAnimator.ofObject(view, "color"
                        ,new ArgbEvaluator(), 0xffff0000, 0xff00ff00);
                animator.setDuration(600);
                animator.start();*/

                ObjectAnimator animator = ObjectAnimator.ofInt(view, "color", 0xffff0000, 0xff00ff00);
                animator.setEvaluator(new ArgbEvaluator());//new HsvEvaluator()
                animator.setDuration(1000);
                animator.start();

                /*if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    ObjectAnimator animator2 = ObjectAnimator.ofArgb(view, "color", 0xffff0000, 0xff00ff00);
                    animator2.start();
                }*/
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow()");
    }
}
