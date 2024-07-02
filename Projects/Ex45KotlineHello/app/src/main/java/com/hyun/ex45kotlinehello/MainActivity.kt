package com.hyun.ex45kotlinehello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 화면에 있는 뷰객체들을 찾아와서 참조하기
        var btn:Button = findViewById(R.id.btn)

        // 버튼 클릭에 반응하는 리스너 생성 및 등록 - 람다표현식 ==> SAM변환 기법

        btn.setOnClickListener { clickBtn() }

    }//onCreate method..


    fun clickBtn(){
        var tv =findViewById<TextView>(R.id.tv )
        tv.text="Nice to meet you"
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    fun aaa(){

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("dialog")
        builder.setMessage("Do you wanna quit?")
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

    fun bbb(){
        val dialog:AlertDialog = AlertDialog.Builder(this).run {
            setTitle("dialog")
            setMessage("Do you wanna quit?")
            create()
        }
        dialog.show()
    }


}