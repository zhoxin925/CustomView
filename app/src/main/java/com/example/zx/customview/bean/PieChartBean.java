package com.example.zx.customview.bean;

import android.graphics.Color;

/**
 * Created by zhouxin on 2018/11/25.
 * 扇形图实体
 */

public class PieChartBean {
    private float value;
    private int color;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
