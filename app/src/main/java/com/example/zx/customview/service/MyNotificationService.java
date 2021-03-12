package com.example.zx.customview.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.view.View;
import android.widget.RemoteViews;

import com.example.zx.customview.R;

@SuppressLint("OverrideAbstract")
public class MyNotificationService extends NotificationListenerService {

    public MyNotificationService() {
        super();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        //super.onNotificationPosted(sbn);
        Notification notification = sbn.getNotification();

        RemoteViews remoteViews=new RemoteViews(getPackageName(), R.layout.notification_layout);
        /**
         * @param ResId：控件的id
         * @param ResId：图片资源的Id
         */
        notification.contentView = remoteViews;
        //builder.setContent(remoteViews);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
