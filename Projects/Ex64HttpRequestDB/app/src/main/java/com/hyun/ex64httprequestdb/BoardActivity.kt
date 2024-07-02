package com.hyun.ex64httprequestdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hyun.ex64httprequestdb.databinding.ActivityBoardBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class BoardActivity : AppCompatActivity() {

    private val binding: ActivityBoardBinding by lazy { ActivityBoardBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //리사이클러뷰가 보여줄 데이터를 서버에서 읽어오기
        loadData()

        // 화면의 데이터를 갱신하는 swipe 동작에 반응하는 리스너 적용
        binding.refreshLayout.setOnRefreshListener {
            //갠신한다는 것은 서버의 데이터를 다시 불러온다는 것임
            loadData()

        }
    }

    // 대량의 데이터 리스트
    var itemList: MutableList<Item> = mutableListOf()

    private fun loadData(){
        //서버에서 데이터 가져오기

        itemList.clear()

        thread {
            val serverUrl= "http://qwer2024.dothome.co.kr/03Android/loadDB.php"

            // 데이터만 읽어오면 되므로 InputStream 만 있으면 됨. 단, 연습을 위해 Connection 사용해보기
            val url= URL(serverUrl)
            val connection= url.openConnection() as HttpURLConnection
            connection.requestMethod= "GET"
            connection.doInput= true
            connection.useCaches= false

            val inputStream= connection.inputStream  // byte stream
            val inputStreamReader= InputStreamReader(inputStream) // character stream
            val reader= BufferedReader(inputStreamReader) // buffering character stream

            val builder= StringBuilder()
            while(true){
                val line= reader.readLine() ?: break
                builder.append(line+"\n")
            }

            // 우선 잘 읽어왔는지 확인
            //runOnUiThread { AlertDialog.Builder(this).setMessage(builder.toString()).create().show() }

            // 서버에서 echo된 문자열 데이터에서 '&'문자를 기준으로 문자열들을 분리
            val rows: List<String> = builder.toString().split("&")

            for( row in rows ){
                //한줄 데이터안에서 각 컬룸(칸)값들을 분리
                val cols: List<String> = row.split(",")
                if(cols.size!=4) continue //4칸이 아니면..

                var no:Int= cols[0].toInt()
                var name:String= cols[1]
                var msg:String= cols[2]
                var date:String= cols[3]

                itemList.add(  Item(no, name, msg, date)  )
            }//for...

            // 읽어온 데이터 리스트를 리사이클러뷰에 보여주기 위해 아답터 생성 및 설정
            runOnUiThread { binding.recyclerView.adapter= BoardAdapter(this,itemList)
                // refresh icon이 사라지도록..
                binding.refreshLayout.isRefreshing= false
            }

        }//thread..
    }
}








