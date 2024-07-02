package com.hyun.ex58locationgeocoding

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // 주소 <-> 좌표 : 지오코딩 -- 구글 서버에 존재함..그래서 서버에서 데이터를 가져오는 것임.
    // 그래서 인터넷 사용에 대한 퍼미션 필요

    val inputLayoutAddress: TextInputLayout by lazy { findViewById(R.id.input_layout_address) }
    val inputLayoutLat: TextInputLayout by lazy { findViewById(R.id.input_layout_lat) }
    val inputLayoutLng: TextInputLayout by lazy { findViewById(R.id.input_layout_lng) }

    val tv: TextView by lazy { findViewById(R.id.tv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickBtn2() }
        findViewById<Button>(R.id.btn3).setOnClickListener { clickBtn3() }
    }

    // 사용자가 검색한 주소글씨
    var addr: String? = null
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    private fun clickBtn(){
        // 주소 --> 좌표로 변환 [ 지오코딩 ]
        addr= inputLayoutAddress.editText!!.text.toString()

        // 지오코딩작업을 수행하는 객체 생성
        val geocoder: Geocoder = Geocoder(this, Locale.KOREA)

        // 주소에 해당하는 좌표 얻어오기..여러개일 수도 있어서 리스트로 ...
        val addressList: MutableList<Address>? = geocoder.getFromLocationName(addr!!, 3)

        // 좌표정보를 TextView에 보여주기 위해.. 하나의 문자열로 만들기
        val buffer: StringBuffer= StringBuffer()
        addressList?.forEach {
            buffer.append("${it.latitude} , ${it.longitude} \n")
        }
        tv.text= buffer.toString()

        latitude= addressList!![0].latitude
        longitude= addressList!![0].longitude
    }

    private fun clickBtn2(){
        // 디바이스에 설치된 지도 앱을 실행하는 인텐트
        val intent= Intent(Intent.ACTION_VIEW)

        // 보여줄 좌표경로 uri 데이터 만들기
        val uri= Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($addr)&z=10")
        intent.setData(uri)

        startActivity(intent)
    }

    private fun clickBtn3(){
        // 좌표 --> 주소 변환 [ 역지오 코딩 ]
        latitude= inputLayoutLat.editText!!.text.toString().toDouble()
        longitude= inputLayoutLng.editText!!.text.toString().toDouble()

        // 지오코딩 객체 생성
        val geocoder= Geocoder(this, Locale.KOREA)

        // 지오코딩 시작
        val addressList: MutableList<Address>? = geocoder.getFromLocation(latitude, longitude, 3)

        val buffer: StringBuffer= StringBuffer()
        addressList?.forEach {
            buffer.append(it.countryName +"\n") //나라 이름
            buffer.append(it.countryCode +"\n") //나라 코드
            buffer.append(it.postalCode + "\n") //우편번호
            buffer.append(it.getAddressLine(0)+"\n") //주소1 : 도로명 건물번호까지
            buffer.append(it.getAddressLine(1)+"\n") //주소2 : 상세주소 - 없으면 null
            buffer.append(it.getAddressLine(2)+"\n") //주소3 : 상세주소 - 없으면 null
            buffer.append("======================\n\n")
        }

        AlertDialog.Builder(this).setMessage(buffer.toString()).create().show()
    }

}