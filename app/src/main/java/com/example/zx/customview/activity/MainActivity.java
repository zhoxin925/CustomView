package com.example.zx.customview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zx.customview.R;
import com.example.zx.customview.view.View5ChartTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        String ssMaster = "I want to create a branch!";
        String ssDev = "I am a new branch, dev!";

        String ss = "I am Master branch!";
    }

    @Override
    public void onClick(View v) {
//        startActivity(new Intent(context, AnimationActivity.class));
        startActivity(new Intent(context, MatrixActivity.class));
//        startActivity(new Intent(context, TestChartActivity.class));
    }
}
