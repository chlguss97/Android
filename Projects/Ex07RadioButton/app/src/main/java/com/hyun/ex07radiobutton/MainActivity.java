package com.hyun.ex07radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    Button btn;
    TextView tv;

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg= findViewById(R.id.rg);
        btn= findViewById(R.id.btn);
        tv= findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rg 안에 있는 것 중 체크가 된 RadioButton이 누구인지..
                int checkedId = rg.getCheckedRadioButtonId();
                RadioButton rb= findViewById(checkedId); // id를 통해 체크된 RadioButton객체 참조

                tv.setText(rb.getText().toString());
            }
        });

        //라디오버튼마다 변뎡리스너를 설정하지 않고..
        // 라디오그룹 전용 체크변경리스너를 사용함.
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= findViewById(checkedId);
                tv.setText(rb.getText().toString());
            }
        });

        ratingBar=findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tv.setText(rating+"점");
            }
        });

    }
}