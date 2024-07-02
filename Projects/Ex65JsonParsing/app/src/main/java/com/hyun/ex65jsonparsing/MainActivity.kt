package com.hyun.ex65jsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.hyun.ex65jsonparsing.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn1.setOnClickListener { clickBtn1() }
        binding.btn2.setOnClickListener { clickBtn2() }
        binding.btn3.setOnClickListener { clickBtn3() }
        binding.btn4.setOnClickListener { clickBtn4() }
        binding.btn5.setOnClickListener { clickBtn5() }
        binding.btn6.setOnClickListener { clickBtn6() }
    }

    private fun clickBtn6(){
        //kotlin object --> json string
        var person:Person= Person("kim",30,180.3, Address("korea","busan"))


        val jsonString:String= Gson().toJson(person)
        binding.tv.text= jsonString
    }




    private fun clickBtn5(){
        //스트림작업을 조금 줄여서..
        val inputStream= assets.open("aaa.json")
        val inputStreamReader= InputStreamReader(inputStream)

        // Gson 은 문자스트림을 통해 읽어와서 분석까지 완료..
        val gson= Gson()
        val person:Person= gson.fromJson(inputStreamReader, Person::class.java)
        binding.tv.text= "${person.name} , ${person.age}"
    }


    private fun clickBtn4(){
        //bbb.json 읽어오기
        val inputStream= assets.open("bbb.json")
        val inputStreamReader= InputStreamReader(inputStream)
        val reader= BufferedReader(inputStreamReader)

        val builder= StringBuilder()
        while(true){
            val line = reader.readLine() ?: break
            builder.append(line+"\n")
        }
        val jsonString= builder.toString()

        val gson:Gson= Gson()
        val people: Array<Person> = gson.fromJson(jsonString, Array<Person>::class.java)

        binding.tv.text=""
        people.forEach {
            binding.tv.append("${it.name} - ${it.age} \n")
        }
        // -----------------------------------
        binding.tv.append("=============================== \n")


        //Gson으로 곧바로 리스트로 받으려면 TypeToken이라는 것을 써야해서 번거로움
        // 그래서.. 편하게.. Array로 받아서 List로 변환
        val people2: List<Person> = people.toList()
        for(p in people2){
            binding.tv.append("${p.name} - ${p.age} \n")
        }

    }



    private fun clickBtn3(){
        //Gson library를 이용한 json object parsing

        //aaa.json 읽어오기
        val inputStream= assets.open("aaa.json")
        val inputStreamReader= InputStreamReader(inputStream)
        val reader= BufferedReader(inputStreamReader)

        val builder= StringBuilder()
        while(true){
            val line = reader.readLine() ?: break
            builder.append(line+"\n")
        }
        val jsonString= builder.toString()

        // Gson 객체 생성
        val gson:Gson= Gson()
        var person:Person= gson.fromJson(jsonString, Person::class.java)
        binding.tv.text= "${person.name} , ${person.age} , ${person.height} , ${person.address.nation}, ${person.address.city}"
    }



    private fun clickBtn2(){
        //assets폴더에 json array로 작성된 bbb.json 문서를 열기
        val inputStream= assets.open("bbb.json")
        val inputStreamReader= InputStreamReader(inputStream)
        val reader= BufferedReader(inputStreamReader)

        val builder= StringBuilder()
        while (true){
            val line= reader.readLine() ?: break
            builder.append(line+"\n")
        }
        binding.tv.text= builder.toString()

        // json 문자열을 분석
        val json:JSONArray = JSONArray(builder.toString())

        // 배열이므로 여러개의 JSONObject가 존재하므로..반복문 처리
        for( i in 0 until json.length()){
            val jo: JSONObject= json.getJSONObject(i)

            // 개별 데이터 얻기
            var name= jo.getString("name")
            var age= jo.getInt("age")
            var height= jo.getDouble("height")

            var address: JSONObject = jo.getJSONObject("address")
            var nation= address.getString("nation")
            var city= address.getString("city")

            // 개별 데이터들을 하나의 클래스로 묶어서 관리..
            // JSONObject 단위로 class 를 설계하여 객체 리스트에 추가..
            val person:Person = Person(name, age, height, Address(nation, city) )
            people.add(person)
        }//for...

        binding.tv.text= people.toString()
        binding.tv.text=""
        people.forEach{
            binding.tv.append("${it.name} ${it.age} - ${it.height} > ${it.address.nation} ~ ${it.address.city}\n")

        }

    }

    // Person 객체의 리스트
    val people: MutableList<Person> = mutableListOf()

    private fun clickBtn1(){
        //assets폴더의 파일을 가져오기 위해 창고관리자(assets: AssetManager) 소환하여 assets/aaa.json 파일을 읽어오기위한 InputStream 열기
        val inputStream: InputStream= assets.open("aaa.json") //byte stream
        val inputStreamReader = InputStreamReader(inputStream) //character stream
        val reader= BufferedReader(inputStreamReader) //buffering character stream

        val builder= StringBuilder()
        while (true){
            val line= reader.readLine() ?: break
            builder.append(line+"\n")
        }

        // 읽어온 글씨를 문자열로 만들기
        val jsonString: String= builder.toString()

        // json 문자열 확인해보기
        binding.tv.text= jsonString

        // json 문자열을 분석하기 위해 JSONObject 생성
        val json: JSONObject= JSONObject(jsonString)

        // 개별데이터 얻기 - json의 key 식별자를 통해 자료형별로 값 얻기
        var name:String = json.getString("name")
        var age:Int = json.getInt("age")
        var height:Double = json.getDouble("height")

        var address:JSONObject = json.getJSONObject("address")
        var nation:String = address.getString("nation")
        var city:String = address.getString("city")

        // 데이터 출력
        binding.tv.text ="이름:$name   나이:$age   키:$height ~ 주소: $nation $city"
    }
}