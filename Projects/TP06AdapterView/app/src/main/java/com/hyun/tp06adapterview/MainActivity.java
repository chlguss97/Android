package com.hyun.tp06adapterview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items = new ArrayList<Item>();

    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      items.add(new Item("ms01", "1",R.drawable.ms_01));
        items.add(new Item("ms02", "1",R.drawable.ms_02));
        items.add(new Item("ms03", "1",R.drawable.ms_03));
        items.add(new Item("ms04", "1",R.drawable.ms_04));
        items.add(new Item("ms05", "1",R.drawable.ms_05));
        items.add(new Item("ms06", "1",R.drawable.ms16));
        items.add(new Item("ms07", "1",R.drawable.ms_07));
        items.add(new Item("ms08", "1",R.drawable.ms_08));

        recyclerView= findViewById(R.id.recycler_view);
        adapter= new MyAdapter(items,this);
        recyclerView.setAdapter(adapter);




    }
}





