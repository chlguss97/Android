package com.hyun.ex60viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.hyun.ex60viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // findViewByid() 기능은 코드도 번거롭고 성능도 떨어짐. 이를 개선하기 위한 기술 ==> view binding
    // 이 기능은 라이브러리가 아님. 이미 존재하는 기능인데 .. off 되어 있을 뿐임. 이를 on 시키면 사용가능 -- build.gradle 에서 작성
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main) //액티비티가 직접 뷰를 만들지 않음

        // activity_main.xml을 뷰객체를 만들어서 안에 있는 뷰들의 참조를 모두 대신해주는 특별한 객체 - 바인딩 객체
        binding= ActivityMainBinding.inflate(layoutInflater)
        //바인딩 객체가 만든 뷰객체를 액티비티가 보여주도록 설정
        setContentView(binding.root)

        binding.tv.text="Nice to meet you"
        binding.btn.setOnClickListener { binding.tv.text="clicked button" }

        binding.btn2.setOnClickListener { binding.tv2.text=binding.inputLayout.editText!!.text.toString() }
    }//onCreate method

    //리사이클러가 보여줄 대량의 데이터
    var itemList: MutableList<Item> = mutableListOf()

    //액티비티가 화면에 완전히 보여질때 실행되는 라이프사이클 메소드

    override fun onResume() {
        super.onResume()

        //대량의 데이터를 추가
        itemList.add(Item(R.drawable.newyork,"뉴욕"))
        itemList.add(Item(R.drawable.paris,"paris"))
        itemList.add(Item(R.drawable.sydney,"sydney"))
        itemList.add(Item(R.drawable.newyork,"뉴욕"))
        itemList.add(Item(R.drawable.paris,"파리"))
        itemList.add(Item(R.drawable.sydney,"시드니"))

        //아답터 객체 생성 및 리사이클러뷰에 설정!!
        //binding.recyclerView.adapter=MyAdapter(this,itemList)
        binding.recyclerView.adapter= MyAdapter2(this, itemList)


    }




}