package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zx.customview.R;

/**
 * Created by zhouxin on 2018/12/14.
 */

public class View8Bitmap extends View {
    private Context context;
    private Paint mPaint;

    public View8Bitmap(Context context) {
        this(context, null);
    }

    public View8Bitmap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View8Bitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        //mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_picture);

        //会截取原图的srcrectf部分
        Rect srcrectf = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        //经过缩放，把原文件放到decrectf这么大的区域内
        Rect decrectf = new Rect(50, 50, 500, 500);
        
        canvas.drawBitmap(bitmap, srcrectf, decrectf, mPaint);

    }
}
