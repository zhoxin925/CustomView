package com.example.zx.customview.activity.animation;

import android.app.Activity;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.core.view.animation.PathInterpolatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zx.customview.R;

public class AnimInterpolatorActivity extends Activity implements View.OnClickListener {
    private TextView accelerate;
    private TextView overshot;
    private TextView accelerateDecelerate;
    private TextView anticipate;
    private TextView anticipateOvershot;
    private TextView bounce;
    private TextView cycle;
    private TextView decelerate;
    private TextView linear;

    private TranslateAnimation transAnim1;
    private TranslateAnimation transAnim2;
    private TranslateAnimation transAnim3;
    private TranslateAnimation transAnim4;
    private TranslateAnimation transAnim5;
    private TranslateAnimation transAnim6;
    private TranslateAnimation transAnim7;
    private TranslateAnimation transAnim8;
    private TranslateAnimation transAnim9;

    Interpolator[] interpolators = new Interpolator[13];
    Path interpolatorPath;
    {
        interpolatorPath = new Path();
        interpolatorPath.lineTo(0.25f, 0.25f);
        interpolatorPath.moveTo(0.25f, 1.5f);
        interpolatorPath.lineTo(1, 1);
        interpolators[0] = new AccelerateDecelerateInterpolator();
        interpolators[1] = new LinearInterpolator();
        interpolators[2] = new AccelerateInterpolator();
        interpolators[3] = new DecelerateInterpolator();
        interpolators[4] = new AnticipateInterpolator();
        interpolators[5] = new OvershootInterpolator();
        interpolators[6] = new AnticipateOvershootInterpolator();
        interpolators[7] = new BounceInterpolator();
        interpolators[8] = new CycleInterpolator(0.5f);
        interpolators[9] = PathInterpolatorCompat.create(interpolatorPath);//
        interpolators[10] = new FastOutLinearInInterpolator();//
        interpolators[11] = new FastOutSlowInInterpolator();//
        interpolators[12] = new LinearOutSlowInInterpolator();//
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout contentView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_interpolator, null);
        setContentView(contentView);

        accelerate = findViewById(R.id.accelerate);
        overshot = findViewById(R.id.overshot);
        accelerateDecelerate = findViewById(R.id.accelerate_decelerate);
        anticipate = findViewById(R.id.anticipate);
        anticipateOvershot = findViewById(R.id.anticipate_overshot);
        bounce = findViewById(R.id.bounce);
        cycle = findViewById(R.id.cycle);
        decelerate = findViewById(R.id.decelerate);
        linear = findViewById(R.id.linear);

        transAnim1 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , accelerate.getY()
                , accelerate.getY());
        transAnim1.setInterpolator(new AccelerateInterpolator());
        transAnim1.setDuration(2000);

        transAnim2 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , overshot.getY()
                , overshot.getY());
        transAnim2.setInterpolator(new OvershootInterpolator());
        transAnim2.setDuration(2000);

        transAnim3 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , accelerateDecelerate.getY()
                , accelerateDecelerate.getY());
        transAnim3.setInterpolator(new AccelerateDecelerateInterpolator());
        transAnim3.setDuration(2000);

        transAnim4 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , anticipate.getY()
                , anticipate.getY());
        transAnim4.setInterpolator(new AnticipateInterpolator());
        transAnim4.setDuration(2000);

        transAnim5 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , anticipateOvershot.getY()
                , anticipateOvershot.getY());
        transAnim5.setInterpolator(new AnticipateInterpolator());
        transAnim5.setDuration(2000);

        transAnim6 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , bounce.getY()
                , bounce.getY());
        transAnim6.setInterpolator(new BounceInterpolator());
        transAnim6.setDuration(2000);

        transAnim7 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , cycle.getY()
                , cycle.getY());
        transAnim7.setInterpolator(new CycleInterpolator(2));
        transAnim7.setDuration(2000);

        transAnim8 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , decelerate.getY()
                , decelerate.getY());
        transAnim8.setInterpolator(new DecelerateInterpolator());
        transAnim8.setDuration(2000);

        transAnim9 = new TranslateAnimation(0
                , measureView(contentView).x/* - measureView(accelerate).x*/
                , linear.getY()
                , linear.getY());
        transAnim9.setInterpolator(new LinearInterpolator());
        transAnim9.setDuration(2000);
    }

    private Point measureView(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        w = view.getMeasuredWidth();
        h = view.getMeasuredHeight();

        return new Point(w, h);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                accelerate.startAnimation(transAnim1);
                overshot.startAnimation(transAnim2);
                accelerateDecelerate.startAnimation(transAnim3);
                anticipate.startAnimation(transAnim4);
                anticipateOvershot.startAnimation(transAnim5);
                bounce.startAnimation(transAnim6);
                cycle.startAnimation(transAnim7);
                decelerate.startAnimation(transAnim8);
                linear.startAnimation(transAnim9);
                break;
        }
    }
}
