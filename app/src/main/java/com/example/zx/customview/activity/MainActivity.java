package com.example.zx.customview.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zx.customview.R;
import com.example.zx.customview.view.MatrixBitmapImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;

    /*private BufferView bufferView;
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            bufferView.postInvalidate();
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*bufferView = new BufferView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bufferView.setLayoutParams(params);
        setContentView(bufferView);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 10000, 3000);
        //timerTask.cancel();*/

        setContentView(R.layout.activity_main);

//        setContentView(R.layout.activity_main1);
//        //setContentView(R.layout.activity_main2); //CoordinatorLayout
//        context = this;
//
//        String ss1 = "chart change on master!";
//        String ss2 = "chart change on dev!";
//
//        String ss4 = "on master";
//        String ss3 = "on dev!";


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        final MatrixBitmapImageView imageView = findViewById(R.id.matrix_imageview);
//        imageView.setImageBitmap(bitmap);
//
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Matrix matrix = new Matrix();
//
//                matrix.postTranslate(0, trans++);
//
//                imageView.setImageMatrix(matrix);
//            }
//        });
    }
    private int trans = 1;

    @Override
    public void onClick(View v) {
//        startActivity(new Intent(context, AnimationActivity.class));
        startActivity(new Intent(context, MatrixActivity.class));
//        startActivity(new Intent(context, TestChartActivity.class));
    }
}
