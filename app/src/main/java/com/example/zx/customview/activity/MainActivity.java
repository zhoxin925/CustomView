package com.example.zx.customview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zx.customview.R;
import com.example.zx.customview.view.View11SlideBar;
import com.example.zx.customview.view.View5ChartTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidebar);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main2); //CoordinatorLayout
        context = this;

        String ss1 = "chart change on master!";
        String ss2 = "chart change on dev!";

        String ss4 = "on master";
        String ss3 = "on dev!";


        View11SlideBar slideBar = findViewById(R.id.slidebar);
        final TextView toastTv = findViewById(R.id.toast_tv);

        slideBar.setOnListener(new View11SlideBar.onTouchListener() {
            @Override
            public void onClick(int choose, String str) {
                toastTv.setText(str);
            }
        });
    }

    @Override
    public void onClick(View v) {
//        startActivity(new Intent(context, AnimationActivity.class));
        startActivity(new Intent(context, MatrixActivity.class));
//        startActivity(new Intent(context, TestChartActivity.class));
    }
}
