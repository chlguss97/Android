package com.mrhi2024.ex75architecturepattern

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrhi2024.ex75architecturepattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 별도의 아키텍처 패턴 없이 그냥 작성하는 Flat Pattern Design 방식
    // 장점 ) 구조가 단순하여 구현하기 쉽고 하나의 문서에 대부분의 기능코드와  UI코드가 존재하여 전체기능이 한눈에 보임
    // 단점 ) 많아지면. 코드가 너무 비대해짐.. 유지보수 어려움. 데이터를 다른 화면에서 사용한다면..같은 비지니스로직을 또 써야 함. 이거 짜증.

    //그래서 아키텍처 패턴이 등장!!


    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //#화면 작업
        setContentView(binding.root)

        //#버튼 클릭 이벤트 처리
        binding.btnSave.setOnClickListener { saveData() }
        binding.btnLoad.setOnClickListener { loadData() }
    }//

    //# Data 제어(저장/읽기/삭제/변경)작업을 하는 코드(Business Logic) 기능 메소드
    private fun saveData(){
        val pref: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        pref.edit().apply{
            putString("name", binding.etName.text.toString())
            putString("email", binding.etEmail.text.toString())
            commit()
        }
    }

    private fun loadData(){
        val pref: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        val name: String? = pref.getString("name", "")
        val email: String? = pref.getString("email", "")

        binding.tv.text= "${name} - ${email}"

    }

}