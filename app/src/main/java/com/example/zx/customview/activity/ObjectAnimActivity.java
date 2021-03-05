package com.example.zx.customview.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.example.zx.customview.R;
import com.example.zx.customview.bean.Point;

/**
 * 组合动画
 *
 * AnimatorSet.play(Animator anim)   ：播放当前动画
 * AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
 * AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
 * AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
 * AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行
 */

public class ObjectAnimActivity extends AppCompatActivity {
    private ImageView roundIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectanim);

        roundIv = findViewById(R.id.round_iv);
        roundIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objAnimation();
            }
        });
    }

    private void objAnimation() {
        /**
         * https://blog.csdn.net/carson_ho/article/details/72909894   属性动画
         *
         * ObjectAnimator类根据传入的属性名 去寻找 该对象对应属性名的 set（） & get（）方法，从而进行对象属性值的赋值
         *
         * with/height无效，因为其是设置的最大宽高---->继承原对象（ImageView）--setWidth--mTarget.getLayoutParams().width = width;
         */
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "alpha", 1f, 0f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "rotation", 0, 360);//rotationX, rotationY
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "translationX", 0, getScreenWH().x, 0);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "translationY", 0, getScreenWH().y, 0);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "scaleX", 0, 1, 1.5f, 1);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "scaleY", 0, 1, 1.5f, 1);
        animator.setDuration(6000);
        animator.start();
    }

    private android.graphics.Point measureView(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        w = view.getMeasuredWidth();
        h = view.getMeasuredHeight();
        return new android.graphics.Point(w, h);
    }

    private android.graphics.Point getScreenWH() {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);//SDK<17
        //display.getRealMetrics(metrics);//SDK>=17
        return new android.graphics.Point(metrics.widthPixels, metrics.heightPixels);
    }
}
