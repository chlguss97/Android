package com.hyun.tp01numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //화면을 구성하는 View들을 제어하기위한 참조변수를 멤버변수로...
    EditText et;
    Button btn;
    TextView tv;

    //사용자가 맞출 정답 숫자를 저장하는 변수
    int com;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //컴퓨터가 랜덤하게 만들 정답값
        Random rnd= new Random();
        com=rnd.nextInt(501)+500; // 500~1000

        // xml에서 민든 뷰들을 찾아서 참조변수에 대입
        et=findViewById(R.id.et);
        btn=findViewById(R.id.btn);
        tv=findViewById(R.id.tv);

        //버튼이 클릭되는 것에 빈응하는 리스너 객체를 생성 및 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 써 있는 숫자모양의 글씨(String) 얻어오기
                String s= et.getEditableText().toString();
                et.setText("");
                //String --> Int로 변환
                int user= Integer.parseInt(s);

                //정답 숫자(com)와 비교하고 그 결과를 TextView에 보여주기
                if(user > com ) {
                    tv.setText(user+"보다 작습니다.");
                }else if(user< com){
                    tv.setText(user+"보다 큽니다");
                }else{
                    tv.setText("축하합니다!\n정답입니다");
                }



            }


        });

    }
}