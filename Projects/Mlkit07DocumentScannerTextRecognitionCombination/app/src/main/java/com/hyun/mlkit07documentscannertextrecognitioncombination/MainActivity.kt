package com.hyun.mlkit07documentscannertextrecognitioncombination

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import com.hyun.mlkit07documentscannertextrecognitioncombination.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets }

        setContentView(binding.root)
        binding.btn.setOnClickListener { clickbtn() }
        binding.btn2.setOnClickListener { clickbtn2() }

    }

    private fun clickbtn(){
        //1. 문서스캐너 구성
        val options= GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(true)
            .setPageLimit(5)
            .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG)
            .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
            .build()

        val scanner= GmsDocumentScanning.getClient(options)

        //2. 문서 스캔화면을 실행하는 인텐트를 얻어서 실행하기
        scanner.getStartScanIntent(this).addOnSuccessListener {
            scannerLauncher.launch(IntentSenderRequest.Builder(it).build())
        }
    }

    private val scannerLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()){
        if(it.resultCode== RESULT_OK){
            imageUriList.clear()
            //결과받기
            val result: GmsDocumentScanningResult?= GmsDocumentScanningResult.fromActivityResultIntent(it.data)
            // 스캔한 이미지가 있다면 그 개수만큼 uri가 존재할 것임.. 이를 리스ㅡㅌ에 저장해 놓기
            result?.pages?.forEach { page ->
                imageUriList.add(page.imageUri)

                //실습 편의상 이미지가 여러개여도.. 1개만 분석해보기
                binding.iv.setImageURI(imageUriList[0])
            }
        }
    }

    //스캔결과 이미지의 uri를 가지는 리스트
    private val imageUriList:MutableList<Uri> = mutableListOf()

    private fun clickbtn2(){

        // OCR - Text Recognition

        //1. 입력 이미지 준비
        val bm =(binding.iv.drawable as BitmapDrawable).bitmap
        val image= InputImage.fromBitmap(bm, 0)

        //2. 텍스트 인식기 준비
        val recognizer= TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())

        //3. 이미지 처리
        recognizer.process(image).addOnSuccessListener {
            binding.tv.text= it.text

            // 단락들에 대한 영역 표시해주기..
            val bitmap= bm.copy(bm.config, true)
            val canvas = Canvas(bitmap)
            val paint = Paint().apply {
                color= Color.RED
                style= Paint.Style.STROKE
                strokeWidth=3.0f }
            it.textBlocks.forEach { canvas.drawRect(it.boundingBox!!, paint) }
            binding.iv.setImageBitmap(bitmap)
        }


    }

}