package com.hyun.ex26bottomappbar;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomAppBar bab;
    FloatingActionButton fab;

    boolean isUp=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bab= findViewById(R.id.bab);
        bab.setNavigationOnClickListener(v -> {
            Toast.makeText(this, "aa",Toast.LENGTH_SHORT).show();
        });

        fab= findViewById(R.id.fab);
        fab.setOnClickListener(v -> {

            if(!isUp){
                ObjectAnimator animator= ObjectAnimator.ofFloat(fab,"translationY",- 150f);
                animator.setDuration(100);
                animator.start();

                isUp=true;
            }else{
                ObjectAnimator.ofFloat(fab,"translationY",0f).setDuration(100).start();
                isUp=false;
            }

        });

    }
}