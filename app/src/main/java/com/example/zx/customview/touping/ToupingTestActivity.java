package com.example.zx.customview.touping;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.zx.customview.R;

import java.util.ArrayList;
import java.util.List;

public class ToupingTestActivity extends Activity {
    private Display[] displays;
    private SparseArray<MyPresentation> presentations = new SparseArray<>();
    private List<BitmapDrawable> drawableList = new ArrayList<>();
    private int index;
    private Bitmap bitmap;

    public static Bitmap getBitmap(Context context, int resId){/*Bitmap bitmap1 = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inBitmap = bitmap1;
        bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher, options);
        Bitmap.Config config = bitmap1.getConfig();
        bitmap1.setConfig(Bitmap.Config.ARGB_8888);*/

        BitmapFactory.Options options = new BitmapFactory.Options();
        TypedValue value=new TypedValue();
        context.getResources().openRawResource(resId, value);
        options.inTargetDensity = value.density;
        options.inScaled = false;
        return BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout content = findViewById(R.id.content);
        /*RecyclerView recyclerView = findViewById(R.id.recyckerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter();*/

        bitmap = getBitmap(this, R.drawable.bg);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            BitmapDrawable drawable1 = (BitmapDrawable) this.getResources().getDrawable(R.drawable.bg, null);
            BitmapDrawable drawable2 = (BitmapDrawable) this.getResources().getDrawable(R.drawable.bg2, null);
            BitmapDrawable drawable3 = (BitmapDrawable) this.getResources().getDrawable(R.drawable.ic_picture, null);
            BitmapDrawable drawable4 = (BitmapDrawable) this.getResources().getDrawable(R.drawable.ic_yinbiao, null);
            BitmapDrawable drawable5 = (BitmapDrawable) this.getResources().getDrawable(R.drawable.ic_launcher_round, null);

            drawableList.add(drawable1);
            drawableList.add(drawable2);
            drawableList.add(drawable3);
            drawableList.add(drawable4);
            drawableList.add(drawable5);
        }

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaRouter mediaRouter = (MediaRouter) ToupingTestActivity.this.getSystemService(Context.MEDIA_ROUTER_SERVICE);
                MediaRouter.RouteInfo routeInfo = mediaRouter.getSelectedRoute(MediaRouter.ROUTE_TYPE_LIVE_VIDEO);
                if (routeInfo != null) {
                    Display presenDisplay = routeInfo.getPresentationDisplay();
                    if (presenDisplay != null) {
                        final MyPresentation myPresentation = new MyPresentation(ToupingTestActivity.this, presenDisplay);
                        myPresentation.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                myPresentation.updateContent(bitmap);
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(ToupingTestActivity.this, "搜索不到设备！！！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ToupingTestActivity.this, "搜索不到设备！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayManager displayManager = (DisplayManager) ToupingTestActivity.this.getSystemService(Context.DISPLAY_SERVICE);
                displays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
                if (displays.length > 0) {
                    for (int i = 0; i < displays.length; i++) {
                        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                                , LinearLayout.LayoutParams.WRAP_CONTENT);
                        ll.leftMargin = 30;
                        ll.topMargin = 30;
                        ll.rightMargin = 30;
                        ll.bottomMargin = 30;

                        TextView textView = new TextView(ToupingTestActivity.this);
                        textView.setBackgroundColor(0xeeeeeeee);
                        textView.setTextColor(0xff555555);
                        textView.setLayoutParams(ll);
                        textView.setText(displays[i].getName());
                        textView.setTag(i);
                        textView.setPadding(20, 10, 10, 20);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                showPresentation(v1);
                            }
                        });
                        content.addView(textView);
                    }
                } else {
                    Toast.makeText(ToupingTestActivity.this, "搜索不到任何设备！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showPresentation(View v1) {
        MyPresentation presentation = presentations.get(displays[(int) v1.getTag()].getDisplayId());
        if(presentation != null) {
            if(index >= 5) {
                index = 0;
            }
            presentation.updateContent(drawableList.get(index));
            index++;
        } else {
            presentation = new MyPresentation(ToupingTestActivity.this, displays[(int) v1.getTag()]);
            presentation.show();
            presentation.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    MyPresentation presentation1 = (MyPresentation) dialog;
                    int displayId = presentation1.getDisplay().getDisplayId();
                    presentations.remove(displayId);
                }
            });
            presentations.append(displays[(int) v1.getTag()].getDisplayId(), presentation);
        }
    }

    private void showPresentation() {
        if(displays == null)
        {
            return;
        }
        for (int i = 0; i < displays.length; i++) {
            MyPresentation pre = presentations.get(displays[i].getDisplayId());
            if(pre != null) {//都被请了？
                pre.updateContent(bitmap);
            } else {
                pre = new MyPresentation(ToupingTestActivity.this, displays[i]);
                pre.show();
                pre.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        MyPresentation presentation1 = (MyPresentation) dialog;
                        int displayId = presentation1.getDisplay().getDisplayId();
                        presentations.remove(displayId);
                    }
                });
                presentations.append(displays[i].getDisplayId(), pre);
            }
        }
    }

    private void hidePresentation() {
        for (int i = 0; i < displays.length; i++) {
            MyPresentation pre = presentations.get(displays[i].getDisplayId());
            if(pre != null) {
                pre.dismiss();
                presentations.remove(pre.getDisplay().getDisplayId());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showPresentation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hidePresentation();
    }

    /*private class DisplayListAdapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }*/
}