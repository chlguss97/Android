package com.hyun.ex59kakaomap

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.common.util.Utility
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMap.OnInfoWindowClickListener
import com.kakao.vectormap.KakaoMap.OnLabelClickListener
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdate
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.mapwidget.InfoWindow
import com.kakao.vectormap.mapwidget.InfoWindowOptions
import com.kakao.vectormap.mapwidget.component.GuiLayout
import com.kakao.vectormap.mapwidget.component.GuiText
import com.kakao.vectormap.mapwidget.component.Orientation

class MainActivity : AppCompatActivity() {

    // 카카오 개발자 사이트의 가이드라인을 참고하여 구현  developers.kakao.com

    val mapView: MapView by lazy { findViewById(R.id.map_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 카카오 인증서 [키 해시] 얻어오기
        val keyHash: String = Utility.getKeyHash(this)
        Log.d("keyHash", keyHash)

        // 지도 시작하기
        mapView.start(object : KakaoMapReadyCallback(){
            override fun onMapReady(kakaoMap: KakaoMap) {
                // 맵의 로딩이 완려되면 실행되는 영역 -- 여기까지만 써도.. 지도는 보임.

                // 카메라 위치를 이동
                val myPos: LatLng = LatLng.from(37.5617, 127.0342) // 내 위치
                var cameraUpdate: CameraUpdate = CameraUpdateFactory.newCenterPosition(myPos,16) // 지도의 가운데 지점을 설정
                kakaoMap.moveCamera(cameraUpdate)

                // 내 위치에 마커 만들기 - 아이콘모양 [ 벡터이미지는 안됨. .png or .jpg만 가능 ]
                // 라벨(마커)의 위치, 스타일 지정하는 옵션객체 생성
                var labelOptions: LabelOptions= LabelOptions.from(myPos).setStyles(R.drawable.profle)

                // 라벨(마커)이 그려질 레이어(Layer) 얻어오기
                val labelLayer: LabelLayer= kakaoMap.labelManager!!.layer!!

                // 라벨(마커) 추가하기
                labelLayer.addLabel(labelOptions)

                // 여러지점에 대한 마커(라벨)들 추가하기
                val positions: MutableList<LatLng> = mutableListOf()
                positions.add(LatLng.from(37.5617, 127.0343))
                positions.add(LatLng.from(37.5619, 127.0340))
                positions.add(LatLng.from(37.5624, 127.0352))
                positions.add(LatLng.from(37.5614, 127.0357))
                positions.add(LatLng.from(37.5606, 127.0351))

                // 포지션 개수만큼 라벨(마커) 추가하기
                for( pos in positions ){
                    var options: LabelOptions = LabelOptions.from(pos).setStyles(R.drawable.location).setTexts("위도:${pos.latitude}","경로:${pos.longitude}")
                    //마커 추가하기
                    kakaoMap.labelManager!!.layer!!.addLabel(options)
                }

                // 라벨(마커) 클릭할 때 반응하기
                kakaoMap.setOnLabelClickListener( object : OnLabelClickListener{
                    override fun onLabelClicked(kakaoMap: KakaoMap?,layer: LabelLayer?,label: Label?) {

                        // 정보창 [ info window ] 보여주기
                        val layout: GuiLayout= GuiLayout(Orientation.Vertical)
                        layout.setPadding(16,16,16,16)
                        layout.setBackground(R.drawable.base_msg, true)

                        // 라벨 글씨를 보여주는 TextView 같은 GuiText
                        label!!.texts.forEach {
                            val guiText: GuiText = GuiText(it)
                            guiText.setTextSize(30)
                            guiText.setTextColor(Color.WHITE)

                            layout.addView(guiText)
                        }

                        // 정보창 객체 만들기
                        val infoWindowOptions: InfoWindowOptions = InfoWindowOptions.from(label.position)
                        infoWindowOptions.body= layout
                        infoWindowOptions.tag= "http://www.mrhi.or.kr"

                        kakaoMap!!.mapWidgetManager!!.infoWindowLayer.removeAll()
                        kakaoMap!!.mapWidgetManager!!.infoWindowLayer.addInfoWindow(infoWindowOptions)
                    }

                })// label click...

                // 정보창 클릭 처리
                kakaoMap.setOnInfoWindowClickListener(object : OnInfoWindowClickListener{
                    override fun onInfoWindowClicked(
                        kakaoMap: KakaoMap?,
                        infoWindow: InfoWindow?,
                        guiId: String?
                    ) {
                        val info:String = infoWindow!!.tag.toString()

                        val intent= Intent(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse(info))

                        startActivity(intent)
                    }
                })


            }
        })

    }
}