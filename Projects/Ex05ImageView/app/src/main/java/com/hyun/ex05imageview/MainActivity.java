package com.hyun.ex05imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // 화면에 보여지는 뷰객체들을 참조하는 참조변수를 멤버변수로...
    ImageView iv;
    Button b1, b2, b3, b4;

    //이미지파일들의 @ResID 를 배열에 차례대로 저장해놓기
    int[] imgs= new int[]{
            R.drawable.flag_australia,
            R.drawable.flag_belgium,
            R.drawable.flag_canada,
            R.drawable.flag_china,
            R.drawable.flag_france,
            R.drawable.flag_germany,
            R.drawable.flag_ghana,
            R.drawable.flag_italy,
            R.drawable.flag_japan,
            R.drawable.flag_korea,
            R.drawable.flag_nepal,
            R.drawable.flag_russia,
            R.drawable.flag_usa,

    };

    int num=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);
        b1= findViewById(R.id.btn01);
        b2= findViewById(R.id.btn02);
        b3= findViewById(R.id.btn03);
        b4= findViewById(R.id.btn04);

        //버튼 클릭에 반응하는 리스너 설정 - 리스너객체는 여러 버튼이 같은 객체 사용
        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);
        b4.setOnClickListener(listener);

        //이미지뷰가 클릭됐을때 반응하는 리스너 생성 및 설정
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지를 순서대로 변경하기..
//                iv.setImageResource(imgs[num]);
//                    num++;
//                    if(num>12) num=0;



                //랜덤하게 이미지를 보여주기
                Random rnd= new Random();
                int n= rnd.nextInt(13); //0~12

                iv.setImageResource(imgs[n]);
            }
        });


    }//onCreate method..

    //버튼들 클릭에 반응하는 리스너객체를 멤버변수로 만들어보기...
    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //이 콜백메소드가 호출될때 클릭된 뷰객체를 참조하는 참조변수가 파라미터로 전달됨
            if( v.getId() == R.id.btn01 ){
                iv.setImageResource(R.drawable.flag_australia);

            }else if( v.getId() == R.id.btn02 ){
                iv.setImageResource(R.drawable.flag_belgium);

            }else if( v.getId() == R.id.btn03 ){
                iv.setImageResource(R.drawable.flag_canada);

            }else if( v.getId() == R.id.btn04 ){
                iv.setImageResource(R.drawable.flag_korea);
            }

        }
    };

}//MainActivity class...