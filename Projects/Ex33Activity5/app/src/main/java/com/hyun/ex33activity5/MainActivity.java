package com.hyun.ex33activity5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.b).setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction("aaa");

            startActivity(intent);
        });
    }
}