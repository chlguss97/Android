package com.hyun.ex49datastoragesharedpreference

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    val inputLayoutName: TextInputLayout by lazy { findViewById(R.id.input_layout_name) }
    val inputLayoutAge: TextInputLayout by lazy { findViewById(R.id.input_layout_age) }
    val tv: TextView by lazy { findViewById(R.id.tv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_save).setOnClickListener { clickSave() }
        findViewById<Button>(R.id.btn_load).setOnClickListener { clickLoad() }
    }

    private fun clickSave(){
        // 저장할 데이터들
        var name: String = inputLayoutName.editText!!.text.toString()
        var age: Int = inputLayoutAge.editText!!.text.toString().toInt()

        // SharedPreference 로 저장하기 - "Data.xml"파일에 저장해주는 객체를 소환하기
        val preference: SharedPreferences = getSharedPreferences("Data", MODE_PRIVATE)

        // 저장작업 시작!! -- 작성자 객체를 리턴해 줌
        val editor: Editor= preference.edit()

        // 작성자를 통해 데이터를 작성
        editor.putString("name", name)
        editor.putInt("age", age)
        editor.putBoolean("sound", true)

        // 작성이 완료되었다고 해야.. 적용됨
        editor.apply() //비동기 방식으로 동작함
    }

    private fun clickLoad(){
        // Data.xml 문서를 읽어주는 SharedPreferenece 객체를 소환
        val preferences: SharedPreferences = getSharedPreferences("Data", MODE_PRIVATE)

        // 자료형별로 데이터 가져오기
        var name:String? = preferences.getString("name", "")
        var age:Int = preferences.getInt("age", 0)
        var sound:Boolean = preferences.getBoolean("sound", true)

        tv.text= "이름: $name   age: $age   sound: $sound"
    }

}