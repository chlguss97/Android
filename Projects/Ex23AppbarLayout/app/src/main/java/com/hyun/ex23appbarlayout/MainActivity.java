package com.hyun.ex23appbarlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= findViewById(R.id.toolbar);
        tabLayout= findViewById(R.id.tab_layout);

        tabLayout.addTab( tabLayout.newTab().setText("TAB1") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB2") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB3") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB4") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB5") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB6") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB7") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB8") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB9") );
        tabLayout.addTab( tabLayout.newTab().setText("TAB10") );

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("TAB1")){
                    Toast.makeText(MainActivity.this, "tab1", Toast.LENGTH_SHORT).show();
                }else if(tab.getText().toString().equals("TAB2")){
                    Toast.makeText(MainActivity.this, "tab2", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}