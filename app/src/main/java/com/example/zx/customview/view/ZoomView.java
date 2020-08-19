package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zx on 2020/3/4 20:11
 * Describe:
 */
public class ZoomView extends View /*implements View.OnTouchListener*/{

    private Paint paint;
    private Matrix matrix;
    private float[] v9 = new float[9];

    public ZoomView(Context context) {
        super(context);
        initView();
    }

    public ZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(3);

        //paint.setStrokeCap(Paint.Cap.SQUARE);
        //paint.setStrokeJoin(Paint.Join.ROUND);

//        setOnTouchListener(this);

        matrix = new Matrix();
        matrix.getValues(v9);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(getWidth()<=0) return;

        canvas.setMatrix(matrix);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawRect(getWidth()/4, getHeight()/4, getWidth()/2+getWidth()/4, getHeight()/2+getHeight()/4, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    private double lastScaleValue;
    private double scaleValue;
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        int pointerNum = event.getPointerCount();
//        if(pointerNum>1) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    lastScaleValue = 0;
//                    return true;
//
//                case MotionEvent.ACTION_MOVE:
//                    float dx = event.getX(0) - event.getX(1);
//                    float dy = event.getY(0) - event.getY(1);
//
//                    double scale = Math.sqrt(dx*dx + dy*dy);
//                    if(lastScaleValue == 0) {
//                        lastScaleValue = scale;
//
//                    } else {
//                        scaleValue = scale / lastScaleValue;
//                    }
//                    matrix.setScale((float) scaleValue, (float) scaleValue);
//                    break;
//
//                case MotionEvent.ACTION_UP:
//                    break;
//                default:
//                    break;
//            }
//        }
//        return false;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerNum = event.getPointerCount();
        if(pointerNum>1) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastScaleValue = 0;
                    break;

                case MotionEvent.ACTION_MOVE:
                    float dx = event.getX(0) - event.getX(1);
                    float dy = event.getY(0) - event.getY(1);

                    double scale = Math.sqrt(dx*dx + dy*dy);
                    if(lastScaleValue == 0) {
                        lastScaleValue = scale;

                    } else {
                        scaleValue = scale / lastScaleValue;
                        matrix.setScale((float) scaleValue, (float) scaleValue);
                        postInvalidate();
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
