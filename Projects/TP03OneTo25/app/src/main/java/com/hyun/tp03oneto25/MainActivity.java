package com.hyun.tp03oneto25;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button[] btns= new Button[25];
    Button btnRetry;

    // 현재 눌러야할 번호를 저장하고 있는 변수
    int num= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1~25의 숫자를 요소들로 가지는 기차 객체(List) 생성
        ArrayList<Integer> numbers= new ArrayList<Integer>();
        for(int i=1; i<26; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers); //요소들 섞어주기..

        tv= findViewById(R.id.tv);
        btnRetry= findViewById(R.id.btn_retry);

        for(int i=0; i<25; i++){
            btns[i]= findViewById(R.id.btn01+i);
            btns[i].setText(""+ numbers.get(i) );
            btns[i].setOnClickListener(listener);
            btns[i].setTag(numbers.get(i));
        }
        //tv.setText(R.id.btn01+"\n"+R.id.btn02+"\n"+R.id.btn03);


        //재시작 버튼 클릭시에 반응하기
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Integer> numbers= new ArrayList<Integer>();
                for(int i=1; i<26; i++){
                    numbers.add(i);
                }
                Collections.shuffle(numbers); //요소들 섞어주기..

                for(int i=0; i<btns.length; i++){
                    btns[i].setVisibility(View.VISIBLE);
                    btns[i].setText(numbers.get(i) +"");
                    btns[i].setTag(numbers.get(i));
                }

                num=1;
                tv.setText(num+"");

            }
        });

    }//onCreate method..

    //버튼들 클릭할때 반응하는 리스너객체 생성 - 멤버변수로...
    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //클릭된 버튼에 있는 글씨가 현재 눌러야 할 번호와 같은지 비교
//            Button btn= (Button)v;
//            String s= btn.getText().toString();
//            int n= Integer.parseInt(s);

            //클릭된 버튼에 저장되어 있는 tag 값을 가져와서 눌러야 할 번호와 같은지 비교
            String s= v.getTag().toString();
            int n= Integer.parseInt(s);

            if(n==num){
                //btn.setText("OK");
                //btn.setTextColor(0xFFFF0000); //ARGB - A:투명도
                //btn.setVisibility(View.INVISIBLE);
                //btn.setBackground(null);
                v.setVisibility(View.INVISIBLE);

                num++;
                tv.setText(num+"");
            }

            if(num>25){
                tv.setText("CLEAR~~");
                btnRetry.setEnabled(true);
            }

        }
    };

}//MainActivity class...