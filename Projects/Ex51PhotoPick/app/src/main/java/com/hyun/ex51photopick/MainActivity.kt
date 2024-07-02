package com.hyun.ex51photopick

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    val tv:TextView by lazy { findViewById(R.id.tv) }
    val iv: ImageView by lazy { findViewById(R.id.iv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener { clickBtn1() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickBtn2() }
        findViewById<Button>(R.id.btn3).setOnClickListener { clickBtn3() }
    }

    private fun clickBtn1(){
        //photo app or gallery app 을 실행시켜주는 Intent 객체
        val intent: Intent = Intent(Intent.ACTION_PICK).setType("image/*") // -- 미디어 파일만 선택가능
        // 결과를 받아오는 작업을 대신 수행하는 대행사 객체를 이용하여 사진앱 실행
        resultLauncher.launch(intent)

    }

    // 결과 받아오는 작업을 대행해저는 대행사 객체를 등록
    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),object : ActivityResultCallback<ActivityResult>{
        override fun onActivityResult(result: ActivityResult) {
            // 사진선택 결과를 받았을때 실행되는 영역
            if(result.resultCode== RESULT_OK){
                //선택된 사진의 경로 uri 를 가지고 온 Intent 택배기사 소환
                val intent:Intent? = result.data
                // 택배기사가 가지고 온 data (사진 경로 uri ) 를 얻기
                val uri: Uri? = intent?.data

                //경로 홛인해보기
                tv.text=uri.toString()
                Glide.with(this@MainActivity).load(uri).into(iv)
            }
            }
    })

    private fun clickBtn2(){
        //(구글 권장 ACTION)
        val intent:Intent= Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*")
        resultLauncher.launch(intent)


    }

    private fun clickBtn3(){
        // 새로운 사진 선택 UI/UX -- BottomSheet 처럼 등장하는 UI를 가진 사진 선택기 [요즘 유행..]
        // JetPack Library 에 같이 포함되어 추가된 기능임
        pickMediaResultLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))


    }

    // JetPack 에서 새롭게 제공된.. 사진 산택기의 결과를 대신 받아주는 대행사 객체를 등록
    val pickMediaResultLauncher: ActivityResultLauncher<PickVisualMediaRequest> = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){uri->
        tv.text= uri.toString()
        Glide.with(this).load(uri).into(iv)
    }



}