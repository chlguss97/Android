package com.hyun.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hyun.texttospeech.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btn.setOnClickListener { clickBtn() }

        // TextToSpeech 객체 생성 및 초기화 -- 초기화 후 언어 설정
        tts=  TextToSpeech(this, object : OnInitListener{
            override fun onInit(status: Int) {
                if(status != TextToSpeech.ERROR) tts.language = Locale.KOREAN
            }
        })

        binding.sliderPitch.addOnChangeListener { slider, fl, b -> pitch= fl }
        binding.sliderRate.addOnChangeListener { slider, fl, b -> rate= fl }

    }

    // TextToSpeech 객체 - 글씨를 음성으로 출력하는 객체참조변수
    lateinit var tts: TextToSpeech

    var pitch:Float= 1.0f
    var rate:Float= 1.0f

    private fun clickBtn(){

        // 음성으로 재생항 글씨
        val text:String= binding.inputLayout.editText!!.text.toString()

        tts.setPitch(pitch) //음성 톤 설정
        tts.setSpeechRate(rate) //재생 속도 설정

        // 음성으로 말하기!!
        val utteranceId:String = hashCode().toString()+"0"
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

}