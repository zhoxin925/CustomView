package com.example.zx.customview.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ProgressView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float progress;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        paint.setColor(0xff00ff00);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(new RectF(0, 0, width, height), paint);

        paint.setStrokeWidth(30);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(0xffFF3C7E);
        //paint.setStrokeJoin(Paint.Join.ROUND);

        RectF rectF = new RectF(15, 15, width - 15, height - 15);
        canvas.drawArc(rectF, 90, progress * (360 / 100), false, paint);

        paint.setColor(0xffffffff);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText((int)progress+"%", centerX - paint.measureText((int)progress+"%")/2
                , centerY - (paint.ascent() + paint.descent()) / 2, paint);
    }
}
