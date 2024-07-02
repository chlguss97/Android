package com.hyun.tp02numberbaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btn;
    TextView tv;
    Random rnd;
    int com;
    int com2;
    int com3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    et=findViewById(R.id.et);
    btn=findViewById(R.id.btn);
    tv=findViewById(R.id.tv);
    Random rnd= new Random();
    com=rnd.nextInt();
    com2=rnd.nextInt();
    com3=rnd.nextInt();

    btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1= Integer.parseInt(et.getText().toString());
                int n2= Integer.parseInt(et.getText().toString());
                int n3= Integer.parseInt(et.getText().toString());
                int strike=0; int ball=0;




            }
        });


















    }
}