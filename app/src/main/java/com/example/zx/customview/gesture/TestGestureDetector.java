package com.example.zx.customview.gesture;

import android.view.GestureDetector;
import android.widget.Scroller;

import com.example.zx.customview.view.View10GestureDetector;

/**
 * Created by temp123 on 2019/7/24.
 */

public class TestGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private Scroller mScroller;

    public TestGestureDetector(View10GestureDetector view) {

        mScroller = new Scroller(view.getContext());
        view.setmScroller(mScroller);
    }



}
