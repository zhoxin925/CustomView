package com.example.zx.customview.touping;

import android.app.Presentation;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

import com.example.zx.customview.R;

/**
 * Created by zx on 2021/1/17 9:06
 * Describe:
 */
public class MyPresentation extends Presentation {
    private ImageView imageView;

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
        imageView = findViewById(R.id.iv);
    }

    public void updateContent(Bitmap bitmap) {
        if(bitmap != null) {
            //BitmapDrawable drawable = new BitmapDrawable(bitmap);
            //imageView.setImageDrawable(drawable);
            imageView.setImageBitmap(bitmap);
        }
    }

    public void updateContent(BitmapDrawable drawable) {
        if(drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }
}
