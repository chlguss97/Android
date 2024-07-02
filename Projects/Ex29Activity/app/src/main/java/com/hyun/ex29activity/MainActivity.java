package com.hyun.ex29activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v -> {
            // SecondActivity 실행시켜주는 택배기사 같은 객체(Intent) 생성
            Intent intent= new Intent(this, SecondActivity.class);
            startActivity(intent);// 택배기사에게 실행하도록 요청.

            //현재 액티비티를 종료시키는 기능호출
            finish();
        });

        findViewById(R.id.btn2).setOnClickListener( v-> {
            //ThirdActivity 실행
            Intent intent= new Intent(this, ThirdActivity.class);
            startActivity(intent);
        });

    }
}