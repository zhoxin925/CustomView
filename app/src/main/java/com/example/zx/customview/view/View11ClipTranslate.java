package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class View11ClipTranslate extends View {

    private Paint paint;
    private int w, h;


    public View11ClipTranslate(Context context) {
        this(context, null);
    }

    public View11ClipTranslate(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View11ClipTranslate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = MeasureSpec.getSize(widthMeasureSpec);
        h = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //translate: 移动原点，x.y可以用正负值来定位
        //clip: 剪切那块区域，就在那块区域内绘制，赋值就算大于区域，也只能绘制出区域内的部分

        canvas.save();

        //canvas.clipRect(0, 0, w, h);
        paint.setColor(Color.parseColor("#885521ff"));
        canvas.drawRect(0, 0, w, h, paint);

        canvas.translate(100, 100);

        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, w, h, paint);

        paint.setColor(Color.parseColor("#8800ff00"));
        canvas.drawRect(-50, -50, w, h, paint);

        canvas.translate(-100, -100);
        canvas.clipRect(20, 200, w-20, h-200);
        paint.setColor(Color.GRAY);
        canvas.drawRect(-20, -20, w, h, paint);
        paint.setColor(Color.parseColor("#88ff0000"));
        canvas.drawRect(0, 0, w, h, paint);

        canvas.restore();


    }
}
