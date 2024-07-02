package com.mrhi2021.tp01numberbaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView result;
    Button button;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    int com1,com2,com3;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();
        do {
            com1 = rnd.nextInt(10);
            com2 = rnd.nextInt(10);
            com3 = rnd.nextInt(10);
        } while (com1 == com2 || com1 == com3 || com2 == com3);

        result = findViewById(R.id.result);
        edit1 = findViewById(R.id.edit01);
        edit2 = findViewById(R.id.edit02);
        edit3 = findViewById(R.id.edit03);
        button = findViewById(R.id.button);
        s = "";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1 = Integer.parseInt(edit1.getText().toString());
                int n2 = Integer.parseInt(edit2.getText().toString());
                int n3 = Integer.parseInt(edit3.getText().toString());
                int strike = 0, ball = 0;
                String buf = "";

                if (n1 == com1) strike++;
                else if (n1 == com2 || n1 == com3) ball++;

                if (n2 == com2) strike++;
                else if (n2 == com1 || n2 == com3) ball++;

                if (n3 == com3) strike++;
                else if (n3 == com1 || n3 == com2) ball++;

                buf = String.format("%d%d%d  : %d Strike, %d Ball\n", n1, n2, n3, strike, ball);
                s += buf;

                if (strike == 3) s += " 정답입니다.";
                result.setText(s);
                edit1.setText("");
                edit2.setText("");
                edit3.setText("");
            }
        });

        //텍스트뷰에 스크롤 적용하기
        result.setMovementMethod(new ScrollingMovementMethod());
    }
}