package com.example.zx.customview.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ArgbColorView extends View {
    private String TAG = "Animation_ArgbColorView";
    private float viewW;
    private float viewH;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int color = Color.RED;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public ArgbColorView(Context context) {
        super(context);
    }

    public ArgbColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArgbColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        viewW = getWidth();
        viewH = getHeight();

        paint.setColor(color);
        canvas.drawCircle(viewW/2, viewH/2, viewW/2, paint);
    }
}
