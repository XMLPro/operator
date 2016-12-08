package com.example.c0115334.operator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by c0115334 on 2016/12/03.
 */

public class BedTimeReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("notificationId", 0);
        NotificationManager myNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder( context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("寝ましょう！！")
                .setContentText("スマホを見るな！")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
        ;

        myNotification.notify(notificationId, builder.build());

    }
}
