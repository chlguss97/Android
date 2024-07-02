package com.hyun.ex03widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;

    int num=0;

    //View 들을 제어하는 참조변수들..멤버변수로...
    EditText et;
    Button btn2;
    TextView tv2;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에 만들어진 View 객체를 찾아와서 참조변수에 대입.
        tv= findViewById(R.id.aa);
        btn= findViewById(R.id.bb);

        //버튼이 클릭되는 것을 듣는 리스너 객체를 생성 --인터페이스여서 추상메소드를 구현하는 클래스를 설계해야함
        //객체를 생성할 때 이름이 없는 클래스를 설계하여 생성 - 익명클래스라고 부름
        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭되었을 때 수행할 작업을 코딩
                num++;
                tv.setText(num+"");
            }
        };

        //버튼에 리스너를 설정하기!
        btn.setOnClickListener(listener);


        et=findViewById(R.id.et);
        et2=findViewById(R.id.et2);
        btn2=findViewById(R.id.btn);
        tv2= findViewById(R.id.tv);

        //버튼2가 클릭되었을 때 EditText에 써있는 글씨를 얻어와서 TextView에 보이기
        //버튼2가 클릭되는 것에 반응하는 리스너 객체 생성 및 설정
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 써 있는 글씨 얻어오기
                String s=et.getText().toString();
                String s2=et2.getText().toString();


                //얻어온 글씨를 TextView에 보여주기
                tv2.setText(s + "\n" + s2);


                //EditText에 써 있는 글씨를 지우기
                et.setText("");
                et2.setText("");


            }
        });


    }//onCreate method
}//MainActivity class