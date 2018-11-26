package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouxin on 2018/11/18.
 * canvas的用法学习
 */

public class View4Canvas extends View {
    Paint mPaint;
    public View4Canvas(Context context) {
        this(context, null);
    }

    public View4Canvas(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View4Canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布背景
        canvas.drawColor(Color.GREEN);

        //绘制点
        canvas.drawPoint(150, 150, mPaint);
        canvas.drawPoints(new float[]{210, 250,
                210, 300,
                210, 350
        }, mPaint);

        //绘制线
        mPaint.setStrokeWidth(5f);
        canvas.drawLine(100, 40, 500, 40, mPaint);
        canvas.drawLines(new float[]{50, 50, 100, 100
                , 50, 70, 100, 120}, mPaint);

        //绘制矩形
        canvas.drawRect(400, 100, 500, 200, mPaint);
        canvas.drawRect(new RectF(400f, 220f, 500f, 320f), mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(400, 350, 500, 450);
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(rectF, 20f, 20, mPaint);
        mPaint.setAntiAlias(false);
        //canvas.drawRoundRect(420f, 370f, 480f, 430f, 10f, 10f, mPaint);

        //绘制椭圆
        canvas.drawOval(new RectF(100, 400, 300, 500), mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawOval(new RectF(120, 420, 280, 480), mPaint);

        //绘制圆
        canvas.drawCircle(150, 600, 80, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(150, 600, 60, mPaint);

        //绘制弧
        canvas.drawArc(new RectF(300, 520, 500, 600), 0, 90, true, mPaint);
        canvas.drawArc(new RectF(500, 520, 700, 600), 0, 90, false, mPaint);
        canvas.drawArc(new RectF(500, 600, 600, 700), 0, 90, true, mPaint);
        canvas.drawArc(new RectF(300, 620, 500, 700), 0, 270, true, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20);     //为了实验效果明显，特地设置描边宽度非常大
        // 描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(100,780,50,mPaint);
        // 填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(250,780,50,mPaint);
        // 描边加填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(400, 780, 50, mPaint);

//        //绘制图表
//        float currentStartAngle = mStartAngle;                    // 当前起始角度
//        canvas.translate(mWidth / 2, mHeight / 2);                // 将画布坐标原点移动到中心位置
//        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
//        RectF rect = new RectF(-r, -r, r, r);                     // 饼状图绘制区域
//        for (int i = 0; i < mData.size(); i++) {
//            PieData pie = mData.get(i);
//            mPaint.setColor(pie.getColor());
//            canvas.drawArc(rect, currentStartAngle, pie.getAngle(), true, mPaint);
//            currentStartAngle += pie.getAngle();
//        }
    }
}
