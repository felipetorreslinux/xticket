package com.xticket.Utils;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.xticket.R;
import com.xticket.Views.View_Login;

public class NotificationsApp {

    static Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    public static void send (final Activity activity, int id_notification, int icon, String title, String message){
        NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(activity)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(sound)
                .setVibrate(new long[]{1000})
                .setContentText(message);
        Intent resultIntent = new Intent(activity, View_Login.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(activity);
        stackBuilder.addParentStack(View_Login.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(String.valueOf(id_notification), "notify", NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }
        mNotificationManager.notify(id_notification, mBuilder.build());
    }

}
