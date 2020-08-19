package com.example.zx.customview.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by zx on 2020/3/18 22:23
 * Describe:
 */
public class MemoryImageCache implements ImageCache {
    private LruCache<String, Bitmap> lruCache;

    public MemoryImageCache() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        int cacheSize = (int) (maxMemory / 8);
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    public void put(String key, Bitmap value) {
        lruCache.put(key, value);
    }

    @Override
    public Bitmap get(String key) {
        return lruCache.get(key);
    }
}
