package com.hyun.ex15recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    //대량의 데이터를 저장할 리스트 참조변수
    ArrayList<Item> items= new ArrayList<>();

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리사이클러뷰가 보여줄 대량의 데이터들을 추가하기 [실무에서는 서버에 있는 DB를 읽어오는 경우가 많음]
        items.add( new Item("sam", "Hello android.") );
        items.add( new Item("robin", "Nice to meet you.")  );
        items.add( new Item("홍길동", "안녕하세요.\n처음 뵙겠습니다.") );
        items.add( new Item("kim", "aaaaa.\n처음 뵙겠습니다.") );
        items.add( new Item("hong", "bbbb.\n처음 뵙겠습니다.") );

        recyclerView= findViewById(R.id.recycler_view);
        //int size= items.size();

        adapter= new MyAdapter( this, items );
        recyclerView.setAdapter(adapter);

    }
}

