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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zx.customview.R;

/**
 * Created by zhouxin on 2018/11/20.
 * Matrix的练习
 */

public class MatrixActivity extends Activity implements View.OnClickListener {

    private Canvas canvas;
    private Paint paint;
    private Matrix matrix;

    private ImageView iv1;
    private ImageView iv2;

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private EditText et6;
    private EditText et7;
    private EditText et8;
    private EditText et9;

    private float matrixResetData[] = new float[]{1,0,0,
                                                  0,1,0,
                                                  0,0,1};
    private float matrixData[] = new float[9];

    private Bitmap bitmap;
    private Bitmap updateBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        et9 = findViewById(R.id.et9);

        findViewById(R.id.change).setOnClickListener(this);
        findViewById(R.id.reset).setOnClickListener(this);

        bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        updateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        canvas = new Canvas(updateBitmap);
        paint = new Paint();
        matrix = new Matrix();

        //jingxiang(bitmap, matrix);
        //daoying(bitmap, matrix);
        //canvas.drawBitmap(bitmap, matrix, paint);

        //iv1.setImageBitmap(bitmap);
        //iv2.setImageBitmap(updateBitmap);





        Matrix m1 = new Matrix();
        float[] v1 = new float[] {
                2,0,0,
                0,2,0,
                0,0,1
        };
        m1.setValues(v1);
        Matrix m2 = new Matrix();
        float[] v2 = new float[] {
                2,0,2,
                0,2,0,
                0,0,1
        };
        m2.setValues(v2);
        boolean b = m1.postConcat(m2);
        /////////////////////////////////////////////////////
        Matrix m11 = new Matrix();
        float[] v11 = new float[] {
                2,0,0,
                0,2,0,
                0,0,1

        };
        m11.setValues(v11);
        Matrix m22 = new Matrix();
        float[] v22 = new float[] {
                2,0,2,
                0,2,0,
                0,0,1
        };
        m22.setValues(v22);
        boolean bb = m11.preConcat(m22);

//////////////////////////////////////////////////////////////////////////////////////////////

        Matrix m = new Matrix();
        float[] v = new float[] {
                2,0,0,
                0,2,0,
                0,0,1
        };
        m.setValues(v);
        Matrix mm = new Matrix();
        float[] vv = new float[] {
                2,0,2,
                0,2,0,
                0,0,1
        };
        mm.setValues(vv);
        boolean bv = mm.postConcat(m);
        ///////////////////////////////////////////////////
        Matrix mm1 = new Matrix();
        float[] vv1 = new float[] {
                2,0,0,
                0,2,0,
                0,0,1
        };
        mm1.setValues(vv1);
        Matrix mm2 = new Matrix();
        float[] vv2 = new float[] {
                2,0,2,
                0,2,0,
                0,0,1
        };
        mm2.setValues(vv2);
        boolean bbv = mm2.preConcat(mm1);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.change) {
            matrixData[0] = Float.valueOf(et1.getText().toString());
            matrixData[1] = Float.valueOf(et2.getText().toString());
            matrixData[2] = Float.valueOf(et3.getText().toString());

            matrixData[3] = Float.valueOf(et4.getText().toString());
            matrixData[4] = Float.valueOf(et5.getText().toString());
            matrixData[5] = Float.valueOf(et6.getText().toString());

            matrixData[6] = Float.valueOf(et7.getText().toString());
            matrixData[7] = Float.valueOf(et8.getText().toString());
            matrixData[8] = Float.valueOf(et9.getText().toString());

        } else if(v.getId() == R.id.reset) {
            for(int i=0; i<matrixResetData.length; i++) {
                matrixData[i] = matrixResetData[i];
            }
            et1.setText("1");
            et2.setText("0");
            et3.setText("0");
            et4.setText("0");
            et5.setText("1");
            et6.setText("0");
            et7.setText("0");
            et8.setText("0");
            et9.setText("1");
        }

        matrix.setValues(matrixData);

        canvas.save();
        canvas.drawBitmap(bitmap, matrix, paint);
        canvas.restore();

        iv1.setImageBitmap(bitmap);
        iv2.setImageBitmap(updateBitmap);
    }

//    private void jingxiang(Bitmap bitmap, Matrix matrix) {
//        matrix.postTranslate(bitmap.getWidth(), 0);
//        matrix.setScale(-1, 1);
//    }
//
//    private void daoying(Bitmap bitmap, Matrix matrix) {
//        matrix.setScale(1, -1);
//        matrix.postTranslate(0, bitmap.getHeight());
//    }
}
