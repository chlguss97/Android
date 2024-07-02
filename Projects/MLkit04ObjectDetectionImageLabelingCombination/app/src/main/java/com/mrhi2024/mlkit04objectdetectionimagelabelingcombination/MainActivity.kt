package com.mrhi2024.mlkit04objectdetectionimagelabelingcombination

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
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.mrhi2024.mlkit04objectdetectionimagelabelingcombination.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){

        //[1단계] Object Detection..

        //#1. 입력 이미지 준비
        val bm:Bitmap = (binding.iv.drawable as BitmapDrawable).bitmap
        val image:InputImage = InputImage.fromBitmap(bm, 0)

        //#2. Object Detector 준비
        val options= ObjectDetectorOptions.Builder().setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE).enableMultipleObjects().build()
        val objectDetector = ObjectDetection.getClient(options)

        //#3. 이미지 처리
        objectDetector.process(image).addOnSuccessListener {
            binding.tv.text= "검출된 개체들의 수 : ${it.size}\n\n"

            // 검출된 object의 사각형 영역그리기
            val bitmap= bm.copy(bm.config, true)
            val canvas= Canvas(bitmap)
            var paint= Paint().apply {
                style= Paint.Style.STROKE
                strokeWidth= 8.0f
                textSize= 40.0f
            }
            val colors= arrayOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN)

            var n=0
            for( obj in it ){
                val bounds= obj.boundingBox //영역 얻어오기

                //1) 영역에 사각형 그리기
                paint.color= colors[n]
                canvas.drawRect(bounds, paint)

                //2) 영역에 번호 표시하기
                canvas.drawText(n.toString(), bounds.left.toFloat(), bounds.top+40f, paint)

                //3) 감지된 객체들별로 이미지 라벨링 하기..
                //객체의 영역에 해당하는 크기로 작은 이미지조각을 만들기
                val b:Bitmap= Bitmap.createBitmap(bm, bounds.left, bounds.top, bounds.width(), bounds.height())
                drawImageLabel(n, b, bounds, canvas, paint)

                n++
            }//for...

            binding.iv.setImageBitmap(bitmap)
        }
    }

    private fun drawImageLabel(n:Int, b:Bitmap, bounds:Rect, canvas: Canvas, paint: Paint){

        // [2단계] 조각 이미지를 분석하여 라벨링 하기..

        //1. 입력이미지 준비
        val image: InputImage = InputImage.fromBitmap(b, 0)

        //2. 이미지 라벨러 준비
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        //3. 이미지 처리
        labeler.process(image).addOnSuccessListener {

            // ImageLabel 중에서 가능 confidence(신뢰도)가 높음 요소를 찾아오는 기능..함수
            val label:ImageLabel? = it.maxByOrNull { it.confidence }

            // 객체번호와 라벨링 값을 TextView 에 보여주기
            binding.tv.append("$n : ${label?.text} \n")

            // 사진의 영역에 라벨명을 번호와 함께 그려주기..
            val colors= arrayOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN)
            paint.color= colors[n]
            canvas.drawText("$n - ${label?.text}", bounds.left.toFloat(), bounds.top.toFloat()+40f, paint)
        }

    }

}













