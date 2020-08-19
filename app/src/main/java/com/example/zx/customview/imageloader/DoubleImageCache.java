package com.example.zx.customview.imageloader;

import android.graphics.Bitmap;

/**
 * Created by zx on 2020/3/18 22:51
 * Describe:
 */
public class DoubleImageCache implements ImageCache {

    private DiskImageCache diskCache = new DiskImageCache();
    private MemoryImageCache memoryCache = new MemoryImageCache();

    @Override
    public void put(String key, Bitmap value) {
        diskCache.put(key, value);
        memoryCache.put(key, value);
    }

    @Override
    public Bitmap get(String key) {
        Bitmap bitmap = memoryCache.get(key);
        if(bitmap == null) {
            bitmap = diskCache.get(key);
        }
        return bitmap;
    }
}
