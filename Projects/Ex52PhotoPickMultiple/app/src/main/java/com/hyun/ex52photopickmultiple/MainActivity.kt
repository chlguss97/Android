package com.hyun.ex52photopickmultiple

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener{ clickBtn1() }
        findViewById<Button>(R.id.btn2).setOnClickListener{ clickBtn2() }
    }

    val tv: TextView by lazy { findViewById(R.id.tv) }

    // 선택된 파일들의 uri 를 저장하는 리스트
    val imgs: MutableList<Uri?> = mutableListOf()
    val pager: ViewPager2 by lazy { findViewById(R.id.pager) }
    val dotsIndicator: WormDotsIndicator by lazy { findViewById(R.id.dots_indicator) }

    private fun clickBtn1(){
        val intent: Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        resultLauncher.launch(intent)

    }

    //결과 받아주는 대행사 객체 등록
    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->

        if(result.resultCode == RESULT_CANCELED ){
            tv.text="선택을 하지 않았습니다."
        }else{
            imgs.clear()

            // 1개만 선택했을때는.. uri data로 전달받음
            if(result.data?.data != null){
                imgs.add(result.data?.data)

            }else{// 2개 이상일때틑 ClipData 라는 것으로 여러 파일들의 정보를 받음
                val cnt:Int = result.data?.clipData?.itemCount!!
                for( i in 0 until cnt){
                    imgs.add(  result.data?.clipData?.getItemAt(i)?.uri )
                }
            }

            tv.text= "선택한 이미지 개수 : ${imgs.size}"
            pager.adapter = MyAdapter(this, imgs)
            dotsIndicator.attachTo(pager)


        }

    }


    private fun clickBtn2(){
        // JetPack 에서 새로 추가된 기능
        pickMultipleLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val pickMultipleLauncher: ActivityResultLauncher<PickVisualMediaRequest> = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()){
            uris: List<Uri> ->

        imgs.clear()

        for( uri in uris) imgs.add(uri)

        tv.text= "선택한 이미지 개수 : ${imgs.size}"
        pager.adapter= MyAdapter(this, imgs)
        dotsIndicator.attachTo(pager)

    }

}













