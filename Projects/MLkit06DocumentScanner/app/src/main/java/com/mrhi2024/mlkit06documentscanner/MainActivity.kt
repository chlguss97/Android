package com.mrhi2024.mlkit06documentscanner

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.mrhi2024.mlkit06documentscanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btn.setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){

        // 스캐너 화면(Activity)을 실행하는 인텐트를 통해 스캔작업 수행

        //1. Document Scanner 설정 옵션
        val options: GmsDocumentScannerOptions = GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(true)
            .setPageLimit(3)
            .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG,GmsDocumentScannerOptions.RESULT_FORMAT_PDF)
            .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
            .build()
        val scanner: GmsDocumentScanner= GmsDocumentScanning.getClient(options)

        //2. 스캐너 화면을 실행할 Intent 얻어오기
        scanner.getStartScanIntent(this).addOnSuccessListener {
            // 스캐너 화면 실행하고 결과를 받아오는 작업을 대신해주는 대행사..
            scannerLauncher.launch( IntentSenderRequest.Builder(it).build() )
        }
    }

    val scannerLauncher= registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()){
        if(it.resultCode== RESULT_OK){

            // 스캔 결과를 가진 인텐트로부터 스캔결과 받기
            val result: GmsDocumentScanningResult? = GmsDocumentScanningResult.fromActivityResultIntent(it.data)

            binding.tv.text=""

            //1) JPEG 이미지로 스캔할 때... 이미지의 경로 uri 받기
            result?.pages?.forEach { page ->
                val imgUri: Uri = page.imageUri
                binding.iv.setImageURI(imgUri)
                binding.tv.append("${imgUri.path}\n")
            }
            binding.tv.append("\n=================\n")

            //2) PDF 문서로 스캔할 때.. 문서 경로 uri
            result?.pdf?.let {
                val pdfUri: Uri= it.uri
                val pageCount:Int = it.pageCount

                binding.tv.append("${pdfUri.path} \n")
                binding.tv.append("PDF문서의 페이지 수 : $pageCount \n")
            }

        }
    }

}













