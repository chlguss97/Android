package com.hyun.ex30activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        findViewById(R.id.btn).setOnClickListener(v -> {
            Intent intent= new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }
}