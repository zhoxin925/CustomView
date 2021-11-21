package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhouxin on 2019/5/26.
 */

public class ZoomTestView extends View {

    private float transX;
    private float transY;
    private float scale = 1;

    private float downX, downY;

    public ZoomTestView(Context context) {
        this(context, null);
    }

    public ZoomTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(widthMeasureSpec);

        setMeasuredDimension(width, height);
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
    double pointLen;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float rawX = event.getRawX();
        float rawY = event.getRawY();

        int pointCount = event.getPointerCount();
        if(pointCount == 1) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    viewL = getLeft();
                    viewT = getTop();
                    viewR = getRight();
                    viewB = getBottom();

                    downX = rawX;
                    downY = rawY;
                    break;
                case MotionEvent.ACTION_MOVE:
                    transX = rawX - downX;
                    transY = rawY - downY;
                    layout((int)(viewL+transX), (int)(viewT+transY)
                            , (int)(viewR+transX), (int)(viewB+transY));
                    break;
            }
        }
        else if(pointCount == 2) {
            float x0 = event.getX(0);
            float y0 = event.getY(0);
            float x1 = event.getX(1);
            float y1 = event.getY(1);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    viewL = getLeft();
                    viewT = getTop();
                    viewR = getRight();
                    viewB = getBottom();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float dx = x1 - x0;
                    float dy = y1 - y0;

                    //Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
                    double valueLen = Math.sqrt(dx*dx + dy*dy);
                    if(pointLen == 0) {
                        pointLen = valueLen;
                    }
                    else if(Math.abs(valueLen - pointLen) > 10){
                        scale = (float) (valueLen / pointLen);
                    }

//                    layout((int)(viewL*scale), (int)(viewT*scale)
//                            , (int)(viewR*scale), (int)(viewB*scale));

                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return true;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();
        //canvas.translate(transX, transY);
        canvas.scale(scale, scale);

        super.dispatchDraw(canvas);
        canvas.restore();

    }
}

