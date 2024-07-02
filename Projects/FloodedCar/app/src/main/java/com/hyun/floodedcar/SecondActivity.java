package com.hyun.floodedcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SecondActivity extends AppCompatActivity {



    ViewPager2 pager;
    MyAdapter adapter;
    TabLayout tabLayout;
    String[] tabtitle= new String[]{"침수차 조회","차량정보 조회","유의사항"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pager=findViewById(R.id.pager);
        adapter= new MyAdapter(this);
        pager.setAdapter(adapter);

        tabLayout=findViewById(R.id.tablayout);

        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabtitle[position]);
            }
        }).attach();



            FragmentManager fragmentManager= getSupportFragmentManager();
            Page1Fragment page1Fragment= (Page1Fragment) fragmentManager.findFragmentById(R.id.btn);




        };









}
