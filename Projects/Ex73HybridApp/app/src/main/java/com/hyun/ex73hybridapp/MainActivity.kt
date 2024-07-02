package com.hyun.ex73hybridapp

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hyun.ex73hybridapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //웹뷰 설정
        binding.wv.settings.javaScriptEnabled= true
        binding.wv.settings.allowFileAccess= true // file:// 에서도 ajax 기술 허용
        binding.wv.settings.builtInZoomControls= true
        binding.wv.settings.displayZoomControls= false
        binding.wv.settings.domStorageEnabled= true

        binding.wv.webViewClient= WebViewClient()
        binding.wv.webChromeClient= WebChromeClient()

        //웹뷰가 보여줄 웹페이지를 로딩하기
        binding.wv.loadUrl("file:///android_asset/index.html")

        //1) native app 에서 web js를 제어하기
        binding.btn.setOnClickListener {
            //웹뷰에 보낼 메세지
            var message:String = binding.inputLayout.editText!!.text.toString()
            binding.wv.loadUrl("javascript:aaa('$message')")
        }






    }
}