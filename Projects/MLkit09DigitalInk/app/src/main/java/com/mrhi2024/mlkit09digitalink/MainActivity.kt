package com.mrhi2024.mlkit09digitalink

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.vision.digitalink.DigitalInkRecognition
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModel
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModelIdentifier
import com.google.mlkit.vision.digitalink.DigitalInkRecognizerOptions
import com.google.mlkit.vision.digitalink.Ink
import com.mrhi2024.mlkit09digitalink.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnClear.setOnClickListener { binding.myDrawingView.clearView() }
        binding.btnRecognition.setOnClickListener { clickRecognition() }
    }

    private fun clickRecognition(){
        //Digital Ink Recognizer 객체를 가져와서...MyDrawingView에 써있는 손글씨 데이터를 읽어오기..

        //1. Digital Ink Recognizer객체를 생성하는 3단계
        // 언어별 모델 식별자 준비
        val modelIndentifier: DigitalInkRecognitionModelIdentifier?= DigitalInkRecognitionModelIdentifier.fromLanguageTag("ko") //가이드 문서에 있는 나라 코드

        // 식별한 언어 인식기의 모델 준비
        var model: DigitalInkRecognitionModel= DigitalInkRecognitionModel.builder(modelIndentifier!!).build()

        // 원격 서버에서 모델 다운로드 하기..
        val remoteModelManager = RemoteModelManager.getInstance()
        remoteModelManager.isModelDownloaded(model).addOnSuccessListener {
            binding.tv.text= "모델 다운로드 상태 : $it \n\n"

            if(it){

                //1. digital ink recognizer 생성
                val recognizer= DigitalInkRecognition.getClient(DigitalInkRecognizerOptions.builder(model).build())

                //2. 인식할 Digital Ink 준비..- MyDrawingView에서 그려진 좌표값을 저장한 Ink 객체를 가져와야 함.
                val ink:Ink = binding.myDrawingView.getInk()

                //3. 손글씨 인식처리
                recognizer.recognize(ink).addOnSuccessListener {
                    binding.tv.append("인식된 글씨 후보 개수 : ${it.candidates.size}")

                    for( candidate in it.candidates ){
                        binding.tv.append("${candidate.text} : ${candidate.score} \n") //인식글씨, 확률점수(지원되는 언어가 몇개 없음-- 지원되지 않는 언어는 null)

                    }
                    //확률이 가장 높은 인식글씨...
                    val text:String = it.candidates[0].text
                }


            }else{
                binding.tv.append("손글씨 인식모델 다운로드 중...\n")

                remoteModelManager.download(model, DownloadConditions.Builder().build()).addOnSuccessListener {
                    binding.tv.append("한국어 인식모델 다운로드 완료!!")
                }

            }

        }

    }
}







