package com.hyun.ex46kotlinrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

class IntroActivity : AppCompatActivity() {

    //var btn:Button =findViewById(R.id.btn) // 버튼 못찾음 - 아직 xml 뷰 객체가 만들어지기 전이라서
    var btn:Button? =null

    //늦은 초기화
    lateinit var btn2:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        //btn 참조하기
        btn= findViewById(R.id.btn)
        //버튼 클릭 리스너 생성 및 설정.. Button? nullable 이어서.. null safe 연산자 필요
        btn?.setOnClickListener(object : OnClickListener{ //익명클래스
            override fun onClick(v: View?) {
                // MainActivity 를 실행하기 위해 택배기사 객체 생성
                var intent:Intent = Intent(this@IntroActivity, MainActivity::class.java)
                startActivity(intent)

            }

        })

        btn2= findViewById(R.id.btn2)
        //리스너 처리를 람다표현식으로 간결하게..
        btn2.setOnClickListener({v-> finish()})
        //람다표현식을 더 간결하게 줄임.. ({...}) 이게 짜증 그래서 그냥 {} 만..
        //이를 SAM 변환 기법이라고 부름
        btn2.setOnClickListener{v-> finish()}
        //만약 파라미터가 1개 라면.. 생략이 가능함
        btn2.setOnClickListener { finish() }



    }
}