package com.example.zx.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.zx.customview.view.CanvasView;

/**
 * Created by zx on 2020/8/29 12:11
 * Describe:
 */
public class CanvasPaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_canvas_paint);
        CanvasView canvasView = new CanvasView(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        canvasView.setLayoutParams(layoutParams);
        setContentView(canvasView);
    }
}

