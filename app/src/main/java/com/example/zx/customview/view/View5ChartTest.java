package com.example.zx.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zx.customview.bean.PieChartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxin on 2018/11/25.
 */

public class View5ChartTest extends View {
    private List<PieChartBean> datas = new ArrayList<>();
    private Paint paint = null;
    private int width;
    private int height;
    private float radius;

    private float startAngle;
    private float sweepAngle;

    public View5ChartTest(Context context) {
        this(context, null);
    }

    public View5ChartTest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View5ChartTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setDatas(List<PieChartBean> datas)
    {
        this.datas = datas;
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);

        radius = 100f;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width/2, height/2);
        canvas.drawCircle(0f, 0f, radius, paint);

        paint.setStyle(Paint.Style.FILL);
        for(int i=0;i<datas.size();i++)
        {
            PieChartBean bean = datas.get(i);
            paint.setColor(bean.getColor());
            sweepAngle += bean.getValue();

            RectF rectF = new RectF(-radius, -radius, radius, radius);
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            startAngle = sweepAngle;
        }
    }
}
