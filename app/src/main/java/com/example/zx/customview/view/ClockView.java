package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zx on 2020/2/27 16:28
 * Describe: 绘制时钟
 */
public class ClockView extends View {

    private Paint paint;
    private Context mContext;


    public ClockView(Context context) {
        super(context);
        initView();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeJoin(Paint.Join.ROUND);


        paint.setTextSize(20);
        paint.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/simkai.ttf"));//Typeface.createFromFile();
        float xinLen20 = paint.measureText("A");
        Log.i("sss1", xinLen20+"");

        paint.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/msyh.ttc"));//Typeface.createFromFile();
        float xinLen220 = paint.measureText("A");
        Log.i("sss2", xinLen220+"");

        paint.setTextSize(10);
        paint.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/simkai.ttf"));//Typeface.createFromFile();
        float xinLen10 = paint.measureText("A");
        Log.i("sss3", xinLen10+"");

        paint.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/msyh.ttc"));//Typeface.createFromFile();
        float xinLen210 = paint.measureText("A");
        Log.i("sss4", xinLen210+"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);

        int w = getWidth();
        int h = getHeight();

        canvas.drawCircle(w/2, h/2, w/2, paint);

        for(int i=0; i<12; i++) {
            String ss = String.valueOf(i);

            if(i==0 || i==3 || i==6 || i==9) {
                paint.setStrokeWidth(3);
                paint.setTextSize(28);
                int textS = (int) paint.measureText(ss);
                canvas.drawLine(w/2, h/2-w/2, w/2, h/2-w/2 + 30, paint);
                canvas.drawText(ss, w/2-textS*ss.length()/2, (h/2-w/2 + 30 + 20 + textS), paint);

            } else {
                paint.setStrokeWidth(2);
                paint.setTextSize(15);
                int textS = (int) paint.measureText(ss);
                canvas.drawLine(w/2, h/2-w/2, w/2, h/2-w/2 + 15, paint);
                canvas.drawText(ss, w/2-textS*ss.length()/2, (h/2-w/2 + 15 + 20 + textS), paint);
            }
            canvas.rotate(30, w/2, h/2);
        }

        canvas.save();
        canvas.translate(w/2, h/2);

        paint.setStrokeWidth(3);
        canvas.drawLine(0,0, w/4, w/4, paint);

        paint.setStrokeWidth(2);
        canvas.drawLine(0,0, 0, w/5, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0, 8, paint);

        canvas.restore();
    }
}
