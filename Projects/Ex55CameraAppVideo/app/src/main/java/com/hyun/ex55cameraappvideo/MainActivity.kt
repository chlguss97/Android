package com.hyun.ex55cameraappvideo

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val vv: VideoView by lazy { findViewById(R.id.vv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn() }


    }

    private  fun clickBtn(){
        //카메라 앱 실행시켜주는 택배기사 생성
        val intent= Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        resultLauncher.launch(intent)

    }

    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
        if(result.resultCode== RESULT_OK){
            //동영상 촬영은 별도의 Extra data 설정 없이도 자동 촬영결과가 File로 저장됨
            //그래서 결과를 파일의 컨텐츠 경로인 uri로 받을 수 있음
            //uri 데이터를 가지고 온 인텐트 객체부터 참조하기
            //val intent= result.data
            //인텐츠 객체로부터 uri 데이터 받기
            //val uri:Uri? = intent?.data

            //위 2줄을 줄여서
            val uri:Uri? = result.data?.data
            //동여상 파일을 비디오뷰를 통해 플레이하기
            vv.setVideoURI(uri)
            // 비디오뷰에 컨트롤바를 붙이기
            vv.setMediaController(MediaController(this))
           // 동영상은 용량이 커서 로딩 과정이 오래걸림. 그래서 로딩준비가 완료되면 start 해야함
            vv.setOnPreparedListener(object : OnPreparedListener{
                override fun onPrepared(mp: MediaPlayer?) {
                    vv.start()
                }

            })
        }
    }


}