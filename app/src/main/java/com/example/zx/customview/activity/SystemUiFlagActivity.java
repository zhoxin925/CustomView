package com.example.zx.customview.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;

import com.example.zx.customview.R;

import java.util.List;

/**
 * SYSTEM_UI_FLAG_FULLSCREEN：全屏，状态栏被隐藏覆盖掉
 * SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：全屏，状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
 *
 * SYSTEM_UI_FLAG_HIDE_NAVIGATION：全屏，导航栏被隐藏覆盖掉
 * SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION：全屏，导航栏不会被隐藏覆盖，导航栏依然可见，Activity底部布局部分会被导航栏遮住
 *
 * LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT：只有当DisplayCutout完全包含在系统状态栏中时，才允许窗口延伸到DisplayCutout区域显示。
 * LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER：该窗口决不允许与DisplayCutout区域重叠。
 * LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES：该窗口始终允许延伸到屏幕短边上的DisplayCutout区域。
 *
 * 全屏，刘海处显示布局：
 *      SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
 *
 * 全屏，刘海处不需要显示布局：
 *      SYSTEM_UI_FLAG_FULLSCREEN | LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
 */

public class SystemUiFlagActivity extends AppCompatActivity {
    private static final String TAG = "SystemUiFlagActivity";
    private boolean isFullscreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_ui_flag);

        findViewById(R.id.picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFullscreen) {
                    full();
                } else {
                    nofull();
                }
                isFullscreen = !isFullscreen;
            }
        });
    }

    private void full()
    {
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            decorView.setSystemUiVisibility(View.GONE);

        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            WindowManager.LayoutParams wl = getWindow().getAttributes();
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            wl.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
            getWindow().setAttributes(wl);

            int uiOption =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN;// |//全屏，状态栏被隐藏覆盖掉
                    //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;//全屏，状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
            decorView.setSystemUiVisibility(uiOption);

        } else {
            int uiOption =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN;// |//全屏，状态栏被隐藏覆盖掉
                    //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;//全屏，状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
            decorView.setSystemUiVisibility(uiOption);
        }
    }

    private void nofull()
    {
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            decorView.setSystemUiVisibility(View.GONE);

        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            WindowManager.LayoutParams wl = getWindow().getAttributes();
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
            wl.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(wl);

            int uiOption =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |//全屏，状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
                    View.SYSTEM_UI_FLAG_FULLSCREEN;//全屏，状态栏被隐藏覆盖掉
            decorView.setSystemUiVisibility(uiOption);

        } else {
            int uiOption =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |//全屏，状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
                    View.SYSTEM_UI_FLAG_FULLSCREEN;//全屏，状态栏被隐藏覆盖掉
            decorView.setSystemUiVisibility(uiOption);
        }
    }

    //获取刘海屏安全显示区域和刘海尺寸信息：
    private void getDisplayCutoutHight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            getWindow().getDecorView().setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @Override
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    DisplayCutout cutout = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                        cutout = windowInsets.getDisplayCutout();
                    }
                    if (cutout == null) {
                        Log.e(TAG, "cutout==null, is not notch screen");//通过cutout是否为null判断是否刘海屏手机
                    } else {
                        List<Rect> rects = cutout.getBoundingRects();
                        if (rects == null || rects.size() == 0) {
                            Log.e(TAG, "rects==null || rects.size()==0, is not notch screen");
                        } else {
                            Log.e(TAG, "rect size:" + rects.size());//注意：刘海的数量可以是多个
                            for (Rect rect : rects) {
                                Log.e(TAG, "cutout.getSafeInsetTop():" + cutout.getSafeInsetTop()
                                        + ", cutout.getSafeInsetBottom():" + cutout.getSafeInsetBottom()
                                        + ", cutout.getSafeInsetLeft():" + cutout.getSafeInsetLeft()
                                        + ", cutout.getSafeInsetRight():" + cutout.getSafeInsetRight()
                                        + ", cutout.rects:" + rect
                                );
                            }
                        }
                    }
                    return windowInsets;
                }
            });
        }
    }

    //经过试验研究，小米和华为的获取的高度就是刘海的高度，并且在不同状态下这个值会有所变化
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
