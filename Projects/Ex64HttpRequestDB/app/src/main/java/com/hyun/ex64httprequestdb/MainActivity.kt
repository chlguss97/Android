package com.hyun.ex64httprequestdb

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.hyun.ex64httprequestdb.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    private  val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener { clickSave() }
        binding.btnLoad.setOnClickListener { clickLoad() }
    }

    private fun clickSave(){
        // 소프트 키보드 닫기
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0 )

        thread {
            //서버에 보낼 데이터
            var name= binding.inputLayoutName.editText!!.text.toString()
            var message= binding.inputLayoutMsg.editText!!.text.toString()

            // save data를 수행하는 백엔드 프로그램 url
            val severUrl="http://qwer2024.dothome.co.kr/03Android/insertDB.php"

            //POST 방식으로 데이터 전송
            val url= URL(severUrl)
            val connection= url.openConnection() as HttpURLConnection
            connection.requestMethod= "POST"
            connection.doInput = true
            connection.doOutput = true
            connection.useCaches = false

            // 보낼데이터 formatting [key=value]
            val data= "name=$name&msg=$message"

            val os= connection.outputStream // byte stream
            val writer = OutputStreamWriter(os) // character
            writer.write(data,0,data.length)
            writer.flush()
            writer.close()
            //---------------------------------------------------

            // 서버(insertDB.php)로 부터 DB 저장결과 응답(echo) 받아 SnackBar에 보여주기
            val inputStream = connection.inputStream // byte stream
            val inputStreamReader= InputStreamReader(inputStream) //charactor stream
            val reader = BufferedReader(inputStreamReader) // buffering character stream

            val builder: StringBuilder = StringBuilder()
            while (true){
                val line = reader.readLine() ?:break
                builder.append(line+"\n")

                //스낵바는 화면에 보여지는 작업이기에 ui thread 에서 작업
                runOnUiThread { Snackbar.make(binding.root, builder.toString(), Snackbar.LENGTH_SHORT).show() }
            }

        }

    }

    private fun clickLoad(){
        val intent= Intent(this,BoardActivity::class.java)
        startActivity(intent)

    }

}