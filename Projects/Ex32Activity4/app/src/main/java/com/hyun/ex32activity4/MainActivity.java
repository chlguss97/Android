package com.hyun.ex32activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v->{
            // 묵시적 인텐트 만들기
            Intent intent= new Intent();
            intent.setAction("bbb");
            startActivity(intent);
        });
    }
}