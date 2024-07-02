package com.hyun.ex48datastorageexternal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {

    lateinit var inputLayout: TextInputLayout
    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputLayout = findViewById(R.id.input_layout)
        tv = findViewById(R.id.tv)

        findViewById<Button>(R.id.btn_save).setOnClickListener { clickSave() }
        findViewById<Button>(R.id.btn_load).setOnClickListener { clickload() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickSave2() }

    }

    private fun clickSave() {
        //phone 저장공간
        // 내장 저장공간: 내부 저장소(internal), 외부 저장소(external : 앱 고유영역, 공용 영역)
        // 외장 저장공간: SDcard, USB c-type,... [퍼미션 필요] - 장착이 불가한 phone도 있움

        // 이중.. 외부저장소의 앱 고유영역에 데이터를 파일로 저장하기

        // 저장할 데이터
        var data: String = inputLayout.editText!!.text.toString()
        inputLayout.editText!!.setText("")

        // 엑티비티에 파일과 연결되는 스트림을 열어주는 기능이 존재하지 않음.
        // 그래서 File 경로부터 알아야 함. 액티비티클래스가 그 경로를 알려줌
        val path: File? = getExternalFilesDir("MyDir") // "MyDir" - 파일이 저장될 폴더명
        path?.apply {
            tv.text = this.path //경로 확인해보기

            //경로 + 파일명.확장자
            val file: File = File(this, "Data.txt")

            //파일과 연결하여 데이터를 저장하는 무지개로드 .. 생성 . 단, 문자스트림으로..
            val fw: FileWriter = FileWriter(file, true)
            //저장작업을 편하게 하기 위해 보조문자스트림으로 변환
            val writer: PrintWriter = PrintWriter(fw)

            //쓰기작업
            writer.println(data)
            writer.flush()
            writer.close()

            Toast.makeText(this@MainActivity, "save", Toast.LENGTH_SHORT).show()
        }

    }


    private fun clickload() {

        // "Data.txt" 파일이 저장되어 있는 외부저장소의 앱고유영역 경로.. 얻어오기
        val path: File? = getExternalFilesDir("MyDir")
        path?.apply {
            val file: File = File(this, "Data.txt")// 경로 + 화일명.확장자

            // 위 file까지 연결하여 데이터를 일어오는 무지개로드.. 단, 문자스트림으로..
            val fr: FileReader = FileReader(file)
            val reader: BufferedReader = BufferedReader(fr)// 보조문자스트림

            val buffer: StringBuffer = StringBuffer()
            while (true) {
                val line: String = reader.readLine() ?: break
                buffer.append(line + "\n")
            }
            tv.text = buffer.toString()
            reader.close()

        }

    }

    private fun clickSave2(){

        // 내장 저장공간의 외부 저장소 중.. 공용영역 - 앱을 삭제해도 파일을 남아 있는 영역

        //경로 얻어오기
        val path:File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        tv.text= path.path //경로 확인해보기

        //경로 + 파일명.확장자
        val file:File= File(path,"aaa.txt")

        // 무지개로드
        val fw:FileWriter = FileWriter(file, true)
        val writer:PrintWriter= PrintWriter(fw)

        writer.println(inputLayout.editText!!.text.toString())
        writer.flush()
        writer.close()

        Snackbar.make(tv,"공용 영역에 파일 저장 완료", Snackbar.LENGTH_LONG  ).show()
    }
}