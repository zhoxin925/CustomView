package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zx.customview.R;

/**
 * Created by zx on 2020/3/3 15:22
 * Describe:
 */
public class BufferView extends View {
    private Paint paint;

    private Bitmap bufferBitmap;
    private Canvas bufferCanvas;

    private float mCircleR = 10;

    public BufferView(Context context) {
        super(context);
        init();
    }

    public BufferView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BufferView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(getWidth()<0)
            return;

        bufferBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bufferCanvas = new Canvas(bufferBitmap);

        Bitmap pic = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
        bufferCanvas.drawBitmap(pic, 0, 0, paint);

        if (mCircleR >= (getWidth() / 10)) {
            mCircleR = 0;
        } else {
            mCircleR++;
        }

        //把5*8个圆绘制在tmp上
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 8; j++)
                bufferCanvas.drawCircle(
                        (getWidth() / 5) * i + (getWidth() / 10),
                        (getHeight() / 8) * j + (getHeight() / 16),
                        mCircleR, paint);
        //把tmp绘制在物理设备上
        canvas.drawBitmap(bufferBitmap, 0, 0, paint);



//        if (mCircleR >= (getWidth() / 10)) {
//            mCircleR = 0;
//        } else {
//            mCircleR++;
//        }
//        for (int i = 0; i < 5; i++)
//            for (int j = 0; j < 8; j++)
//                canvas.drawCircle(
//                        (getWidth() / 5) * i + (getWidth() / 10),
//                        (getHeight() / 8) * j + (getHeight() / 16),
//                        mCircleR, paint);

    }
}
