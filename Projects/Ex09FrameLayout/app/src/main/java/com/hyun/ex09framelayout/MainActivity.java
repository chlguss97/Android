package com.hyun.ex09framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;
    LinearLayout layout01;
    LinearLayout layout02;
    RelativeLayout layout03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.btn01);
        b2=findViewById(R.id.btn02);
        b3=findViewById(R.id.btn03);

        layout01=findViewById(R.id.layout01);
        layout02=findViewById(R.id.layout02);
        layout03=findViewById(R.id.layout03);

        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);




            }

            View.OnClickListener listener= new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(v.getId()== R.id.btn01){
                        layout01.setVisibility(View.VISIBLE);
                        layout02.setVisibility(View.GONE);
                        layout03.setVisibility(View.GONE);
                    } else if (v.getId()==R.id.btn02) {
                        layout02.setVisibility(View.VISIBLE);
                        layout01.setVisibility(View.GONE);
                        layout03.setVisibility(View.GONE);

                    } else if (v.getId()==R.id.btn03) {
                        layout03.setVisibility(View.VISIBLE);
                        layout01.setVisibility(View.GONE);
                        layout02.setVisibility(View.GONE);

                    }


                };



    };
}