package com.example.zx.customview.activity;

import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.zx.customview.view.ZoomTestView;
import com.example.zx.customview.view.ZoomView;

/**
 * Created by zx on 2020/3/4 20:16
 * Describe:
 */
public class ZoomActivity extends AppCompatActivity {

    private View containerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        containerView = new ZoomView(this);
        //containerView = new ZoomTestView(this);
        //containerView.setBackgroundColor(Color.RED);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200, 200);
        containerView.setLayoutParams(params);

        setContentView(containerView);
    }
}
