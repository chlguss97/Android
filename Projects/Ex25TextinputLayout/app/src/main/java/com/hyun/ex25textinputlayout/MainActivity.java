package com.hyun.ex25textinputlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextInputLayout inputLayout;
    TextInputLayout inputLayout2;

    //드롭다운 메뉴에 보일 글씨를 있을 리스트 객체
    ArrayList<String> items= new ArrayList<>();
    ArrayAdapter adapter;
    AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLayout= findViewById(R.id.input_layout);


        //익명클래스의 추상메소드 구현 코드가 너무 지저분.. 이를 축약표현으로 줄여 쓰는 문법 [ 람다식 ]
        findViewById(R.id.btn).setOnClickListener(v -> {
            String s= inputLayout.getEditText().getText().toString();
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        });

        items.add("apple");
        items.add("Banana");
        items.add("Orange");

        inputLayout2=findViewById(R.id.input_layout2);
        adapter=new ArrayAdapter(this, R.layout.dropdown_item, items);

        actv= (AutoCompleteTextView)inputLayout2.getEditText();
        actv.setAdapter(adapter);

        findViewById(R.id.btn2).setOnClickListener(v -> {
            String s= actv.getText().toString();
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        });

    }
}