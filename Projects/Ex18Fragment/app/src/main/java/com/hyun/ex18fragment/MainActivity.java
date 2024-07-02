package com.hyun.ex18fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Hello activity!!");
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyFragmewnt 객체를 찾아와서 참조하여 그 안에 있는 TextView를 제어하기


                // Fragment를 관리하는 관리자 객체를 얻어오기
                FragmentManager fragmentManager= getSupportFragmentManager();
                //관리자에게 프레그먼트를 찾아달라고 요청..
                MyFragment myFragment= (MyFragment)fragmentManager.findFragmentById(R.id.frag);
                myFragment.tv.setText("ㅋㅋㅋㅋㅋㅋㅋㅋ");
            }
        });

    }
}