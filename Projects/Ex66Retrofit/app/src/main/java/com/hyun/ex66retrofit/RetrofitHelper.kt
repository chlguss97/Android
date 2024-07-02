package com.hyun.ex66retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    // 동반 객체 - 자바의 static 을 대신하는 영역
    companion object{
        fun getRetrofitInstance() : Retrofit {
            val builder: Retrofit.Builder = Retrofit.Builder()
            builder.baseUrl("http://qwer2024.dothome.co.kr")
            builder.addConverterFactory(GsonConverterFactory.create())
            val retrofit: Retrofit = builder.build()

            return retrofit
        }
    }
}