package com.mrhi2024.mlkit11objectdetectionviacamerax

import android.Manifest
import android.content.pm.PackageManager
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.mrhi2024.mlkit11objectdetectionviacamerax.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //카메라 사용에 대한 퍼미션 체크 - 퍼미션 요청 or 카메라 프리뷰 시작
        if( checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) permissionLauncher.launch(Manifest.permission.CAMERA)
        else startCameraPreview()

    }

    private val permissionLauncher= registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if(it) startCameraPreview() //카메라 프리뷰 시작
        else Toast.makeText(this, "이 앱의 기능을 사용할 수 없습니다.", Toast.LENGTH_SHORT).show()
    }

    // 카메라 프리뷰 작업 시작!! 메소드
    private fun startCameraPreview(){
        //카메라 제공자(관리자) 객체 얻어오기 - [비동기로 처리 - 준비가 완료되면 객체를 얻어올 수 있음]
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener( {
            //카메라 기능 제공자 객체 얻기
            val cameraProvider = cameraProviderFuture.get()

            //[STEP 1.] Preview 객체 생성
            val preview= Preview.Builder().build()
            preview.setSurfaceProvider(binding.previewView.surfaceProvider)

            //[STEP 2.] ImageAnalysis 객체 생성 - ImageAnalysis.Analyzer 인터페이스를 구현한 클래스로 객체를 생성
            val imageAnalysis : ImageAnalysis = ImageAnalysis.Builder().setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()
            //이미지 분석에 사용할 이미지 분석가 설정
            imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(), MyImageAnalyzer())

            // 카메라 선택 [ Font, Back ]
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.unbindAll()
            // 액티비티의 라이프사이클에 연결된 카메라 작업들(프리뷰, 캡처, 비디오촬영, 분석 등.. )
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)

        } ,ContextCompat.getMainExecutor(this))
    }

    // 이미지 분석가 이너 클래스 설계
    inner class MyImageAnalyzer : ImageAnalysis.Analyzer{

        //2. Object Detector 객체 생성
        private val options= ObjectDetectorOptions.Builder().setDetectorMode(ObjectDetectorOptions.STREAM_MODE).enableMultipleObjects().build()
        private val objectDetector= ObjectDetection.getClient(options)

        @OptIn(ExperimentalGetImage::class)
        override fun analyze(image: ImageProxy) {
            Log.d("TAG", "MyImageAnalyzer .....")

            // ML kit Object Detection 작업 수행.. 현재 image(파라미터 전달받은)에 대해
            // 1. 입력이미지 준비
            val mediaImage: Image? = image.image
            if(mediaImage != null){
                val inputImage: InputImage = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees) //프레임의 이미지 회전각도를 반영

                //3. 이미지 처리
                objectDetector.process(inputImage).addOnSuccessListener {
                    binding.tv.text="탐지한 객체 개수 : ${it.size}\n\n"

                    for( detectedObject in it ){
                        binding.tv.append("${detectedObject.trackingId} : ${detectedObject.boundingBox}\n")
                    }

                    // camerax 의 이미지분석용 image는 무조건 width:640, height:480
                    binding.tv.append("\n")
                    binding.tv.append("이미지의 크기 : ${image.width} , ${image.height}  ~ 회전각도 : ${image.imageInfo.rotationDegrees}\n")
                    binding.tv.append("오버레이 크기 : ${binding.myOverlayView.width} , ${binding.myOverlayView.height}\n\n")

                    // 이미지와 오버레이뷰의 크기 비율 계산..
                    val widthRatio: Float = binding.myOverlayView.height.toFloat() / image.width.toFloat()
                    val heightRatio: Float = binding.myOverlayView.width.toFloat() / image.height.toFloat()

                    //탐지한 Object 의 영역 박스 그리기..
                    // 프리뷰뷰와 겹쳐진 투명한 Viw를 설계하여 그 곳에 그림을 그려야 함
                    binding.myOverlayView.drawObjectBoundingBox(it, widthRatio, heightRatio)

                    //현재 이미지 분석은 완료했으니..다음 이미지 프레임을 분석하기 위해..
                    image.close()
                }
            }

            //현재 image에 대한 분석이 끝났다고..닫아줘야 함... 닫으면.. 다음 이미지 분석을 위해 또 이 analyze()메소드가 실행됨
            //image.close()
        }
    }

}//MainActivity class..
