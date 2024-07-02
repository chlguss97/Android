package com.hyun.ex01hellojava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면에 보여줄 View 를 설정하는 메소드 호출.. 주석처리..
        //setContentView(R.layout.activity_main);

        //Java 언어만으로 화면 꾸미기..

        //액티비티에 놓여질 수 있는 것은 View클래스를 상속받은 클래스들만 가능함.

        //글씨를 보여주는 능력을 가진 View를 객체로 생성하여 액티비티에 설정해보기
        TextView tv= new TextView(this);
        tv.setText("Hello world");

        //setContentView(tv);

        Button btn= new Button(this);
        btn.setText("버튼");

       // setContentView(btn);

        //애석하게 액티비티는 View를 1개만 놓을 수 있음.
        //그래서 여러개의 view를 배치하려면..
        //View들 여러개를 담을 수 있는 ViewGroup을 객체로 만들어서
        // 거기에 모두 붙이고 이 ViewGroup 1개를 액티비티에 붙이는 방식을 사용함

        //ViewGroup 중에서 가장 이해하기 쉽고 많이 사용되는
        //LinearLayout 이라는 클래스를 객체로 생성히여 사용해보기

        LinearLayout layout= new LinearLayout(this); //수평배치가 기본
        layout.addView(tv);
        layout.addView(btn);

        //액티비티에 ViewGroup 1개를 설정
        setContentView(layout);

        //버튼이 클릭되는 것을 듣는 리스너 객체를 생성
        //View.OnClickListener listener= new View.OnclickListener(); //리스너는 인터페이스여서 직접 객체생성 불가능

        //리스너 인터페이스를 구현하는 익명클래스를 설계하면서 객체 생성
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                tv.setText(num+"");
            }
        };


        btn.setOnClickListener(listener);

    }//onCreate method

}//MainActivitiy class