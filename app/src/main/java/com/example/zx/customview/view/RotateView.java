package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zx on 2021/4/18 20:20
 * Describe:
 */
public class RotateView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RotateView(Context context) {
        this(context, null);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //线宽较大时可以看出，绘制出来的线是以线的中心来算的。
        /*paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(20, 20, getWidth()-20, getHeight()-20, paint);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(20, 20, getWidth()-20, getHeight()-20, paint);*/


        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        canvas.save();
        canvas.translate(150, 150);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        canvas.restore();


        paint.setColor(Color.GREEN);
        canvas.save();
        canvas.rotate(45, getWidth()/2, getHeight()/2);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        canvas.translate(20, 200);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, 80, 80, paint);
        canvas.restore();

        /*paint.setColor(Color.BLUE);
        canvas.save();
        canvas.rotate(45, 150, 550);
        canvas.drawRect(100, 500, 200, 600, paint);

        canvas.translate(150, 150);
        canvas.drawRect(100, 500, 200, 600, paint);
        canvas.restore();*/



        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawPoint(150, 150, paint);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawPoint(getWidth()/2, getHeight()/2, paint);



//        canvas.save();
//        canvas.translate(150, 150);
//        canvas.rotate(15);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
//        canvas.restore();


        canvas.save();
        canvas.rotate(15);
        canvas.translate(150, 150);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        canvas.restore();
    }
}
