package com.example.zx.customview.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.zx.customview.R;

/**
 * Created by zhouxin on 2018/11/12.
 */

public class AnimationActivity extends Activity implements View.OnClickListener {

    ValueAnimator animator;
    int duration = 1000;

    ImageView imageView;

    double current;
    double pre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = (ImageView) findViewById(R.id.imageview);

        animator = ValueAnimator.ofInt(0, 1000);
        animator.setDuration(duration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("动画-----", animation.getAnimatedValue()+"");

                current = System.currentTimeMillis();
                Log.i("动画--间隔---", ((current-pre)/1000)+"s");
                pre = current;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                animator.start();
                break;
        }
    }
}
