package com.example.zx.customview.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.zx.customview.R;

/**
 * Created by zhouxin on 2018/11/20.
 * Matrix的练习
 */

public class MatrixActivity extends Activity {
    private Canvas canvas;
    private Paint paint;
    private Matrix matrix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        ImageView iv1 = (ImageView) findViewById(R.id.iv1);
        ImageView iv2 = (ImageView) findViewById(R.id.iv2);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        Bitmap updateBitmap = Bitmap.createBitmap(bitmap.getWidth() * 2
                , bitmap.getHeight() * 2, bitmap.getConfig());
        canvas = new Canvas(updateBitmap);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        matrix = new Matrix();

//        jingxiang(bitmap, matrix);
        daoying(bitmap, matrix);
        canvas.drawBitmap(bitmap, matrix, paint);

        iv1.setImageBitmap(bitmap);
        iv2.setImageBitmap(updateBitmap);
    }

    private void jingxiang(Bitmap bitmap, Matrix matrix) {
        matrix.postTranslate(bitmap.getWidth(), 0);
        matrix.setScale(-1, 1);
    }

    private void daoying(Bitmap bitmap, Matrix matrix) {
        matrix.setScale(1, -1);
        matrix.postTranslate(0, bitmap.getHeight());
    }
}
