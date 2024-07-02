package com.hyun.tf02foodimageclassification

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
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.hyun.tf02foodimageclassification.databinding.ActivityMainBinding
import com.hyun.tf02foodimageclassification.ml.ModelFood
import org.tensorflow.lite.Tensor
import org.tensorflow.lite.support.image.TensorImage
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btn.setOnClickListener { clickBtn() }

        loadCalorieData()
    }

    //음식별 칼로리 정보를 가지고 있는 Map 객체 준비
    val foodCalorieMap: MutableMap<String, FoodCalorie> = mutableMapOf()
    var totalCalorie:Int = 0

    private fun loadCalorieData(){

        // assets폴더의 .csv를 읽어서 식별자와 칼로릴 정보들 매핑..
        val inputStream= assets.open("KoreanFoodCalorieUTF8.csv")
        val inputStreamReader = InputStreamReader(inputStream)
        val reader = BufferedReader(inputStreamReader)

        // 첫번째 줄을 컬룸 제목이므로..데이터가 아님.. 그냥 읽어서 소진..
        reader.readLine()

        val builder= StringBuilder()
        while (true){
            val line:String = reader.readLine() ?: break
            builder.append(line+"\n")

            // 한 줄 단위에 , 구분자로 구분하여 Map에 저장
            val data: List<String> =  line.split(",")
            // 첫번째 데이터는 [식별자], 두번째,세번째는 FoodCalorie 객체로..
            foodCalorieMap[data[0]] = FoodCalorie(data[1], data[2].toInt())
        }

        binding.tv.text= "칼로리 정보 개수 : ${foodCalorieMap.size} \n\n"
        binding.tv.append(builder.toString())
    }


    private fun clickBtn(){

        //#1. ML kit Object Detection ... [최대 5개 인식 가능]

        //1) 입력이미지 준비
        val bm= (binding.iv.drawable as BitmapDrawable).bitmap
        val image: InputImage = InputImage.fromBitmap(bm, 0)

        //2) ml kit object detector instance
        val options= ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()
            .build()
        val objectDetector= ObjectDetection.getClient(options)

        //3) image process....
        objectDetector.process(image).addOnSuccessListener {
            binding.tv.text= "인식된 음식의 개수 : ${it.size} \n\n"

            //이미지 위에 사각형 영역을 그리기!!!
            val bitmap= bm.copy(bm.config, true)
            val canvas= Canvas(bitmap)
            val paint= Paint().apply {
                color= Color.RED
                style= Paint.Style.STROKE
                strokeWidth= 4f
            }

            for( detectedObject in it ){
                //1. 인식한 음식 이미지 영역 그리기
                val bounds:Rect = detectedObject.boundingBox
                canvas.drawRect(bounds, paint)

                //2. 영역만큼의 작은 Bitmap을 잘라서 그 영역에 대한 TFLite 이미지 분류 모델로 분석하기
                val b:Bitmap = Bitmap.createBitmap(bm, bounds.left, bounds.top, bounds.width(), bounds.height())
                drawImageLabel(b, bounds, canvas, paint)

            }//for..

            binding.iv.setImageBitmap(bitmap)

            val calorie:String= DecimalFormat("#,###,###").format(totalCalorie)
            binding.tvCalorie.text= calorie

        }

    }//clickBtn() .....

    private fun drawImageLabel(b:Bitmap, bounds:Rect, canvas: Canvas, paint: Paint){

        // 전달받은 조각그림(b)을 이미지 분류하기..

        //1. 모델 만들기
        val modelFood: ModelFood = ModelFood.newInstance(this)
        //2. 입력이미지 준비
        val image: TensorImage = TensorImage.fromBitmap(b)
        //3. 이미지 처리..후 결과 받기
        val outputs: ModelFood.Outputs = modelFood.process(image)

        // 여러 가능성 중 가장 확률이 높은 식별요소 얻어오기
        val category= outputs.probabilityAsCategoryList.maxByOrNull { it.score }

        // 식별된 라벨을 출력하기..
        val food:FoodCalorie?= foodCalorieMap[category!!.label]
        binding.tv.append("${food!!.name} : ${food!!.calorie}kcal\n")

        // canvas에 라벨글씨 그리기..
        paint.textSize=24f
        canvas.drawText("${category?.label ?: "?"}", bounds.left.toFloat(), bounds.top.toFloat() +24f, paint)

        // 총 칼로리 정보 누적하기..
        totalCalorie += food.calorie

    }


}