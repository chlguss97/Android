package com.hyun.ex06compoudbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    CheckBox cb01, cb02, cb03;
    Button btn;
    TextView tv;

    ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb01= findViewById(R.id.cb01);
        cb02= findViewById(R.id.cb02);
        cb03= findViewById(R.id.cb03);
        btn= findViewById(R.id.btn);
        tv= findViewById(R.id.tv);

        //버튼 클릭에 반응하는 리스너객체 생성 및 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크된 체크박스의 글씨를 얻어오기
                String s= "";

                if( cb01.isChecked() ) s= s+ cb01.getText().toString();
                if( cb02.isChecked() ) s= s+ cb02.getText().toString();
                if( cb03.isChecked() ) s+= cb03.getText().toString();

                tv.setText( s );
            }
        });


        //체크박스의 체크상태가 변경되는 것을 듣는 리스너 생성
        CompoundButton.OnCheckedChangeListener changeListener= new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //체크된 체크박스의 글씨를 얻어오기
                String s= "";

                if( cb01.isChecked() ) s= s+ cb01.getText().toString();
                if( cb02.isChecked() ) s= s+ cb02.getText().toString();
                if( cb03.isChecked() ) s+= cb03.getText().toString();

                tv.setText( s );
            }
        };

        //체크박스들에 체크상태변경리스너를 설정
        cb01.setOnCheckedChangeListener(changeListener);
        cb02.setOnCheckedChangeListener(changeListener);
        cb03.setOnCheckedChangeListener(changeListener);


        //토글버튼 체크상태변경에 반응하기
        tb= findViewById(R.id.tb);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tv.setText(isChecked+"");
            }
        });


    }
}