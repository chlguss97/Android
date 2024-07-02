package com.hyun.ex35activityrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class DetailActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    ImageView iv;
    TextView  tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //넘어온 택배기사 참조
        Intent intent= getIntent();
        String title= intent.getStringExtra("title");
        String message= intent.getStringExtra("msg");
        int imgId= intent.getIntExtra("imgId", R.drawable.ic_launcher_background);

        toolbar= findViewById(R.id.toolbar);
        iv= findViewById(R.id.iv);
        tv= findViewById(R.id.tv);

        // title, message, imgId
        toolbar.setTitle(title);
        tv.setText(message);

        iv.setImageResource(imgId);
        //이전 화면에서 [전환효과]를 위해 특정뷰에 별칭이 있을 것임. "ss"
        iv.setTransitionName("ss");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }
}