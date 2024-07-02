package com.mrhi2024.mvc.controller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrhi2024.mvc.databinding.ActivityMainBinding
import com.mrhi2024.mvc.model.Item
import com.mrhi2024.mvc.model.ItemModel

class MainActivity : AppCompatActivity() {

    // MVC 패턴 [ Model- View - Controller ]
    // 1)Model - 데이터를 저장하는 클래스나 데이터를 DB/네트워크/파일 등에 저장하거나 불러오는 등의 작업을 하는 코드를 작성하는 파일들
    // 2)View - 사용자가 볼 화면을 구현하는 목적의 코드가 있는 파일들
    // 3)Controller - 뷰와 모델사이에서 연결하는 역할. 클릭 같은 이벤트를 처리하면 뷰의 요청에 따라 model 데이터 제어를 요청하는 작업코드 작성

    // app모듈에서 Activity.kt 에 작성한 코드들을 크게 3가지 역할로 구분해보면..
    // #1. 화면구현
    // #2. 클릭이벤트 처리
    // #3. SharedPreferences 를 이용하여 데이터을 제어하는 코드

    // 역할별로 package를 나누어 작성하면 나중에 위치 검색이 쉬움

    // 안드로이에서는 Activity.kt 문서가 View이면서 Controller 역할도 수행

    // # view 참조변수
    lateinit var binding:ActivityMainBinding

    // # model 참조변수
    lateinit var model: ItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 모델 객체 생성
        model= ItemModel(this)

        // ## view 역할
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ## controller 역할 - 클릭 이벤트 처리 [ 모델역할에게 데이터 제어를 요청 ]
        binding.btnSave.setOnClickListener {
            model.saveData(binding.etName.text.toString(), binding.etEmail.text.toString())
        }
        binding.btnLoad.setOnClickListener {
            val item: Item = model.loadData()
            //뷰에게 데이터를 보여주라고 요청
            binding.tv.text="${item.name} - ${item.email}"
        }
    }
}