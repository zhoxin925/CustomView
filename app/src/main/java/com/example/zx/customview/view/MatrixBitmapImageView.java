package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by zx on 2020/3/10 17:59
 * Describe:
 */
public class MatrixBitmapImageView extends androidx.appcompat.widget.AppCompatImageView {

    public MatrixBitmapImageView(Context context) {
        super(context);
        init();
    }

    public MatrixBitmapImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixBitmapImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        super.setImageBitmap(imageBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
