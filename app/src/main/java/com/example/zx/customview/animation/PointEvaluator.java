package com.example.zx.customview.animation;

import android.animation.TypeEvaluator;

import com.example.zx.customview.bean.Point;

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        Point result = null;
        float x = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
        float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());
        result = new Point(x, y);
        return result;
    }
}
