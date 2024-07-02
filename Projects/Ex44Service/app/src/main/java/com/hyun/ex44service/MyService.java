package com.hyun.ex44service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

// 4대 컴포넌트는 AndroidManifest.xml 에 등록을 해야 사용이 가능함
public class MyService extends Service {

    MediaPlayer mp;

    // startService()를 통해 서비스객체를 실행하면 자동으로 발동하는 콜백메소드 [ 라이프사이클 메소드 ]
    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // startForegroundService()로 실행했다면... 반드시 알림을 보여줘여야 함.

        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel= new NotificationChannel("ch01","Ex44", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);
        Notification.Builder builder= new Notification.Builder(this, "ch01");

        builder.setSmallIcon(R.drawable.ic_stat_music);
        builder.setContentTitle("Ex44 Music Service");
        builder.setContentText("음악이 실행중입니다");

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,20,i,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        Notification notification= builder.build();
        startForeground(100, notification);



        // 음악 재생 시작
        if(mp==null){
            mp= MediaPlayer.create(this, R.raw.kalimba);
            mp.setVolume(0.7f, 0.7f);
            mp.setLooping(true);
        }

        mp.start();

        return START_STICKY; // 시스템이 메모리 부족으로 강제로 서비스를 종료했을때.. 이후 공간이 생기면 자동으로 다시 구동하는 설정값
    }

    // stopService()를 통해 서비스객체를 종료하면 자동으로 발동하는 콜백메소드 [ 라이프사이클 메소드 ]
    @Override
    public void onDestroy() {

        // 음악 재생 종료
        if(mp!=null){
            mp.stop();
            mp.release();
            mp= null;
        }

        // 포어그라운드 서비스를 멈추도록..
        stopForeground(true);

        super.onDestroy();
    }

    // 이 서비스를 실행하는 Activity 에서 참조하고 싶을 때.. 사용하는 기능메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
