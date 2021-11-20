package com.example.zx.customview.view1;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zx on 2021/11/7 17:46
 * Describe:
 *
 * 1.调用每个子View的measure(), 让子View自我测量
 * 2.根据子view给出的尺寸，得出子view的位置，并保存他们的位置和尺寸
 * 3.根据子View的位置和尺寸计算出自己的尺寸，并用setMeasuredDimension()保存
 *
 */
public class SomeLayout extends ViewGroup {
    private int usedWidth;

    public SomeLayout(Context context) {
        super(context);
    }

    public SomeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SomeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();

        int selfWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int selfWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        //int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        for(int i=0; i<childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams lp = childView.getLayoutParams();
            int childWidthMeasureSpec = 0;

            switch (lp.width) {
                case MATCH_PARENT:
                    if(selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(selfWidthSize - usedWidth, MeasureSpec.EXACTLY);
                    } else {
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                case WRAP_CONTENT:
                    if(selfWidthMode == MeasureSpec.EXACTLY || selfWidthMode == MeasureSpec.AT_MOST) {
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(selfWidthSize, MeasureSpec.AT_MOST);
                    } else {
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                default:
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY);
                    break;
            }

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
