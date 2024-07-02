package com.hyun.ex35activityrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items= new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터를 추가 -- 실무에서는 서버에서 가져올것임.
        items.add( new Item("모아나01", "안녕하세요. 모아나 입니다.", R.drawable.moana01) );
        items.add( new Item("모아나02", "반가워요. 모아나 입니다.", R.drawable.moana02) );
        items.add( new Item("모아나03", "싫어요. 모아나 입니다.", R.drawable.moana03) );
        items.add( new Item("모아나04", "그만해요. 모아나 입니다.", R.drawable.moana04) );
        items.add( new Item("모아나05", "이제 쉬자. 모아나 입니다.", R.drawable.moana05) );
        items.add( new Item("모아나06", "안녕하세요. 모아나 입니다.", R.drawable.moana01) );
        items.add( new Item("모아나07", "반가워요. 모아나 입니다.", R.drawable.moana02) );
        items.add( new Item("모아나08", "싫어요. 모아나 입니다.", R.drawable.moana03) );
        items.add( new Item("모아나09", "그만해요. 모아나 입니다.", R.drawable.moana04) );
        items.add( new Item("모아나10", "이제 쉬자. 모아나 입니다.", R.drawable.moana05) );

        recyclerView= findViewById(R.id.recycler_view);
        adapter= new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }
}













