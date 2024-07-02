package com.hyun.ex62webservice

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hyun.ex62webservice.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener { clickBtn1() }
        binding.btn2.setOnClickListener { clickBtn2() }




    }

    private fun clickBtn1(){
        //서버에서 제공하는 텍스트 파일 읽어오기 - 인터넷 퍼미션 필요
        // 네트워크 작업은 반드시 별도의 Thread 가 해야 함
        val t: Thread = object :Thread(){
            override fun run() {
                // 텍스트파일의 경로 url
                val texturl:String= "http://qwer2024.dothome.co.kr/index.html"

                //서버와 연결하는 무지개로드를 열기 위해.. 해임달..
                val url:URL= URL(texturl)
                // 무지개로드 열기
                val inputStream: InputStream = url.openStream() //byte 스트림
                val inputStreamReader: InputStreamReader = InputStreamReader(inputStream) // 문자 스트림
                val reader: BufferedReader = BufferedReader(inputStreamReader)// 보조 문자 스트림

                val buffer: StringBuffer = StringBuffer()
                while (true){
                    var line:String= reader.readLine() ?: break
                    buffer.append(line+"\n")
                }// while..

                // 스레드는 UI를 직접 제어하지 못하여.. UI Thread 에서 동작하도록..
                runOnUiThread(object : Runnable{
                    override fun run() {
                        binding.tv.text= buffer.toString()
                    }

                })

            }
        }
        t.start() // 자동으로 run()메소드 영역이 발동됨.

    }


    private fun clickBtn2(){
        //서버에서 제공하는 이미지 파일을 읽어오기

        //코틀린 스타일 스레드 코딩 - 스레드가 해야할 영역만 작성함.. 자동 start()
        thread {
            val imgUrl= "http://qwer2024.dothome.co.kr/imgs/paris.jpg"

            val url= URL(imgUrl)
            val inputStream= url.openStream() // byte stream

            // android 에서 이미지를 가지는 객체 Bitmap
            val bm:Bitmap = BitmapFactory.decodeStream(inputStream) // 스트림을 통해 이미지 파일을 읽어서 Bitmap 객체를 생성해줌
            // 이미지뷰에 보여주기 - 화면작업은 ui thread
            runOnUiThread { binding.iv.setImageBitmap(bm) }
        }

        // 위 스레드 작업을 이미지로드 라이브러리를 이용해면 한줄로 끝.. !! [Glide, Picasso]
        //Glide.with(this).load("http://qwer2024.dothome.co.kr/imgs/paris.jpg").into(binding.iv)

    }

}