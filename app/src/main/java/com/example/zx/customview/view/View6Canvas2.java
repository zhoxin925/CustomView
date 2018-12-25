package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouxin on 2018/12/4.
 */

public class View6Canvas2 extends View {
    private Paint mPaint;
    private Path pathx, pathy;
    private int mWidth;
    private int mHeight;

    public View6Canvas2(Context context) {
        this(context, null);
    }

    public View6Canvas2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View6Canvas2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2f);
        mPaint.setAntiAlias(true);

        pathx = new Path();
        pathy = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制x、y坐标轴
        canvas.drawLine(0, mHeight/2, mWidth, mHeight/2, mPaint);
        canvas.drawLine(mWidth/2, 0, mWidth/2, mHeight, mPaint);

        //绘制y坐标轴箭头
        pathx.moveTo(mWidth/2 - 20, 20);
        pathx.lineTo(mWidth/2, 0);
        pathx.lineTo(mWidth/2 + 20, 20);
        //pathx.close();
        canvas.drawPath(pathx, mPaint);
        //绘制x坐标轴箭头
        pathy.moveTo(mWidth - 20, mHeight/2 - 20);
        pathy.lineTo(mWidth, mHeight/2);
        pathy.lineTo(mWidth - 20, mHeight/2 + 20);
        //pathy.close();
        canvas.drawPath(pathy, mPaint);

        RectF rectF = new RectF(0f, -200f, 200f, 0f);

        canvas.translate(mWidth / 2, mHeight / 2);

        //Matrix matrix = canvas.getMatrix();

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rectF, mPaint);
        canvas.save();
        for(int i=200; i>50; i--)
        {
            canvas.scale(0.9f, 0.9f, 100, -100);
            canvas.drawRect(rectF, mPaint);
        }
        canvas.restore();

        canvas.save();
        canvas.scale(-0.5f, 0.5f);
        mPaint.setColor(Color.RED);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(1f, -1f);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(30);
        canvas.scale(1f, -1f);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        for(int i=200; i>50; i--)
        {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, mPaint);
        }
        canvas.restore();

        //canvas.setMatrix(matrix);
        canvas.drawCircle(0, 0, 180, mPaint);
        canvas.drawCircle(0, 0, 200, mPaint);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(3f);
        for(int i=10; i<=360; i+=10)
        {
            canvas.drawLine(0, -180, 0, -200, mPaint);
            canvas.rotate(10);
        }

    }
}
