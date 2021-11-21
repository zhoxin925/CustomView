package com.example.zx.customview.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;

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
