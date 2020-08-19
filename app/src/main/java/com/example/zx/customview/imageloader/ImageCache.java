package com.example.zx.customview.imageloader;

import android.graphics.Bitmap;

/**
 * Created by zx on 2020/3/18 22:21
 * Describe:
 */
public interface ImageCache {

    void put(String key, Bitmap value);

    Bitmap get(String key);
}
