package com.hyun.tpsearchplacebykakao

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // 카카오 SDK 초기화
        KakaoSdk.init(this,"88b13351b8cfe2d17a847ccbc6a7ccc9")
    }

}