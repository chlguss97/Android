package com.hyun.ex47datastorageinternal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {

    lateinit var inputLayout:TextInputLayout
    lateinit var tv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputLayout= findViewById(R.id.input_layout)
        tv= findViewById(R.id.tv)

        findViewById<Button>(R.id.btn_save).setOnClickListener { clickSave() }
        findViewById<Button>(R.id.btn_load).setOnClickListener { clickLoad() }
    }

    private fun clickSave(){
        // 저장할 데이터
        var data:String = inputLayout.editText!!.text.toString()
        // 다음 입력작업을 편하기 하기 위해 그 전 글씨를 제거
        inputLayout.editText!!.setText("")

        // data가 저장될 내부저장소의 File에 데이터를 저장하는 무지개로드 열기
        // 액티비티 클래스는 이미 내부저장소에 File을 저장할 수 있도록 Stream을 열어주는 기능메소드가 존재함
        val fos:FileOutputStream = openFileOutput("Data.txt", MODE_APPEND)
        // fos는 바이트 스트림이어서 문자열을 바이트배열로 변환해야 해서 번거로움..
        // 그래서 문자스트림(Writer)으로 변환. 더 나아가 보조문자스트림으로..변환하면 쓰기작업이 더 수월함
        val writer: PrintWriter = PrintWriter(fos)

        writer.println(data)
        writer.flush()
        writer.close()

        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
    }

    private fun clickLoad(){

        // File이 저장된 내부저장소까지 무지개로드 열기
        val fis:FileInputStream = openFileInput("Data.txt")
        // byte --> 문자 스트림
        val isr:InputStreamReader = InputStreamReader(fis)
        // 한줄단위로 읽어주는 보조문자스트림으로 변환
        val reader:BufferedReader = BufferedReader(isr)

        val buffer:StringBuffer = StringBuffer()
        while(true){
//            val line:String= reader.readLine() //한줄 읽어오기 - 줄바꿈 문자는 제외됨
//            if(line==null) break
//            buffer.append(line+"\n")
            //코틀린 스럽게
            val line:String= reader.readLine() ?: break  // ?:앨비스 연산자
            buffer.append(line+"\n")
        }

        tv.text= buffer.toString()

    }

}