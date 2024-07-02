package com.hyun.ex27bottomsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    BottomSheetBehavior bottomSheetBehavior;
    MyBottomSheetDialogFragment bsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout= findViewById(R.id.bs);
        bottomSheetBehavior= BottomSheetBehavior.from(layout);

        findViewById(R.id.btn).setOnClickListener(v -> {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        });

        findViewById(R.id.btn2).setOnClickListener(v -> {
            bsd= new MyBottomSheetDialogFragment();
            bsd.show(getSupportFragmentManager(),"aa");

        });

    }
}