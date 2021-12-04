package com.example.zx.customview.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zx.customview.R;

/**
 * Created by zx on 2021/12/4 21:54
 * Describe:
 */
public class ViewDrawInvalidateActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewGroup rootview;
    private Button add;
    private Button remove;

    private ViewGroup customViewF;
    private TextView textView;
    private View cView;
    private CSurfaceView surfaceView;

    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdraw_invalidate);

        rootview = findViewById(R.id.rootview);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);

        textView = new TextView(this);
        textView.setText("哈哈哈哈哈哈哈哈哈哈哈哈好");
        textView.setTextSize(50);
        textView.setTextColor(Color.WHITE);
        cView = new CView(this);

        surfaceView = new CSurfaceView(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                customViewF = new RelativeLayout(this);
                customViewF.setBackgroundColor(Color.BLUE);

                customViewF.addView(cView);
                customViewF.addView(surfaceView);
                rootview.addView(customViewF);
                Log.d("FInvalidateView", "add");
                break;

            case R.id.remove:
                customViewF.setVisibility(View.GONE);
//                rootview.requestLayout();
//                rootview.invalidate();
//                rootview.postInvalidate();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        surfaceView.drawBitmap();
                        rootview.removeView(customViewF);
//                    }
//                }, 10);
//                surfaceView.drawBitmap();
                customViewF.removeAllViews();
                customViewF = null;

                Log.d("FInvalidateView", "remove");
                break;

            default:
                break;
        }
    }

    class CSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder holder;

        public CSurfaceView(Context context) {
            super(context);
            holder = getHolder();
            holder.addCallback(this);
//            setBackgroundColor(Color.YELLOW);
            setZOrderOnTop(true);
        }


        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            Canvas canvas = holder.lockCanvas();
            if(canvas != null) {
                canvas.drawBitmap(bitmap, 0, 0, null);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        }

        public void drawBitmap() {
            Canvas canvas = getHolder().lockCanvas();
            if(canvas != null) {
                canvas.drawBitmap(bitmap, 0, 0, null);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    class CView extends View {

        public CView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }
}
