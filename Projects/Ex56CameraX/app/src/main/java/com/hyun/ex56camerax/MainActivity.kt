package com.hyun.ex56camerax

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OnImageSavedCallback
import androidx.camera.core.ImageCapture.OutputFileOptions
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // Camera H/W 를 직접 제어하여 카메라 앱을 구현해보기
    // 이를 위해 등장한 JetPack 으로 공개된 CameraX 라이브러리를 사용해야 함

    // 구현하기 위해 Google에서 제공하는 튜토리얼을 가이드라인을 참고하여 구현함.

    val previewView:PreviewView by lazy { findViewById(R.id.preview_view) }
    val iv:ImageView by lazy { findViewById(R.id.iv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //액티비티의 창(window) 영역을 외부 상태표시줄까지.. 모두 사용하도록 설정
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //촬영버튼 처리
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{takePhoto()}


        //카메라 사용을 위한 동적 퍼미션
        //먼저. 카메라 퍼미션 허용여부를 체크
        val permissionState:Int= checkSelfPermission(Manifest.permission.CAMERA)
        if(permissionState==PackageManager.PERMISSION_DENIED){
            //퍼미션 요청 다이얼로 보여주는 작업을 대신해주는 대행사 이용
            resultLauncher.launch(Manifest.permission.CAMERA)
        }else{
            startPreview() //미리보기 시작
        }
    }//onCreate method..

    //퍼미션 요청을 대신 받아주는 대행사 등록
    val resultLauncher: ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            permissionResult:Boolean ->

        if(permissionResult) {
            //카메라 미리보기를 시작!!
            startPreview()
        }
        else finish()
    }


    // 미리보기 작업 메소드
    private fun startPreview(){
        Toast.makeText(this, "미리보기를 시작합니다.", Toast.LENGTH_SHORT).show()

        //카메라 프로세스 제공객체 참조하기
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        //카메라 프로세스 작업 준비가 완료되엇다는 것을 듣는 리스너 처리
        cameraProviderFuture.addListener({
              //카메라 프로세스 시작!
              val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            //프리뷰 객체 생성
            val preview=Preview.Builder().build()
            //프리뷰가 취득한 이미지데이터를 보여줄 고속버퍼뷰(Surface)를 설정
            preview.setSurfaceProvider(previewView.surfaceProvider)

            // 프리뷰 작업 시작..
            cameraProvider.unbindAll()
            //cameraProvider.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA,preview)

            //이미지 캡처가 포함된 작업 시작
            imageCapture=ImageCapture.Builder().build()
            cameraProvider.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA, preview, imageCapture)

        },ContextCompat.getMainExecutor(this))

    }

    // 이미지 캡처 객체의 참조변수
    var imageCapture:ImageCapture? = null


    //촬영버튼 클릭시 실행되는 메소드
    private  fun takePhoto(){
        // 이미지캡처 객체가 없을 수도 있으니..
        imageCapture ?: return


        //촬영한 사진이 저장될 파일명
        val name:String = SimpleDateFormat("yyyyMMMMHHmmss", Locale.KOREA).format(System.currentTimeMillis())
        //MediaStore의 Database에 파일의 정보를 삽입(insert) 하기 위해.. 한줄(레코드) 데이터 객체 생성
        val contentValues: ContentValues= ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)// 파일명
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")// 타입
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.P){
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,"Pictures/Ex56") //경로
        }

        //촬영할 사진을 저장할 옵션객체
        val outputFileOptions: OutputFileOptions= ImageCapture.OutputFileOptions.Builder(contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build()

        imageCapture!!.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this), object: OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Snackbar.make(previewView,"image saved",Snackbar.LENGTH_SHORT).show()
                //촬영된 사진 uri
                val uri:Uri? = outputFileResults.savedUri
                Glide.with(this@MainActivity).load(uri).into(iv)
            }

            override fun onError(exception: ImageCaptureException) {
                Snackbar.make(previewView,"fail",Snackbar.LENGTH_SHORT).show()

            }
        })
    }


}//MainActivity class...