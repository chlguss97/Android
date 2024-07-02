package com.hyun.ex31activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class SecondActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    TextInputLayout inputLayoutName, inputLayoutAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toolbar= findViewById(R.id.toolbar);
        inputLayoutName= findViewById(R.id.input_layout_name);
        inputLayoutAge= findViewById(R.id.input_layout_age);

        //글 작성을 취소 하는 상황
        toolbar.setNavigationOnClickListener(v -> finish());

        //작성완료 버튼클릭 하는 상황
        findViewById(R.id.btn).setOnClickListener(v -> {
            //입력된 글씨 얻어오기
            String name= inputLayoutName.getEditText().getText().toString();
            int age= Integer.parseInt(inputLayoutAge.getEditText().getText().toString());

            //MainActivity 에게 돌려줄 데이터를 택배기사를 통해 전달
            Intent intent= getIntent(); // MainActivity로 부터 온 택배기사를 참조하기
            intent.putExtra("name", name); // value - String
            intent.putExtra("age", age);   // value - int

            // 현재 상황이 OK인 상황임을 택배기사에게 설정
            setResult(RESULT_OK, intent);

            //MainActivity로 택배기사가 돌아갈 수 있도록.. 현재 액티비티를 닫기
            finish();

        });

    }
}









