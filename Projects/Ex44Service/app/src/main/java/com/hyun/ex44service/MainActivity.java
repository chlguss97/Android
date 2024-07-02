package com.hyun.ex44service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> clickStart());
        findViewById(R.id.btn_stop).setOnClickListener(v -> clickStop());
        
        //알림 허용  동적 퍼미션
        String[] permissions= new String[]{Manifest.permission.POST_NOTIFICATIONS};
        if(checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED ) requestPermissions(permissions,200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if(requestCode==200){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"알림 사용이 가능합니다.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "알림 제한으로 기능 중 일부가 구동되지 않아요", Toast.LENGTH_SHORT).show();
                
            }
        }
        
    }

    void clickStart(){
        //백그라운드 작업을 수행하는 컴포넌트(Service)를 실행하기 위해 Intent 객체를 생성
        Intent intent= new Intent(this, MyService.class);
        startForegroundService(intent); //서비스 구동을 알려주는 Notification을 반드시 만들어야 하는 서비스를 실행하는 메소두

    }

    void clickStop(){
        Intent intent= new Intent(this, MyService.class);
        stopService(intent);


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}