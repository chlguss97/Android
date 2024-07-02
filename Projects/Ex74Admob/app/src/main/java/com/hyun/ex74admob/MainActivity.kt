package com.hyun.ex74admob

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //admob sdk 초기화
        thread {
            MobileAds.initialize(this){
                Toast.makeText(this, "애드몹 광고 초기화 완료", Toast.LENGTH_SHORT).show()
            }
        }

        //admob 광고 형식
        //1. 배너광고
        val adView: AdView = AdView(this)
        adView.setAdSize(AdSize.BANNER)
        adView.adUnitId="ca-app-pub-3940256099942544/9214589741"
        //admob 웹 사이트에 등록한 앱에서 발급한 배너광고단위 id값을 설정

        // 실제 광고를 불러오는 요청객체
        val adRequest: AdRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest) //광고 요청..


        //레이아웃에 AdView 추가하기
        findViewById<FrameLayout>(R.id.adview_container).addView(adView)

        }

}