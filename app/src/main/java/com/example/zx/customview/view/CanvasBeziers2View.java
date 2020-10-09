package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zx on 2020/10/8 20:35
 *
 * https://hencoder.com/ui-1-1/
 * https://www.jianshu.com/p/12fcc3fedbbc
 *
 * Describe: 二阶贝塞尔曲线 Beziers
 */
public class CanvasBeziers2View extends View {

    private Paint mPaint;
    private Path mBeziersPath;

    private Path mPointPath;

    private Point mStartPoint;
    private Point mControlPoint;
    private Point mEndPoint;

    public CanvasBeziers2View(Context context) {
        this(context, null);
    }

    public CanvasBeziers2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasBeziers2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeWidth(5);

        mBeziersPath = new Path();
        mPointPath = new Path();

        mStartPoint = new Point();
        mStartPoint.set(100, 300);

        mControlPoint = new Point();
        mControlPoint.set(300, 100);

        mEndPoint = new Point();
        mEndPoint.set(500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBeziersPath.moveTo(mStartPoint.x, mStartPoint.y);
        mBeziersPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);
        mBeziersPath.rQuadTo(200, 300, 400, -200);

        mPointPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPointPath.lineTo(mControlPoint.x, mControlPoint.y);
        mPointPath.lineTo(mEndPoint.x, mEndPoint.y);

        canvas.drawPath(mBeziersPath, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawPath(mPointPath, mPaint);
    }
}
