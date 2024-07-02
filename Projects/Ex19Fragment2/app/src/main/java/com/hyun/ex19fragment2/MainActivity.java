package com.hyun.ex19fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //프레그먼트를 동적으로 추가하기..

                //프레그먼트 매니저를 얻어오기
                FragmentManager fragmentManager= getSupportFragmentManager();

                //프레그먼트에게 동적으로 MyFragment 객체를 추가하는 작업을 시작
                FragmentTransaction transaction =fragmentManager.beginTransaction();

                //원하는 작업 수행[동적 추가/제거/변경]
                transaction.add(R.id.container,new MyFragment());
                //프레그먼트를 뒤로가기버튼으로 이전 상황으로 돌리고 싶다면
                transaction.addToBackStack(null);


                //트랜잭션 작업 완료 명령!을 해야 실제 적용됨
                transaction.commit();
            }
        });



    }
}