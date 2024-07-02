package com.hyun.ex54cameraapp2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    val iv:ImageView by lazy { findViewById(R.id.iv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn() }
    }

    // 촬영될 사진이 저장될 파일의 경로 uri 정보를 저장할 객체의 참조변수
    var imgUri: Uri? = null

    private fun clickBtn(){
        // 카메라 실행을 위한 택배기사
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // 촬영한 이미지를 저장할 파일의 경로를 만들어주는 기능 메소드를 호출 - 작업이 좀 지저분해서 저 아래 메소드를 만들고 호출..
        setPhotoUri()

        // 캡쳐한 이미지를 저장되게 하려면.. 저장될 파일의 경로 uri를 택배기사에게 extra data로 보내줘야 함
        //if(imgUri != null) intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri)
        imgUri?.let { intent.putExtra(MediaStore.EXTRA_OUTPUT, it) }

        // 결과를 받아주는 작업 대행사에게 요청
        resultLauncher.launch(intent)
    }

    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== RESULT_OK ){
            //이미지 위에서 만들어 놓은 imgUri에 이미지정보가 파일로 저장되어 있을 것임.
            Glide.with(this).load(imgUri).into(iv)
        }
    }


    // 촬영한 이미지가 저장될 File의 콘텐트 경로 uri를 만들어주는 기능 메소드
    private fun setPhotoUri(){
        // 내장 저장공간의 외부 저장소 중에서 공용영역에 저장 - 앱을 삭제해도 파일은 남아 있는 영역

        // 공용영역의 경로부터..
        val path:File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

        // 경로는 정해졌으니.저장될 파일의 이름과 확장자 정하기 - 중복되지 않도록 날짜를 이용하여 명명
        val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val fileName: String = "IMG_" + sdf.format(Date()) + ".jpg" //"IMG_20240219143924.jpg"

        // 경로와 파일명을 결합
        val file:File = File(path, fileName)

        // 여기까지 경로가 잘 되었는지 확인
        //AlertDialog.Builder(this).setMessage(file.toString()).create().show()

        // 카메라앱은 File 의 경로가 아닌 콘텐츠 경로라고 불리는 Uri 를 원함
        // File --> Uri 변경
        imgUri= FileProvider.getUriForFile(this,"aaa", file)
        // 카메라앱에게 Ex54에서 만든 Uri 콘텐츠 경로를 접근할 수 있도록.. 제공해주는 Provider 이용
        // 단, 4대 컴포넌트를 사용한다면..반드시 AndroidManifest.xml 에 등록해야 함.

        //AlertDialog.Builder(this).setMessage(imgUri.toString()).create().show()
    }

}