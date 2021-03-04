package com.example.zx.customview.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zx.customview.R;

/**
 * Created by zx on 2020/3/5 10:14
 * Describe: Matrix 的scale trans shew rotate的前乘后乘
 */
public class MatrixBitmapActivity extends Activity implements View.OnClickListener {

    private Canvas canvas;
    private Paint paint;
    private Matrix opeMatrix;
    private Matrix baseMatrix;
    private Matrix setMatrix;

    //private ImageView source;
    private ImageView iv;

    private Bitmap bitmap;
    private Bitmap operBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_bitmap);

        //source = findViewById(R.id.source);
        iv = findViewById(R.id.iv);

        findViewById(R.id.setscale).setOnClickListener(this);
        findViewById(R.id.prescale).setOnClickListener(this);
        findViewById(R.id.postscale).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);

        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher);
        operBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(operBitmap);

        paint = new Paint();
        opeMatrix = new Matrix();
        baseMatrix = new Matrix();
        setMatrix = new Matrix();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setscale:
                setMatrix.setScale(0.5f, 0.5f);
                canvas.drawBitmap(bitmap, setMatrix, paint);

                iv.setImageBitmap(operBitmap);

//                opeMatrix.reset();
//                opeMatrix.setScale(0.5f, 0.5f);
//
//                setMatrix.set(baseMatrix);
//                setMatrix.postConcat(opeMatrix);
//
//                iv.setImageMatrix(setMatrix);

                break;

            case R.id.prescale:

                opeMatrix.reset();
                opeMatrix.preScale(0.5f, 0.5f);

                setMatrix.set(baseMatrix);
                setMatrix.postConcat(opeMatrix);

                iv.setImageMatrix(setMatrix);
                break;

            case R.id.postscale:

                opeMatrix.reset();
                opeMatrix.postScale(0.5f, 0.5f);

                setMatrix.set(baseMatrix);
                setMatrix.postConcat(opeMatrix);

                iv.setImageMatrix(setMatrix);
                break;

            case R.id.reset:
                Matrix ivMatrix3 = iv.getImageMatrix();
                ivMatrix3.set(baseMatrix);

                iv.setImageMatrix(ivMatrix3);
                break;
        }
    }
}
