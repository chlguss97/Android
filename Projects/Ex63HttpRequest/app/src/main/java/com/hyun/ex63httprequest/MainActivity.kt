package com.hyun.ex63httprequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyun.ex63httprequest.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(binding.root)

        binding.btnGet.setOnClickListener { clickGet() }
        binding.btnPost.setOnClickListener { clickPost() }
    }


    private fun clickGet(){
        //네크워크 잡업은 별도의 스레드 - 코틀린 스타일로
        thread {
            //서버로 보낼 데이터
            var title: String = binding.inputLayoutTitle.editText!!.text.toString()
            var message: String = binding.inputLayoutMsg.editText!!.text.toString()

            // GET 방식으로 보낼 서버의 주서[URL]
            val serverUrl:String = "http://qwer2024.dothome.co.kr/03Android/aaa.php"

            // GET 방식은 보낼데이터를 URL에 붙여서 보내는 방식
            // URL에는 한글 및 특수문자 사용 불가 - 한글을 URL에 사용할 수 있도록 암호화[인코딩]
            title= URLEncoder.encode(title, "utf-8")
            message= URLEncoder.encode(message, "utf-8")



            //GET 방식은 URL 뒤에 ?르 붙이고 요청파라미터 값(title, message)을 전송
            val getURL: String="$serverUrl?title=$title&msg=$message"

            //서버와 연결
            val url: URL = URL(getURL)
            // GET방식은 이미 서버주소에 보낼 데이터가 붙어서 전송되기에 별도의 전송작업코드는 필요없으
            //즉, OutputStream이 필요하지 않음

            //서버(aaa.php)에서 echo된 글씨를 읽어오기 위해 InputStream 필요
            val inputStream:InputStream = url.openStream()// 바이트 스트림
            val inputStreamReader: InputStreamReader= InputStreamReader(inputStream)// 문자스트림
            val bufferedReader: BufferedReader= BufferedReader(inputStreamReader)// 보조스트림

            val buffer: StringBuffer= StringBuffer()
            while (true){
                val line: String= bufferedReader.readLine() ?:break
                buffer.append(line+"\n")
            }
            runOnUiThread { binding.tv.text= buffer.toString() }

        }

    }

    private fun clickPost(){

        thread {
            // 서버로 보낼 데이터
            var title = binding.inputLayoutTitle.editText!!.text.toString()
            var message= binding.inputLayoutMsg.editText!!.text.toString()

            // POST방식으로 데이터를 보낼 서버 주소
            val serverUrl= "http://qwer2024.dothome.co.kr/03Android/bbb.php"

            // 서버와 연결작업
            val url= URL(serverUrl)
            // URL은 InputStream만 열 수 있음

            //HTTP 통신 규약에 따라 데이터를 주고 받는 역할을 수행하는 URL의 조수같은 객체가 있음
            val connection: HttpURLConnection= url.openConnection() as HttpURLConnection
            connection.requestMethod= "POST" //반드시 대문자
            connection.doOutput = true
            connection.doInput  = true
            connection.useCaches= false

            // 데이터를 보내기 위헤 OutputStream
            val outputStream: OutputStream =  connection.outputStream // 바이트 스트림
            val writer: OutputStreamWriter= OutputStreamWriter(outputStream) //문자 스트림

            // 보낼 데이터를 POST 방식으로 쓰기 위해 [key=value]규칙에 맞게 하나의 문자열로 결합
            val data:String = "title=$title&msg=$message"

            // 보낼 데이터를 OutStream을 통해 내보내기
            writer.write(data,0,data.length)
            writer.flush()
            writer.close()
            //---------------------------------------------


            // 서버(bbb.php)에서 응답(echo) 시킨 문자열 읽어오기
            val inputStream = connection.inputStream // byte stream
            val inputStreamReader= InputStreamReader(inputStream) // character stream
            val reader= BufferedReader(inputStreamReader) // buffering character stream

            val buffer = StringBuffer()
            while (true){
                val line= reader.readLine() ?:break
                buffer.append(line+"\n000")
            }


            runOnUiThread { binding.tv.text= buffer.toString() }



        }


    }
}