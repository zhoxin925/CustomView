package com.example.zx.customview.activity.animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.example.zx.customview.R;
import com.example.zx.customview.animation.view.Practice06KeyframeView;
import com.example.zx.customview.animation.view.ProgressView;

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
    private ProgressView progressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectanim);

        progressView = findViewById(R.id.progress);
        roundIv = findViewById(R.id.round_iv);
        roundIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //objAnimation();
                //properAnimation();
                notifyTest();
            }
        });

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private NotificationManager notificationManager;
    private void notifyTest() {
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setAutoCancel(true)
                .setContentTitle("内容标题")
                .setContentText("今天周五啦，开心一点")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setColor(Color.parseColor("#234465"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg))
                //.setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(1, notification);
    }

    private void properAnimation() {
        /*ObjectAnimator animator = ObjectAnimator.ofInt(progressView, "progress", 0, 100);
        animator.setDuration(2000);
        animator.start();*/

        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);
        Keyframe keyframe3 = Keyframe.ofFloat(1, 80);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress"
                , keyframe1, keyframe2, keyframe3);
        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(progressView, holder);
        animator1.setDuration(6000);
        animator1.setInterpolator(new FastOutSlowInInterpolator());
        animator1.start();
    }

    private void objAnimation() {
        /**
         * ObjectAnimator类根据传入的属性名 去寻找 该对象对应属性名的 set（） & get（）方法，从而进行对象属性值的赋值
         *
         * with/height无效，因为其是设置的最大宽高---->继承原对象（ImageView）--setWidth--mTarget.getLayoutParams().width = width;
         */

        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "alpha", 1f, 0f, 1f);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "rotation", 0, 360);//rotationX, rotationY
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "translationX", 0, getScreenWH().x, 0);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "translationY", 0, getScreenWH().y, 0);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "scaleX", 0, 1, 1.5f, 1);
        //ObjectAnimator animator = ObjectAnimator.ofFloat(roundIv, "scaleY", 0, 1, 1.5f, 1);
        //animator.setDuration(6000);
        //animator.start();

        ObjectAnimator animator1 = new ObjectAnimator();
        animator1.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return 0;
            }
        });
        animator1.setTarget(roundIv);
        animator1.setPropertyName("rotation");
        animator1.setFloatValues(0, 360);
        animator1.setDuration(6000);
        animator1.start();
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
