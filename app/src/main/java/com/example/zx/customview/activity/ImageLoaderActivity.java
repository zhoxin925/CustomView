package com.example.zx.customview.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.zx.customview.R;
import com.example.zx.customview.imageloader.ImageLoader;

/**
 * Created by zx on 2020/3/18 22:00
 * Describe:
 */
public class ImageLoaderActivity extends AppCompatActivity {
    private String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584551043974&di=58c303555a5ce473678301b034b329e4&imgtype=0&src=http%3A%2F%2Fimages.freeimages.com%2Fimages%2Fpremium%2Fpreviews%2F1102%2F11029265-rosa-tulpen-mit-tupfen.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        ImageView imageView = findViewById(R.id.imageView);

        ImageLoader imageLoader = new ImageLoader();
        imageLoader.updateBitmap(imageUrl, imageView);
    }
}
