package com.example.zx.customview.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by zx on 2020/3/18 21:33
 * Describe:
 */
public class ImageLoader {

    private LruCache<String, Bitmap> lruCache;
    private ExecutorService executor = Executors.newFixedThreadPool(5);
    private Handler handler = new Handler(Looper.getMainLooper());

    public ImageLoader() {
        initCache();
    }

    private void initCache() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        int cacheSize = (int) (maxMemory / 8);
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void updateBitmap(final String urlstr, final ImageView imageView) {
        imageView.setTag(urlstr);

        if(lruCache.get(urlstr) != null) {
            imageView.setImageBitmap(lruCache.get(urlstr));

        } else {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    downloadBitmap(urlstr, imageView);
                }
            });
        }

    }

    private void downloadBitmap(final String urlstr, final ImageView imageView) {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            url = new URL(urlstr);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            if(bitmap != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lruCache.put(urlstr, bitmap);
                        updateBitmap(urlstr, imageView);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
