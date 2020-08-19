package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.zx.customview.R;

/**
 * Created by zx on 2020/3/8 10:03
 * Describe:
 */
public class ScrollZoomView extends android.support.v7.widget.AppCompatImageView {
    private Context context;
    private Paint paint;
    private Matrix matrix;
    private Matrix downmatrix;

    private float lastX, lastY;
    private float lastRawX, lastRawY;

    private Bitmap bitmap;

    private double lastScale;
    private boolean isMorePointer;
    private Point centerPoint;


    public ScrollZoomView(Context context) {
        super(context);
        init(context);
    }

    public ScrollZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        matrix = new Matrix();
        downmatrix = new Matrix();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float rawx = event.getRawX();
        float rawy = event.getRawY();

        switch (event.getAction() & event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                lastRawX = event.getRawX();
                lastRawY = event.getRawY();


                downmatrix.set(matrix);

                isMorePointer = false;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                isMorePointer = true;
                lastScale = 0;
                break;

            case MotionEvent.ACTION_MOVE:
                int pointNum = event.getPointerCount();

                if(!isMorePointer && pointNum == 1) { //移动处理
                    float disx = x - lastX;
                    float disy = y - lastY;
                    float disRawX = rawx - lastRawX;
                    float disRawY = rawy - lastRawY;

                    // 两种方式都可实现，1记录每一个移动点之间的值，每一次的移动都在matrix中操作
                    matrix.postTranslate(disx, disy);
                    setImageMatrix(matrix);
                    lastX = event.getX();
                    lastY = event.getY();
                    lastRawX = event.getRawX();
                    lastRawY = event.getRawY();

                    // 两种方式都可实现，2记录down时点的x,y值，给记录矩阵downmatrix赋值
                    // 移动每一个点matrix都设置为down时的矩阵，计算当前的点与down时的点的距离，对matrix做一个down到目前移动的点的整体的一个移动
                    /* matrix.set(downmatrix);
                    matrix.postTranslate(disx, disy);
                    postInvalidate();*/

                } else if(isMorePointer && pointNum > 1) {//双指缩放处理
                    float disX = event.getX(0) - event.getX(1);
                    float disY = event.getY(0) - event.getY(1);

                    if(lastScale == 0) {
                        lastScale = (int) Math.sqrt(disX*disX + disY*disY);
                        centerPoint = getCenterPoint(event.getX(0), event.getY(0)
                                , event.getX(1), event.getY(1));

                    } else {
                        double newScale = Math.sqrt(disX*disX + disY*disY);
                        float scale = (float) (newScale/lastScale);

                        lastScale = newScale;

                        matrix.postScale(scale, scale, centerPoint.x, centerPoint.y);
                        setImageMatrix(matrix);
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY();
//        float rawx = event.getRawX();
//        float rawy = event.getRawY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastX = event.getX();
//                lastY = event.getY();
//                lastRawX = event.getRawX();
//                lastRawY = event.getRawY();
//
//
//                downmatrix.set(matrix);
//
//                lastScale = 0;
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                int pointNum = event.getPointerCount();
//
//                if(pointNum == 1) { //移动处理
//                    float disx = x - lastX;
//                    float disy = y - lastY;
//                    float disRawX = rawx - lastRawX;
//                    float disRawY = rawy - lastRawY;
//
//                    // 两种方式都可实现，1记录每一个移动点之间的值，每一次的移动都在matrix中操作
//                    /*matrix.postTranslate(disx, disy);
//                    setImageMatrix(matrix);
//                    lastX = event.getX();
//                    lastY = event.getY();
//                    lastRawX = event.getRawX();
//                    lastRawY = event.getRawY();*/
//
//                    // 两种方式都可实现，2记录down时点的x,y值，给记录矩阵downmatrix赋值
//                    // 移动每一个点matrix都设置为down时的矩阵，计算当前的点与down时的点的距离，对matrix做一个down到目前移动的点的整体的一个移动
//                    matrix.set(downmatrix);
//                    matrix.postTranslate(disx, disy);
//                    postInvalidate();
//
//                } else if(pointNum > 1) {//双指缩放处理
//                    float disX = event.getX(0) - event.getX(1);
//                    float disY = event.getY(0) - event.getY(1);
//
//                    if(lastScale == 0) {
//                        lastScale = (int) Math.sqrt(disX*disX + disY*disY);
//                        centerPoint = getCenterPoint(event.getX(0), event.getY(0)
//                                                    , event.getX(1), event.getY(1));
//
//                    } else {
//                        double newScale = Math.sqrt(disX*disX + disY*disY);
//                        float scale = (float) (newScale/lastScale);
//
//                        lastScale = newScale;
//
//                        matrix.postScale(scale, scale, centerPoint.x, centerPoint.y);
//                        setImageMatrix(matrix);
//                    }
//                }
//                break;
//
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//
//        return true;
//    }

    private Point getCenterPoint(float x0, float y0, float x1, float y1) {
        float centerX = (x1-x0)/2 + x0;
        float centerY = (y1-y0)/2 + y0;
        return new Point((int)centerX, (int)centerY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.setMatrix(matrix);
        //canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        canvas.save();
        canvas.drawBitmap(bitmap, matrix, null);
        canvas.restore();

        canvas.drawRect(getLeft(), getTop(), getLeft()+getWidth(), getTop()+getHeight(), paint);
    }
}
