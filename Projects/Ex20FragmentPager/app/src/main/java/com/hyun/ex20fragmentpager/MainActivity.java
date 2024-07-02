package com.hyun.ex20fragmentpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pager;
    MyAdapter adapter;

    TabLayout tabLayout;

    String[] tavTitle= new String[]{"Home","Map","Search"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager=findViewById(R.id.pager);
        adapter=new MyAdapter(this);
        pager.setAdapter(adapter);


        tabLayout=findViewById(R.id.tab_laout);

        //탭레이아웃과 뷰페이저의 동작을 중재하는 중재인 객체를 생성
        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tavTitle[position]);
            }
        }).attach();
    }


}