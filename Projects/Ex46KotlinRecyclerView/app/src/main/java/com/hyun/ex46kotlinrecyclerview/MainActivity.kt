package com.hyun.ex46kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // 뷰 참조변수
    val recyclerView:RecyclerView by lazy { findViewById(R.id.recycler_view) }

    // 대량의 데이터를 저장하는 리스트
    var itemList: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //데이터를 추가하기.. -- 실무에서는 서버나. DB에서 값을 읽어옴.
        loadData()

        // 리사이클러뷰에 보여줄 아이템뷰를 만들어주는 아답터 객체 생성 및 설정
        recyclerView.adapter= MyAdapter(this, itemList)

        // 리사이클러뷰의 아이템 배치를 관리하는 관리자 설정
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true)

    }//onCreate method

    // 대량의 데이터를 추가하는 메소드
    private fun loadData(){
        itemList.add(Item("sam","Hello.kotlin", R.drawable.bg_one01))
        itemList.add(Item("robin","Nice.kotlin", R.drawable.bg_one02))
        itemList.add(Item("kim","Good.kotlin", R.drawable.bg_one03))
        itemList.add(Item("man","Hello.jaca", R.drawable.bg_one04))
        itemList.add(Item("him","sad.java", R.drawable.bg_one05))
        itemList.add(Item("girl","Hello.kok", R.drawable.bg_one06))
    }


}//MainActivity class//