package com.hyun.ex43backgroudtaskbythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyThread t= null; //백그라운드 작업을 수행할 스레드객체의 참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> clickStart());
        findViewById(R.id.btn_stop).setOnClickListener(v -> clickStop());

    }//onCreate method..

    void clickStart(){
        //백그라운드 작업을 별도의 Thread를 이용하여 수행하기
        t= new MyThread();
        t.start();
    }

    void clickStop(){
        if (t==null){
            Toast.makeText(this, "스레드객체를 참조하고 있지 않습니다.", Toast.LENGTH_SHORT).show();
            // 이 메소드의 작업을 여기서 그만..하고 돌아가..
            return;
        }
        t.isRun=false;
    }


    //inner class
    class MyThread extends Thread{

        boolean isRun=true;

        @Override
        public void run() {

            while(isRun){
                // 5초마다 토스트를 보여주는 작업
                runOnUiThread(()-> {
                    Toast.makeText(MainActivity.this, "Ex43", Toast.LENGTH_SHORT).show();
                    Log.d("aaa","Ex43");
                });

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }//while

        }//run method
    }

    // 다바이스의 뒤로가기버튼을 클릭했을때 자동으로 발동하는 콜백메소드


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}//MainActivity class..