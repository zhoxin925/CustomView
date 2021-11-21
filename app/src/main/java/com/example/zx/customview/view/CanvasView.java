package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zx on 2021/3/4 20:40
 * Describe:
 *
 * 1.内容实际上是绘制在屏幕上；
 * 2.画布，即Canvas，只是规定了绘制内容时的规则；
 * 3.内容的位置由坐标决定，而坐标是相对于画布而言的
 */
public class CanvasView extends View {

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);

        canvas.translate(50, 50);

        canvas.drawColor(Color.BLUE);
    }
}
