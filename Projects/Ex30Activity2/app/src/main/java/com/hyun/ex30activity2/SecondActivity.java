package com.hyun.ex30activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 이 액티비티를 실행하기 위핸 넘어온 택배기사 참조하기
        Intent intent= getIntent();

        // 택배기사가 가지고 온 추가데이터(Extra data)를 자료형별로 얻어오기
        String name= intent.getStringExtra("name"); // (식별자)
        int age= intent.getIntExtra("age", -1); //(식별자, 전달된 값이 없을때의 기본값)

        TextView tv= findViewById(R.id.tv);
        tv.setText("이름 : " + name +"\n나이 : "+age);

    }
}