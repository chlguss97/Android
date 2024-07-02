package com.mrhi2024.mlkit02objectdetection

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
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.mrhi2024.mlkit02objectdetection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){

        // #1. 입력이미지 준비
        val bm:Bitmap = (binding.iv.drawable as BitmapDrawable).bitmap
        val image: InputImage = InputImage.fromBitmap(bm, 0)

        // #2. 객체 감지기 준비
        val options: ObjectDetectorOptions = ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
            .enableMultipleObjects() //최대 5개 검출
            .enableClassification() //검출된 object에 대한 분류 (큰 범주로만 분류함)
            .build()
        val objectDetector: ObjectDetector = ObjectDetection.getClient(options)

        // #3. 이미지 처리
        objectDetector.process(image).addOnSuccessListener {
            binding.tv.text="인식된 object 개수 : ${it.size} \n\n"

            // 검출된 object들의 사각형 영역 그리기...
            val bitmap:Bitmap= bm.copy(bm.config, true)
            val canvas: Canvas = Canvas(bitmap)
            val paint = Paint()
            paint.style= Paint.Style.STROKE
            paint.strokeWidth= 8.0f
            paint.textSize= 40.0f
            val colors= arrayOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN)

            var n=0
            for( detectedObject in it ){
                val bounds: Rect = detectedObject.boundingBox
                paint.color= colors[n]
                canvas.drawRect(bounds, paint)
                canvas.drawText("$n", bounds.left.toFloat(), bounds.top.toFloat(), paint)

                // 검출된  object의 범주로 예측되는 글씨들..
                for( label in detectedObject.labels ){
                    binding.tv.append(" ${label.index} : ${label.text}  ~  ${label.confidence} \n")
                }
                binding.tv.append("-----------------------\n")
                n++
            }

            binding.iv.setImageBitmap(bitmap)
        }

    }
}