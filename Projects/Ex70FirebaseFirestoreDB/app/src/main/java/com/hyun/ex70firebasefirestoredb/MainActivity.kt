package com.hyun.ex70firebasefirestoredb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hyun.ex70firebasefirestoredb.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // Firebase cloud Firestore database : No-SQL방식의 데이터베이스
    // Firebase와 연동

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener { clickSave() }
        binding.btnLoad.setOnClickListener { clickLoad() }
        binding.btnSearch.setOnClickListener { clickSearch() }
    }

    private fun clickSave(){

        // 저장할 데이터
        var name:String = binding.inputLayoutName.editText!!.text.toString()
        var age:Int = binding.inputLayoutAge.editText!!.text.toString().toInt()
        var address:String = binding.inputLayoutAddress.editText!!.text.toString()

        // 데이터들을 한방에 저장하기 위해..Map으로 묶어서 저장
        val data: MutableMap<String, Any> = mutableMapOf()
        data["name"]= name
        data["age"]= age
        data["address"]= address

        // 파이어스토어 제어 객체를 통해..데이터를 저장할 Collection 참조하기 [ MySQL에서 테이블명 같은 역할 ]
        val userRef: CollectionReference = Firebase.firestore.collection("user") // 'user'컬렉션이 없으면 만들고.. 있으면 참조..

        // 데이터 저장의 여러 방법
        //1) 자동으로 생성되는 랜덤한 이름의 Document가 만들어지고 그 안에 user 데이터를 저장하기
        //userRef.document().set(data).addOnSuccessListener {Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show() }

        //2) 위 document().set(data)를 줄여서...
        //userRef.add(data).addOnSuccessListener { Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show() }

        //3) Document를 순서대로 배치되게 하려면.. 명칭을 지정해야 함. .. 날짜를 이용하면 편함
        //var n= SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(Date())
        //userRef.document(n).set(data)

        //4) 데이터들은 보통은 data class로 만든 객체로 된 경우가 많음 User 클래스를 설계하여 한방에 객체로 저장
        val user:User = User(name, age, address)
        userRef.document().set(user).addOnSuccessListener { Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show() }

    }

    private fun clickLoad(){

        // "user" 라는 이름의 Collection 에 저장된 데이터들 읽어오기 위해 참조객체 얻어오기
        val userRef: CollectionReference= Firebase.firestore.collection("user")

        // 데이터를 얻어오는 작업 - 비동기 작업 [작업완료 리스너]
        userRef.get().addOnSuccessListener {
            //파라미터로 전달된 QuerySnapshot : 요청 순간의 데이터를 캡쳐한 스냅샷 객체
            //QuerySnapshot 안에 각 Document 별로 다시 DocumentSnapshot를 가지고 있음.
            //그래서 모든 값을 얻어오려면..반복문이 필요
            binding.tv.text=""
            for( snapshot:DocumentSnapshot in it ){
                // DocumentSnapshot 별로 필드값 받기
                val data: Map<String, Any> = snapshot.data!!

                var name:String= data["name"].toString()
                var age:Int= data["age"].toString().toInt()
                var address:String= data["address"].toString()

                binding.tv.append("$name : $age - $address \n")
            }//for...
        }
    }


    private fun clickSearch(){
        // "user"컬렉션에서 특정 필드 값에 해당하는 데이터 가져오기

        // 검색할 이름
        val name= binding.inputLayoutName.editText!!.text.toString()

        // "user"컬렉션 참조하는 객체
        val userRef: CollectionReference = Firebase.firestore.collection("user")

        // database 쿼리문의 where절 역할로 검색하는 메소드
        userRef.whereEqualTo("name", name).get().addOnSuccessListener {
            // 같은 이름의 데이터가 여러개 일 수도 있으니..
            binding.tv.text=""
            for( snapshot in it ){
                // Map 으로 받는 것 짜증..그래서 그냥 곧바로 User 객체로 받기 - 반드시 User클래스안에는 파라미터 없는 생성자가 존재해야만 함.
                val user:User?= snapshot.toObject(User::class.java)
                user?.apply { binding.tv.append("$name ~ $age >> $address\n") }
            }//for...

        }

    }


}







