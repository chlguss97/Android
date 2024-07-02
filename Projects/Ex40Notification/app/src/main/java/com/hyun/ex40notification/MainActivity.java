package com.hyun.ex40notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
    }

    void clickBtn(){

        // 안드로이드 13 버전부터는 알림을 사용할때 사용자의 허가(permission)를 요청하는 다이얼로그를 보여줘야 함.
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            //알림 퍼미션에 대한 요청..단, 한번 허용하면 다시 물어보지 않도록 하기 위해..
            //알림 퍼미션이 허용되어 있는지 확인
            int permissionResult= checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS);
            if(permissionResult== PackageManager.PERMISSION_DENIED){
                //알림 퍼미션 사용 요청 다이얼로그를 보여주도록 요청
                String[] permissions= new String[]{ Manifest.permission.POST_NOTIFICATIONS };
                requestPermissions(permissions, 100);
            }
        }

        // 알림(Notification)을 관리하는 관리자객체 소환하기
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 알림객체 만들기 - 알림 건축가 객체에게 의뢰
        // 알림채널객체 만들기 - 알림매니저가 만들어 줌
        NotificationChannel channel= new NotificationChannel("ch01","MyChannel", NotificationManager.IMPORTANCE_HIGH);

        // 알림음 설정
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.s_goodjob);
        channel.setSound(uri, new AudioAttributes.Builder().build());

        notificationManager.createNotificationChannel(channel);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this, "ch01");

        // 알림의 내용을 설정 - 건축가에게 의뢰

        // 상태표시줄에 보여질 작은 아이콘
        builder.setSmallIcon(R.drawable.ic_stat_noti);

        // 상태바를 드래그하여 아래로 내리면 보이는 알림창(확장 상태바)의 설정
        builder.setContentTitle("Ex40의 알림");
        builder.setContentText("알림 메세지를 이곳에 보여줍니다.");

        Bitmap bm= BitmapFactory.decodeResource(getResources(), R.drawable.paris);
        builder.setLargeIcon(bm);

        // 알림창을 클릭했을때 반응하여 실행할 액티비티를 실행하는 Intent를 생성
        Intent intent= new Intent(this, SecondActivity.class);
        //알림창을 클릭할때까지 실행을 보류하는 PendingIntent 로 만들기
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true); //알림창 클릭시에..스몰아이콘 삭제

        // 알림창에 추가 액션을 만들기
        builder.addAction(R.drawable.ic_stat_noti, "둘러보기", pendingIntent);
        builder.addAction(R.drawable.ic_stat_noti, "옵션", pendingIntent);

        // 알림창에 특별한 스타일을 적용
        NotificationCompat.BigPictureStyle picStyle= new NotificationCompat.BigPictureStyle(builder);
        picStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.newyork));

        Notification notification= builder.build();

        // 매니저에게 알림을 보내도록 요청
        notificationManager.notify(10, notification);

    }//onCreate method..

    //퍼미션 요청 다이얼로그에서 결과를 선택했을때 자동으로 발동하는 콜백메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "알림을 허용하셨습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "알림을 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

    }
}//MainActivity class..