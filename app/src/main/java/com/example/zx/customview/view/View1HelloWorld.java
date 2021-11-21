package com.example.zx.customview.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhouxin on 2018/3/19.
 */

public class View1HelloWorld extends View {

    public View1HelloWorld(Context context) {
        this(context, null);
    }

    public View1HelloWorld(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View1HelloWorld(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
}
