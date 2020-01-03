package com.example.zx.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.zx.customview.R;

public class View11SlideBar extends View {
    private Context mContext;
    private float mWidth;
    private float mHeight;

    private Paint mBgPaint;
    private Paint mTextPaint;
    private static String[] letters;

    private int mLayoutBg;
    private int mTextSize;
    private int mTextNormalColor;
    private int mTextCheckedColor;

//    private int mToastBg;
//    private int mToastTextSize;
//    private int mToastTextColor;

    private int mChooseIndex = -1;
    private float itemSize;
    private float space;

    public View11SlideBar(Context context) {
        this(context, null);
    }

    public View11SlideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View11SlideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        @SuppressLint("CustomViewStyleable") TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.slidebar_style);
        mLayoutBg = array.getResourceId(R.styleable.slidebar_style_layout_background, Color.GRAY);
        mTextSize = array.getInt(R.styleable.slidebar_style_textSize, 40);
        mTextNormalColor = array.getColor(R.styleable.slidebar_style_textNormalColor, Color.BLACK);
        mTextCheckedColor = array.getColor(R.styleable.slidebar_style_textCheckedColor, Color.BLUE);

//        mToastBg = array.getResourceId(R.styleable.slidebar_style_toast_background, Color.BLUE);
//        mToastTextSize = array.getResourceId(R.styleable.slidebar_style_toast_textSize, 60);
//        mToastTextColor = array.getResourceId(R.styleable.slidebar_style_toast_textColor, Color.WHITE);
        array.recycle();

        init();
    }

    private void init() {

        mBgPaint = new Paint();
        mBgPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextNormalColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mTextSize);

        letters = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };

        itemSize = mTextPaint.measureText(letters[0]);
        space = (mHeight - itemSize * letters.length) / (letters.length + 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);

        if(wMode == MeasureSpec.AT_MOST || wMode == MeasureSpec.UNSPECIFIED) {
            mWidth = 100;
        } else {
            mWidth = wSize;
        }

        mHeight = hSize;
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBgPaint.setColor(Color.GRAY);
        //canvas.drawRect(0, 0, mWidth, mHeight, mBgPaint);

        //itemSize = mTextPaint.measureText(letters[0]);
        //float space = (mHeight - itemSize * letters.length) / (letters.length + 1);
        //float mHeightY = space;

        while (space < 20) {
            mTextSize = mTextSize - 4;
            mTextPaint.setTextSize(mTextSize);

            itemSize = mTextPaint.measureText(letters[0]);
            space = (mHeight - itemSize * letters.length) / (letters.length + 1);
        }

        for(int i=0;i<letters.length;i++) {

            //canvas.drawText(letters[i], mWidth/2 - mTextPaint.measureText(letters[i])/2, mHeightY, mTextPaint);
            //mHeightY = mHeightY + itemSize + space;

            if(mChooseIndex == i) {
                mTextPaint.setColor(mTextCheckedColor);
            } else {
                mTextPaint.setColor(mTextNormalColor);
            }

            canvas.drawText(letters[i]
                    , mWidth/2 - mTextPaint.measureText(letters[i])/2
                    , itemSize * i + (i+1)*space + itemSize
                    , mTextPaint);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction ();
        float y = event.getY ();
        float x = event.getX ();
        int oldChoose = mChooseIndex;

        //(y - space / 2) 去掉顶部的一半space   (mHeight - space)去掉顶部底部各一半space
        int c = (int) ((y - space / 2) / (mHeight - space) * letters.length);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (c != oldChoose && c >= 0 && c < letters.length) {
                    if (listener != null) {
                        listener.onClick (c, letters[c]);
                    }

                    mChooseIndex = c;
                    invalidate ();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mChooseIndex = -1;
                invalidate ();
                break;
            case MotionEvent.ACTION_MOVE:
                if (c != oldChoose && c >= 0 && c < letters.length) {
                    if (listener != null) {
                        listener.onClick (c, letters[c]);
                    }

                    mChooseIndex = c;
                    invalidate ();
                }
                break;
            default:
        }
        return true;
    }

    public interface onTouchListener {
        void onClick(int choose, String str);
    }

    private onTouchListener listener;

    public void setOnListener(onTouchListener listener) {
        this.listener = listener;
    }

}
