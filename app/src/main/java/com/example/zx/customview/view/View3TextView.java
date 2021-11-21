package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zhouxin on 2018/3/19.
 * 不知道干啥的 2018.3.19  15:52
 */

public class View3TextView extends TextView {
    private int mViewWidth = 0;
    private Paint mPaint;
    private LinearGradient mlinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;

    public View3TextView(Context context) {
        this(context, null);
    }

    public View3TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View3TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth>0) {
                mPaint = new Paint();
                mlinearGradient = new LinearGradient(0,0,getMeasuredWidth(),0
                        , new int[]{Color.BLUE, 0xffffff, Color.BLUE}
                        , null
                        , Shader.TileMode.CLAMP);
                mPaint.setShader(mlinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mGradientMatrix!=null) {
            mTranslate += mViewWidth/5;
            if(mTranslate>2*mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mlinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidate();
        }
    }
}
