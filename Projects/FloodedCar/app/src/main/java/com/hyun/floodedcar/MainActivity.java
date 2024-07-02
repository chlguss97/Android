package com.hyun.floodedcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView maingif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn).setOnClickListener(v -> {
            Intent intent= new Intent(this,SecondActivity.class);
            startActivity(intent);

            Intent intent3 = new Intent(this, SplashActivity.class);
            startActivity(intent3);





        });

        maingif = (ImageView)findViewById(R.id.main_gif); //GIF ImageView연결
        Glide.with(this).load(R.raw.maingif3).into(maingif);

    }

}