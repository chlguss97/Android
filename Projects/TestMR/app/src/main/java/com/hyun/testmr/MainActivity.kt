package com.hyun.testmr

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.hyun.testmr.databinding.ActivityMainBinding
import com.hyun.testmr.ml.Model
import org.tensorflow.lite.support.image.TensorImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.btn.setOnClickListener { clickBtn() }

    }

    private fun clickBtn() {
        // #1. 모델 객체 생성
        val model: Model = Model.newInstance(this)

        // #2. 입력 이미지 준비
        val bm = (binding.iv.drawable as BitmapDrawable).bitmap
        val image: TensorImage = TensorImage.fromBitmap(bm)

        // #3. 추론 작업 시작
        val outputs: Model.Outputs = model.process(image)

        // #4. 분류 결과 출력
        var maxProbability = -1.0f
        var predictedCategory = ""

        for (category in outputs.probabilityAsCategoryList) {
            if (category.score > maxProbability) {
                maxProbability = category.score
                predictedCategory = category.label

            }
        }

        // 가장 높은 확률을 갖는 카테고리 출력
        binding.tv.text = "음식명: $predictedCategory ~ 확률: ${maxProbability}) ~ "

        // 모델 해제
        model.close()
    }
}