package com.hyun.ex41broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    findViewById(R.id.btn).setOnClickListener(v -> clickBtn());



        }

    void clickBtn(){
        //방송보내기.. 방송이라고 했지만 실제로는 Intent 객체를 보내는 것임 like . 퀵실버
        Intent intent= new Intent(this, MyReceiver.class);
        sendBroadcast(intent);


    }
}