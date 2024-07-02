package com.hyun.ex71firebasechatting

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.hyun.ex71firebasechatting.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //파이어베이스와 연동 [ Firebase Storage & Firestore]

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var imgUri: Uri? = null // 선택된 이미지의 콘텐츠 주소(경로)

    private var isFirst: Boolean = true // 처음실행 하는지 여부
    private var isChange: Boolean= false //이미지를 변경한 적이 있는가?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.civ.setOnClickListener { clickImage() }
        binding.btn.setOnClickListener { clickBtn() }

        // sharedPreference에 저장한 닉네임과 프로필 이미지 얻어오기
        val pref:SharedPreferences= getSharedPreferences("profile", MODE_PRIVATE)
        G.nickname= pref.getString("nickname","")!!
        G.profileUrl=pref.getString("profileUrl","")!!

        if(G.nickname!=""){
            binding.inputLayoutNickname.editText!!.setText(G.nickname)
            Glide.with(this).load(G.profileUrl).into(binding.civ)

            isFirst= false // 처음이 아니라는 식별값
        }


    }

    private fun clickImage() {
        val intent =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Intent(MediaStore.ACTION_PICK_IMAGES) else null
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            imgUri = it.data?.data
            Glide.with(this).load(imgUri).into(binding.civ)

            isChange= true //사진을 선택했으니.. 변경했다고.. 식별값
        }

    private fun clickBtn() {
        //처음이거나..이미지를 변경했다면 닉네임과 프로필 이미지를 저장
        if (isFirst || isChange) {
            saveData()
        } else {//처음이 아니면.. 곧바로 채팅화면으로 이동
            startActivity(Intent(this, ChatActivity::class.java))
            finish()

        }//---------------------



    }
    private fun saveData()  {
        //이미지를 선택하지 않으면 채팅 못함
        imgUri?: return

        //입력한 닉네임 가져오기 - 닉네임은 이앱 어디서든 사용되기에 전역변수처럼 만들고자 함 G클래스를 만들고 사용
        G.nickname= binding.inputLayoutNickname.editText!!.text.toString()

        //먼저 이미지를 Firebase Storage 저장소에 먼저 업로드.. 하고 업로드된 이미지의 [다운로드 url]을 닉네임과 함께 DB에 저장

        //저장소에 이미지 업로드를 위한 참조객체 업어오기
        val fileName= "IMG_"+ SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(Date())
        val imgRef: StorageReference= Firebase.storage.getReference("profileImage/$fileName")

        //이미지 업로드
        imgRef.putFile(imgUri!!).addOnSuccessListener {
            //이미지 업로드가 성공되었다면.. 이미지의 [다운로드 url] 얻어오기
            imgRef.downloadUrl.addOnSuccessListener {
                //다운로드 URL을 문자열로 변환해서 전역변수에 저장
                G.profileUrl= it.toString()
                Toast.makeText(this, "프로필 이미지 저장 완료\n${G.profileUrl}", Toast.LENGTH_SHORT).show()

                //[닉네임, 프로필이미지 url] 저장을 서버와 디바이스 2군데에 저장
                //1] 서버의 Firebase DB에  저장
                //"profile" 이라는 이름의 Collection 참도(없으면 생성, 있으면 참조)
                val profileRef:CollectionReference = Firebase.firestore.collection("profile")

                //닉네임을 Document 이름으로 사용하고.. 필드 값으로 이미지 url을 저장할 것임.
                // 회원의 필드값이 여러개일 수도 있으니 .. 나중에 확장을 편히 하기 위해
                val data: MutableMap<String,Any> = mutableMapOf()
                data["profileUrl"]= G.profileUrl
                //.. 나중에 추가할게.. 있다면.. 여기
                profileRef.document(G.nickname).set(data)
                //---------------------------------------------------------


                //2] 앱을 실행할때 이전에 저장된 내 프로필을 확인할 수 있도록.. SharedPreference
                val pref: SharedPreferences= getSharedPreferences("profile", MODE_PRIVATE)
                val editor: Editor =pref.edit()
                editor.putString("nickname",G.nickname)
                editor.putString("profileUrl",G.profileUrl)
                editor.apply() //위 작업을 적용하라는 요청의 명령.. 안하면.. 적용 안됨.

                //저장이 완료되었으니.. 채팅화면으로 이동
                startActivity(Intent(this, ChatActivity::class.java))
                finish()
            }
        }
    }
}
