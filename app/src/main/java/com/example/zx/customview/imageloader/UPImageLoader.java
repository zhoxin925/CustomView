package com.example.zx.customview.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zx on 2020/3/18 22:21
 * Describe:
 */
public class UPImageLoader {

    private ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ImageCache imageCache = new MemoryImageCache();
    private Handler handler = new Handler(Looper.getMainLooper());

    public UPImageLoader(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    public void displayImage(String imageUrl, ImageView imageView) {
        Bitmap bitmap = imageCache.get(imageUrl);
        if(bitmap != null) {
            updateImageView(imageView, bitmap);
            return;
        }
        submitLoadRequest(imageUrl, imageView);
    }

    public void updateImageView(final ImageView imageView, final Bitmap bitmap) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    private void submitLoadRequest(final String imageUrl, final ImageView imageView) {
        imageView.setTag(imageUrl);
        service.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoad(imageUrl);
                if(bitmap == null) {
                    return;
                }
                if(imageView.getTag().equals(imageUrl)) {
                    updateImageView(imageView, bitmap);
                }
                imageCache.put(imageUrl, bitmap);
            }
        });
    }

    private Bitmap downLoad(String imageUrl) {
        URL url = null;
        HttpURLConnection connection = null;
        Bitmap bitmap = null;

        try {
            url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
