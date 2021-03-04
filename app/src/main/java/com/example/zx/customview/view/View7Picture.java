package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zx.customview.R;

/**
 * Created by zhouxin on 2018/12/6.
 */

public class View7Picture extends View {
    private Context mContext;
    private Paint mPaint;
    private Picture picture;

    public View7Picture(Context context) {
        this(context, null);
    }

    public View7Picture(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View7Picture(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        recoding();
    }

    private void init() {
        picture = new Picture();
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void recoding() {
        Canvas canvas = picture.beginRecording(500, 500);

        //canvas.translate(250, 250);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
        //canvas.drawBitmap(bitmap, 0, 0, mPaint);

        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, mPaint);

        picture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /** 1	使用Picture提供的draw方法绘制。
            2	使用Canvas提供的drawPicture方法绘制。
            3	将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。

            1 对canvas有影响
            2、3 对canvas没有影响

            1 可操作性弱
            2、3 可操作性较强
         **/

//        picture.draw(canvas); // 1

//        canvas.drawPicture(picture); // 2
//        //绘制的内容根据选区进行了缩放
//        canvas.drawPicture(picture,new Rect(0,0, picture.getWidth(), 500));
//        canvas.drawPicture(picture,new RectF(0,0, picture.getWidth(), 250));
//        canvas.drawPicture(picture,new RectF(0,0, 250, picture.getHeight()));

        PictureDrawable drawable = new PictureDrawable(picture); // 3
        drawable.setBounds(new Rect(0,0, picture.getWidth(), picture.getHeight()));
        drawable.draw(canvas);

    }
}
