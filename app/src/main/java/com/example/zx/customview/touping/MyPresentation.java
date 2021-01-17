package com.example.zx.customview.touping;

import android.app.Presentation;
import android.content.Context;
import android.content.res.Resources;
import android.view.Display;
import com.example.zx.customview.R;

/**
 * Created by zx on 2021/1/17 9:06
 * Describe:
 */
class MyPresentation extends Presentation {

    public MyPresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    public MyPresentation(Context outerContext, Display display, int theme) {
        super(outerContext, display, theme);
    }

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在Presentation中的getContext得到的context与它依附的Activity的context是不同的,Presentation的context是目标屏幕属性的context,包含着辅助屏幕的属性信息
        Resources resources = getContext().getResources();

        setContentView(R.layout.presentation_content_layout);
    }
}
