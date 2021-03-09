package com.example.zx.customview.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.zx.customview.gesture.TestGestureDetector;

/**
 * Created by temp123 on 2019/7/24.
 */

public class View10GestureDetector extends LinearLayout {
    private Context mContext;
    private TestGestureDetector mGestureDetector;
    private Scroller mScroller;

    public View10GestureDetector(Context context) {
        super(context);
        this.mContext = context;

        init();
    }

    private void init() {
        mGestureDetector = new TestGestureDetector(this);
    }

    public void setmScroller(Scroller scroller) {
        this.mScroller = scroller;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
