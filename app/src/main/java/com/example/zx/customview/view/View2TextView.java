package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zhouxin on 2018/3/19.
 */

public class View2TextView extends TextView {
    private Paint mPaint1, mPaint2;

    public View2TextView(Context context) {
        this(context, null);
    }

    public View2TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View2TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.STROKE);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.GREEN);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        canvas.drawRect(10, 10, getMeasuredWidth()-10, getMeasuredHeight()-10, mPaint2);
        canvas.save();
        //绘制文字前平移10像素
        canvas.translate(20, 10);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
