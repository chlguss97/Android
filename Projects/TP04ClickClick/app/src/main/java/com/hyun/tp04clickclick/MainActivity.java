package com.hyun.tp04clickclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView st;
    LinearLayout ll01;
    LinearLayout ll02;
    LinearLayout ll03;
    LinearLayout ll04;
    TextView tv;
    ArrayList<Integer> img= new ArrayList<>();

    ImageView[] nums;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    tv= findViewById(R.id.tv);
    st= findViewById(R.id.st);
    st.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        ll01=findViewById(R.id.ll01);
        ll02=findViewById(R.id.ll02);
        ll03=findViewById(R.id.ll03);
        ll04=findViewById(R.id.ll04);

        ll01.setVisibility(View.VISIBLE);
        ll02.setVisibility(View.VISIBLE);
        ll03.setVisibility(View.VISIBLE);
        ll04.setVisibility(View.VISIBLE);
        st.setImageResource(R.drawable.ing);
        tv.setVisibility(View.INVISIBLE);



            for(int i=0; i<11;  i++ ) {
                nums[i]= findViewById(R.id.iv01+i);

            }

            for(int i=0; i<11;  i++ ){
                ArrayList<integer>




            }









        }
    });



    }





        }




