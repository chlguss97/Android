package com.hyun.ex42broadcastreceiverbooting;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;


// 4대 컴포넌트는 반드시 androidManifest.xml에 등록해야 사용 가능함
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i("aaa", "부팅완료 방송을 수신했습니다.");

            // 사용자에게 알림(Notification)을 보여주기 - 알림은 동적퍼미션 필요

            // 알림매니저 소환하기
            NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel= new NotificationChannel("ch01","Ex42 알림", NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"ch01");

            //건축가에게 알림에 대한 설정들
            builder.setSmallIcon(R.drawable.ic_stat_noti);
            builder.setContentTitle("Ex42 부팅완료 리시버 테스트");
            builder.setContentText("부팅이 완료되는 것을 수신했습니다.");

            Intent i= new Intent(context,MainActivity.class);
            PendingIntent pendingIntent= PendingIntent.getActivity(context,11,i,PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(pendingIntent);


            Notification notification= builder.build();

            // 매니저에게 알림을 요청
            notificationManager.notify(100, notification);
        }


    }
}
