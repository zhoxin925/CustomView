package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zhouxin on 2019/5/26.
 */

public class View9TransView extends FrameLayout {

    private float transX;
    private float transY;
    private float scale = 1;

    //    private float lastX;
//    private float lastY;
//    private float lastRawX;
//    private float lastRawY;

    private float downX, downY;

//    private int viewW, viewH;

    public View9TransView(Context context) {
        this(context, null);
    }

    public View9TransView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View9TransView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = measureWidth(widthMeasureSpec);
//        int height = measureHeight(widthMeasureSpec);

//        View child1 = getChildAt(0);
//        child1.measure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

//        viewW = child1.getWidth();
//        viewH = child1.getHeight();

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(width, height);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if(widthMode==MeasureSpec.EXACTLY) {
            result = widthSize;
        } else {
            result = 200;
            if(widthMode==MeasureSpec.AT_MOST) {
                result = Math.min(result, widthSize);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(heightMode==MeasureSpec.EXACTLY) {
            result = heightSize;
        } else {
            result = 200;
            if(heightMode==MeasureSpec.AT_MOST) {
                result = Math.min(result, heightSize);
            }
        }
        return result;
    }

    float viewL;
    float viewT;
    float viewR;
    float viewB;

//    float x;
//    float y;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View child1 = getChildAt(0);
        float rawX = event.getRawX();
        float rawY = event.getRawY();

//        if(rawX > viewX && rawX < viewX+viewW
//                && rawY > viewY && rawY < viewY+viewH)
//        {
//            Log.i("View1---onTouchEvent", "点击在view内");
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
//                    x = event.getX();
//                    y = event.getY();

                    viewL = child1.getLeft();
                    viewT = child1.getTop();
                    viewR = child1.getRight();
                    viewB = child1.getBottom();

                    downX = rawX;
                    downY = rawY;
                    break;

                case MotionEvent.ACTION_MOVE:
                    transX = rawX - downX;
                    transY = rawY - downY;
//                    invalidate();
                    child1.layout((int)(viewL+transX), (int)(viewT+transY)
                            , (int)(viewR+transX), (int)(viewB+transY));
                    break;

                case MotionEvent.ACTION_UP:
//                    float tempX = rawX - downX;
//                    float tempY = rawY - downY;
////
//                    child1.layout((int)(viewL+tempX), (int)(viewT+tempY)
//                            , (int)(viewR+tempX), (int)(viewB+tempY));
                    break;
            }
//        }
//        else
//        {
//            Log.i("View1---onTouchEvent", "点击在view外");
//        }

//        lastX = x;
//        lastY = y;
//        lastRawX = rawX;
//        lastRawY = rawY;
//        return super.onTouchEvent(event);
        return true;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

//        canvas.save();
//        canvas.translate(transX, transY);
//        canvas.scale(scale, scale);

        super.dispatchDraw(canvas);
//        canvas.restore();

    }
}
