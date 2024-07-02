package com.hyun.ex57location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class MainActivity : AppCompatActivity() {

    // 기본적으로 안드로이드는 LocationManager 를 통해 내 위치를 얻어옴
    // 이를 효율적으로 개선하여 만든 라이브러리 Google Fused API 라고 부름
    // 라이브러리 추가 play-services-location

    // 내 위치는 위험한 정보임..퍼미션 필요

    val tv: TextView by lazy { findViewById(R.id.tv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn() }

        // 위치정보 제공 퍼미션
        val permissionState:Int = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if(permissionState==PackageManager.PERMISSION_DENIED) resultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }//onCreate method..

    val resultLauncher: ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if(it) Toast.makeText(this, "위치정보 제공에 동의하셨습니다.", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "위치정보 제공을 거부하셨기에 일부 기능사용이 제한됩니다.", Toast.LENGTH_SHORT).show()
    }

    // Fused API에 제공하는 위치정보제공자 객체 참조변수
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private fun clickBtn(){
        // 위치정보 제공자 객체 얻어오기
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)

        // 위치정보 요청 객체 생성 - 최적화의 기준을 정하는 객체 - 기준 : 높은 정확도:gps, 3초마다 갱신
        val locationRequest: LocationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,3000).build()

        // 위치정보 실시간 갱신 요청
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationListener, Looper.getMainLooper())
    }

    // 업데이트 위치정보의 결과를 받을때 반응하는 리스너객체 생성
    private val locationListener: LocationListener = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            // 파라미터 location : 찾은 위치정보 객체 (위도,경도,고도...등의 정보를 가진 객체)
            tv.text= "내 위치 : ${location.latitude} , ${location.longitude}"

            // 특정 위치에 들어갔을 때 이벤트 발생!!
            // 왕십리역 좌표 : 37.5612919, 127.0375806

            // 내 위치와 왕십리역 사이의 실제거리 (m:미터 단위)
            val result: FloatArray = floatArrayOf(0.0f, 0.0f, 0.0f) //3칸짜리 빈 배열 준비 --이 배열안에 계산결과를 넣어줌
            Location.distanceBetween(location.latitude, location.longitude, 37.5612919, 127.0375806, result)

            // result[0]에 두 지점의 거리를 m미터 단위로 계산하여  가지고 있음.
            if(result[0]<50){ // 두 지점의 거리가 50m 이내...
                if(wasEnter==false){
                    AlertDialog.Builder(this@MainActivity).setMessage("축하합니다. 이벤트 당첨!").setPositiveButton("OK", null).create().show()
                    wasEnter=true
                }
            }else{
                wasEnter= false
            }

        }
    }

    // 특정 지점에 들어간 적이 있는가..를 저장하는 변수
    var wasEnter:Boolean= false


}