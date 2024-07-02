package com.mrhi2024.tf01flowerimageclassification

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrhi2024.tf01flowerimageclassification.databinding.ActivityMainBinding
import com.mrhi2024.tf01flowerimageclassification.ml.Model
import org.tensorflow.lite.support.image.TensorImage

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btn.setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){
        // model.tflite 모델을 사용하여 꽃 종류 분류 기능 구현..

        //# 1. 모델 객체 생성
        val model:Model = Model.newInstance(this)

        //# 2. 입력이미지 준비
        val bm = (binding.iv.drawable as BitmapDrawable).bitmap
        val image:TensorImage = TensorImage.fromBitmap(bm)

        //#3. 추론작업 시작..---동기식 방식
        val outputs: Model.Outputs = model.process(image)

        //#4. 분류한 라벨명이 5개 이므로... 가능성도 5개가 출력됨..
        binding.tv.text=""
        for( category in outputs.probabilityAsCategoryList ){
            binding.tv.append(" ${category.label} : ${category.displayName} - ${category.score} \n")
        }


    }
}