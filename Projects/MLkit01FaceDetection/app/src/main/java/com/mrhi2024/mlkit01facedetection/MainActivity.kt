package com.mrhi2024.mlkit01facedetection

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.mrhi2024.mlkit01facedetection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        setContentView(binding.root)
        binding.btn.setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){

        //#1. 얼굴 인식기를 생성하기 위한 옵션준비
        val options: FaceDetectorOptions = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE) //정확도 위주로
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL) //눈, 귀, 코, 뺨, 입술 같은 얼굴 특징을 식별하지 여부
            .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL) //얼굴 특징의 윤괄을 감지할지 여부
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL) //얼굴이 "웃고 있음' 또는 "눈을 뜨고 있음" 같은 카테고리로 분류..해줄 지 여부
            .build()

        //#2. 위 옵션으로 얼굴 인식기 객체 생성
        val faceDetector: FaceDetector = FaceDetection.getClient(options)

        //#3. 입력 이미지 준비
        val bm:Bitmap = (binding.iv.drawable as BitmapDrawable).bitmap
        val image:InputImage = InputImage.fromBitmap(bm, 0)

        //#4. 이미지 처리
        faceDetector.process(image).addOnSuccessListener {
            binding.tv.text="감지된 얼굴 개수 : ${it.size} \n\n"

            for( face in it ){
                //감지된 얼굴의 영역 좌표 얻어오기 (사각형 영역)
                val bounds: Rect = face.boundingBox
                binding.tv.append("[${bounds.left}, ${bounds.top}, ${bounds.width()}, ${bounds.height()}]")

                //옵션에 따라.. 웃고 있는지 여부..알수 있음.
                if(face.smilingProbability !=null ){
                    binding.tv.append("smile : ${face.smilingProbability}")
                }

                binding.tv.append("\n----------------------------------------\n")
            }

            //사진에 얼굴영역의 사각형 그리기....
            //기존 Bitmap 이미지를 복제하기
            val bitmap:Bitmap = bm.copy(bm.config, true)
            //복제본에 그림을 그리기 위해 도화지 준비..
            val canvas:Canvas = Canvas(bitmap)
            //그림도구 Paint 준비
            val paint:Paint = Paint()
            paint.color= Color.RED
            paint.style= Paint.Style.STROKE
            paint.strokeWidth= 8.0f

            for(face in it ){
                val bounds:Rect= face.boundingBox
                canvas.drawRect(bounds, paint)

                //혹시 웃고 있으면.. smile. 이라는 글씨를 표시
                paint.textSize= 80f
                face.smilingProbability?.also {
                    if(it>0.8) canvas.drawText("smile", bounds.left.toFloat(), bounds.top.toFloat(), paint)
                }

            }

            binding.iv.setImageBitmap(bitmap)


        }

    }

}