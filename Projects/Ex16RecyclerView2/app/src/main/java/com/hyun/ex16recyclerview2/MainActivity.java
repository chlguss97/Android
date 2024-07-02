package com.hyun.ex16recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items= new ArrayList<Item>();

    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 대량의 데이터를 추가하기
        items.add( new Item("루피", "밀집모자해적단 선장", R.drawable.bg_one01) );
        items.add( new Item("조로", "밀집모자해적단 부선장", R.drawable.bg_one02) );
        items.add( new Item("나미", "밀집모자해적단 항해사", R.drawable.bg_one03) );
        items.add( new Item("우솝", "밀집모자해적단 저격수", R.drawable.bg_one04) );
        items.add( new Item("상디", "밀집모자해적단 요리사", R.drawable.bg_one05) );
        items.add( new Item("쵸파", "밀집모자해적단 의사", R.drawable.bg_one06) );
        items.add( new Item("로빈", "밀집모자해적단 고고학자", R.drawable.bg_one07) );
        items.add( new Item("프랑크", "밀집모자해적단 조선공", R.drawable.bg_one08) );

        //2. 아이템뷰 1개의 디자인 시안 .xml 모양 레이아웃 설계

        //3. Item 데이터의 개수만큼 xml 시안 모양으로 아이템뷰 객체들을 생성해주는 아답터 클래스 설계 및 객체 생성
        recyclerView= findViewById(R.id.recycler_view);
        adapter= new MyAdapter(this, items);
        recyclerView.setAdapter(adapter); //4. 리사이클러뷰와 연결하기
    }
}