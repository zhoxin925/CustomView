package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zx on 2020/8/29 12:12
 * Describe:
 */
public class CanvasPaintPracView extends View {
    private Paint mPaint;

    public CanvasPaintPracView(Context context) {
        this(context, null);
    }

    public CanvasPaintPracView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasPaintPracView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();

        //样式，画笔的风格（实心或空心）
        mPaint.setStyle(Paint.Style.FILL);//填充
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //线帽，
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        //线段连接处样式，
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);

        //排列
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextAlign(Paint.Align.RIGHT);
    }

}
