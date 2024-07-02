package com.hyun.mlkit05textrecognition

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import com.hyun.mlkit05textrecognition.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btn.setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){

        // #1. 입력 이미지 준비
        val bm:Bitmap = (binding.iv.drawable as BitmapDrawable).bitmap
        val image: InputImage = InputImage.fromBitmap(bm, 0)

        // #2. 텍스트 인식기 객체 만들기
        val recognizer: TextRecognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build()) //한국어 인식기로 생성

        // #3. 이미지 처리
        recognizer.process(image).addOnSuccessListener {

            // 인식된 글씨에는 TextBlock(단락), Line(한줄), Element(단어), Symbol(음절) 단위로 항목을 구분할 수 있음.

            //1) 인식된 전체 글씨 출력해보기
            binding.tv.text= it.text

            //2) 단락 단위로 출력해보기
            val bitmap:Bitmap = bm.copy(bm.config, true)
            val canvas= Canvas(bitmap)
            val paint= Paint().apply {
                style= Paint.Style.STROKE
                strokeWidth= 6.0f
            }

            binding.tv.text= "Block 개수 : ${it.textBlocks.size}\n\n"
            for( block in it.textBlocks ){
                binding.tv.append("${block.text}\n")
                // 블럭영역에 사각형 그리기
                paint.color= Color.RED
                block.boundingBox?.apply { canvas.drawRect(this, paint) }

                // Line 단위로 구분하여 출력해보기
                binding.tv.append("라인 개수 : ${block.lines.size}\n")
                block.lines.forEach { line ->
                    binding.tv.append("${line.text} \n")

                    paint.color= Color.BLUE
                    paint.strokeWidth= 3.0f
                    line.boundingBox?.apply { canvas.drawRect(this, paint) }

                    // 단어 단위로 구분하여 출력해보기
                    binding.tv.append("라인별 단어 개수 : ${line.elements.size}\n")
                    line.elements.forEach { element ->
                        binding.tv.append("${element.text}, ")

                        paint.color= Color.MAGENTA
                        paint.strokeWidth= 2.0f
                        element.boundingBox?.apply { canvas.drawRect(this, paint) }
                    }
                    binding.tv.append("\n")
                }

                binding.tv.append("-------------\n")
            }

            binding.iv.setImageBitmap(bitmap)

        }

    }
}