package com.hyun.ex69firebasestorage

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.hyun.ex69firebasestorage.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // Firebase : 백엔드 플랫폼
    // Firebase Cloud Storage : 저장소

    // 앱과 Firebase 플랫폼을 연동하기.. [ firebase.com의 가이드라인 참고 ]

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLoad.setOnClickListener { clickLoad() }
        binding.btnSelect.setOnClickListener { clickSelect() }
        binding.btnUpload.setOnClickListener { clickUpload() }
    }

    private fun clickLoad(){
        //Firebase Storage에 저장되어 있는 이미지 파일 읽어오기

        // 읽어오길 원하는 파일의 참조객체를 얻어오기
        val imgRef: StorageReference= Firebase.storage.getReference("moana01.jpg")

        // 파일의 서버경로 url 을 얻어와서 이미지뷰에 보여주기
        imgRef.downloadUrl.addOnSuccessListener(object : OnSuccessListener<Uri>{
            override fun onSuccess(p0: Uri?) { //파라미터로 '다운로드 url'이 전달됨
                Glide.with(this@MainActivity).load(p0).into(binding.iv)
            }
        })
    }

    var imgUri: Uri?= null

    private fun clickSelect(){
        val intent= if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU) Intent(MediaStore.ACTION_PICK_IMAGES) else Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*")
        resultLauncher.launch(intent)
    }

    private val resultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        imgUri= it.data?.data
        Glide.with(this).load(imgUri).into(binding.iv)
    }

    private fun clickUpload(){

        //Firebase Storage 이미지(imgUri) 업로드

        //파일이 저장될 저장소의 이름을 정해야함 -- 중복되면 안됨
        val name: String= "IMG_" + SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(Date())

        // 저장할 파일 위치에 대한 참조객체 얻어오기
        val imgRef: StorageReference= Firebase.storage.getReference("upload/$name") //name에 해당하는 참조위치가 없으면 만들어 줌

        //이미지를 선택했다면 .. 업로드
        imgUri?.apply {
            imgRef.putFile(this).addOnSuccessListener {
                Toast.makeText(this@MainActivity, "success upload", Toast.LENGTH_SHORT).show()
            }
        }


    }


}







