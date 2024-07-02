package com.hyun.ex31activity3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(v -> {

            // 결과를 받기위해 SecondActivity를 실행하기
            Intent intent= new Intent(this, SecondActivity.class);
            // 결과를 받아오는 Intent의 동작을 대신 관리해주는 대행사 객체에게 액티비티 실행의 의뢰...
            resultLauncher.launch(intent);
        });
    }//onCreate method..

    // 결과를 받는 액티비티 실행을 대신해주는 대행사 객체를 멤버변수로.. 등록
    ActivityResultLauncher<Intent> resultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                //결과를 가지고 돌아온 택배기사님 소환
                Intent intent= result.getData();

                //데이터 받기
                String name= intent.getStringExtra("name");
                int age= intent.getIntExtra("age", -1);

                tv.setText(name +" : " + age);

            }else{
                Toast.makeText(MainActivity.this, "작성을 취소했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    });

}