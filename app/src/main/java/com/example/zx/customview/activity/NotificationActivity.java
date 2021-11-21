package com.example.zx.customview.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zx.customview.R;

/**
 *
 */

public class NotificationActivity extends AppCompatActivity {
    public static final int NOTIFICATION_CHART = 1;
    public static final int NOTIFICATION_SUBSCRIBE = 2;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        setContentView(R.layout.activity_notification);
        if (!isEnabled()) {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "监控器开关已打开", Toast.LENGTH_SHORT);
            toast.show();
        }

        ImageView btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyTest(0);
            }
        });

        ImageView btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyTest(1);
            }
        });

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId0 = "chat";
            String channelName0 = "聊天消息";
            int importance0 = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel0 = new NotificationChannel(channelId0, channelName0, importance0);
            notificationManager.createNotificationChannel(channel0);

            String channelId = "subscribe";
            String channelName = "订阅消息";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void notifyTest(int i) {
        /*
         * PendingInteng.getBroadcast(contex, requestCode, intent, flags) 
         * PendingInteng.getService(contex, requestCode, intent, flags) 
         * PendingInteng.getActivity(contex, requestCode, intent, flags) 
         * PendingInteng.getActivities(contex, requestCode, intent, flags) 
         *
         * 其中flags属性参数用于确定PendingIntent的行为： 
         * FLAG_ONE_SHOT： 表示返回的PendingIntent仅能执行一次，执行完后自动消失 
         * FLAG_NO_CREATE： 表示如果描述的PendingIntent不存在，并不创建相应的PendingIntent，而是返回NULL 
         * FLAG_CANCEL_CURRENT： 表示相应的PendingIntent已经存在，则取消前者，然后创建新的PendingIntent 
         * FLAG_UPDATE_CURRENT： 表示更新的PendingIntent，如果构建的PendingIntent已经存在，则替换它，常用。
         *
         * 原文链接：https://blog.csdn.net/qq_35507234/article/details/90676587
         */
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        if(i == 0) {
            Notification notification = new NotificationCompat.Builder(this, "chat")
                    .setAutoCancel(true)
                    .setContentTitle("收到聊天")
                    .setContentText("今天周五啦，开心一点")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setColor(Color.parseColor("#234465"))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg))
                    //.setContentIntent(pendingIntent)
                    .build();
            notificationManager.notify(NOTIFICATION_CHART, notification);

        } else {
            Notification notification = new NotificationCompat.Builder(this, "subscribe")
                    .setAutoCancel(true)
                    .setContentTitle("收到订阅消息")
                    .setContentText("日常新闻")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setColor(Color.parseColor("#234465"))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg))
                    //.setContentIntent(pendingIntent)
                    .build();
            notificationManager.notify(NOTIFICATION_SUBSCRIBE, notification);
        }
    }


    // 判断是否打开了通知监听权限
    private boolean isEnabled() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
