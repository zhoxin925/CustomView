package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

public class View10GradientTextView extends AppCompatTextView {
    private Paint mPaint;
    private LinearGradient mGradient;
    private Matrix mMatrix;
    private int mViewWidth;
    private int mTranslate;

    public View10GradientTextView(Context context) {
        this(context, null);
    }

    public View10GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View10GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth>0) {
                mPaint = getPaint();
                mGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.BLUE, 0xffffffff, Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mGradient);
                mMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mMatrix != null) {
            mTranslate += mViewWidth / 5;
            if(mTranslate > 2*mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mMatrix.setTranslate(mTranslate, 0);
            mGradient.setLocalMatrix(mMatrix);
            postInvalidate();
        }
    }
}
