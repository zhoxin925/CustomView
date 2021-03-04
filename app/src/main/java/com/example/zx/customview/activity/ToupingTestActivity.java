package com.example.zx.customview.activity;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.zx.customview.R;
import com.example.zx.customview.touping.MyPresentation;

public class ToupingTestActivity extends Activity {
    private Display[] displays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout content = findViewById(R.id.content);
        /*RecyclerView recyclerView = findViewById(R.id.recyckerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter();*/

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaRouter mediaRouter = (MediaRouter) ToupingTestActivity.this.getSystemService(Context.MEDIA_ROUTER_SERVICE);
                MediaRouter.RouteInfo routeInfo = mediaRouter.getSelectedRoute(MediaRouter.ROUTE_TYPE_LIVE_VIDEO);
                if (routeInfo != null) {
                    Display presenDisplay = routeInfo.getPresentationDisplay();
                    if (presenDisplay != null) {
                        MyPresentation myPresentation = new MyPresentation(ToupingTestActivity.this, presenDisplay);
                        myPresentation.show();
                    }
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
                        textView.setBackgroundColor(0xffeeeeee);
                        textView.setLayoutParams(ll);
                        textView.setText(displays[i].getName());
                        textView.setTag(i);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                MyPresentation presentation = new MyPresentation(ToupingTestActivity.this, displays[(int) v1.getTag()]);
                                presentation.show();
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