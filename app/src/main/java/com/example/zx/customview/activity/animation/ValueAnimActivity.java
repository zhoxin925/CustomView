package com.example.zx.customview.activity.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.zx.customview.R;
import com.example.zx.customview.animation.PointEvaluator;
import com.example.zx.customview.bean.Point;

public class ValueAnimActivity extends AppCompatActivity {
    private ImageView roundIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valueanim);

        roundIv = findViewById(R.id.round_iv);

        android.graphics.Point screenWH = getScreenWH();
        android.graphics.Point roundIvWH = measureView(roundIv);
        final Point startPoint = new Point(roundIv.getX(), roundIv.getY());
        final Point endPoint = new Point(screenWH.x - roundIvWH.x, screenWH.y - roundIvWH.y);

        roundIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://blog.csdn.net/carson_ho/article/details/72909894   属性动画
                ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
                animator.setRepeatCount(2);
                animator.setDuration(6000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Point point = (Point) animation.getAnimatedValue();
                        roundIv.setX(point.getX());
                        roundIv.setY(point.getY());
                        roundIv.invalidate();
                    }
                });
                animator.start();
            }
        });
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
