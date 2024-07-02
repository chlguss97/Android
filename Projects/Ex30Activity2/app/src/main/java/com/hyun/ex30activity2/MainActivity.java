package com.hyun.ex30activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout inputLayoutName;
    TextInputLayout inputLayoutAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLayoutName= findViewById(R.id.input_layout_name);
        inputLayoutAge= findViewById(R.id.input_layout_age);

        findViewById(R.id.btn).setOnClickListener(v -> {
            //SecondActivity 에 보낼 데이터를 얻어오기
            String name= inputLayoutName.getEditText().getText().toString();
            int age= Integer.parseInt(inputLayoutAge.getEditText().getText().toString());

            //SecondActivity 를 실행하는 택배기사 객체(Intent) 생성
            Intent intent= new Intent(this, SecondActivity.class);

            //택배기사가 직접 SecondActivity로 넘어가기에.. 넘겨 줄 데이터가 있다면 택배기사에게 주어야 함
            intent.putExtra("name", name); // (식별자, 값 - String)
            intent.putExtra("age", age);   // (식별자, 값 - int)

            //실행
            startActivity(intent);

        });

        findViewById(R.id.btn2).setOnClickListener(v -> {
            Intent intent= new Intent(this, ThirdActivity.class);
            startActivity(intent);
        });

    }
}