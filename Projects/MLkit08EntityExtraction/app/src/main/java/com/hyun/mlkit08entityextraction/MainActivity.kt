package com.hyun.mlkit08entityextraction

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.nl.entityextraction.Entity
import com.google.mlkit.nl.entityextraction.EntityExtraction
import com.google.mlkit.nl.entityextraction.EntityExtractionParams
import com.google.mlkit.nl.entityextraction.EntityExtractor
import com.google.mlkit.nl.entityextraction.EntityExtractorOptions
import com.hyun.mlkit08entityextraction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }

    }

    private fun  clickBtn(){
        //추출 대상 글씨..
        var text:String="최적의 일정을 결정하려면 내일 info@naver.com 으로 테스트팀에 문의하세요"
        text= "Google에서 주문 상품이 발송되었습니다. 배송 진행 상황을 확인하려면 운송장 번호 9612804152073070474837을 사용하세요."
        text= "저녁 식사를 결제하려면 555-555-1234로 식당에 전화하세요. 제 카드 번호는 5111-1111-1111-1111 입니다"
        text= "미래IT캠퍼스 : 서울 성동구 왕십리로 315 16층"
        text= "2024년 3월 29일, 내일 오후 6시에 서울 성동구 왕십리 광장로 17 왕십리역에서 만나자"
        text= "1000원"
        text= "1000won"
        text= "1000dollar"
        text= "10000 won"
        text= "1000$"
        text= "1000₩"
        text= "1000KRW"
        text= "1000USD"
        text= "1000EUR"

        //1. 항목 추출기 준비
        val entityExceptor: EntityExtractor= EntityExtraction.getClient(EntityExtractorOptions.Builder(EntityExtractorOptions.KOREAN).build())

        //2. 해당 언어의 모델 다운로드 할 필요가 있다면 다운로드..
        entityExceptor.downloadModelIfNeeded().addOnSuccessListener {

            //3. 추출 대상글씨를 추출기의 전달값(파라미터) 객체로 만들기
            var params : EntityExtractionParams = EntityExtractionParams.Builder(text).build()

            //4. 추출작업 수행
            entityExceptor.annotate(params).addOnSuccessListener {
                binding.tv.text="추출 항목 개수 : ${it.size}\n\n"

                for(entityAnnotation in it){
                    //추출된 주석 글씨 출력
                    binding.tv.append("추출된 항목 주석: ${entityAnnotation.annotatedText}\n")

                    //추출된 항목의 위치범위
                    binding.tv.append("추출 항목 범위: ${entityAnnotation.start}~ ${entityAnnotation.end}\n")

                    //추출된 항목에 부여된 주석의 개수 ..
                    binding.tv.append("추출 항목의 주석 개수 : ${entityAnnotation.entities.size}\n")

                    entityAnnotation.entities.forEach {
                        binding.tv.append("항목 클래스명: $it \n")

                        //타입별로 사용자 안내글씨 출력
                        when(it.type) {
                            Entity.TYPE_ADDRESS -> binding.tv.append("주소 항목입니다\n")
                            Entity.TYPE_DATE_TIME -> binding.tv.append("날짜-시간 항목입니다\n")
                            Entity.TYPE_EMAIL -> binding.tv.append("이메일 항목입니다")
                            Entity.TYPE_FLIGHT_NUMBER -> binding.tv.append("항공편 번호 항목입니다\n")
                            Entity.TYPE_IBAN -> binding.tv.append("IBAN 은행계좌 항목입니다\n")
                            Entity.TYPE_ISBN -> binding.tv.append("국제표준도서번호 항목입니다\n")
                            Entity.TYPE_MONEY -> binding.tv.append("화폐/통화 항목입니다\n")
                            Entity.TYPE_PAYMENT_CARD -> binding.tv.append("결제/신용카드번호 항목입니다\n")
                            Entity.TYPE_PHONE -> binding.tv.append("전화번호 항목입니다\n")
                            Entity.TYPE_TRACKING_NUMBER -> binding.tv.append("운송장번호(국제표준) 항목입니다\n")
                            Entity.TYPE_URL -> binding.tv.append("URL 항목입니다\n")
                            else-> binding.tv.append("특정할 수 없는 항목\n")

                        }
                    }

                    binding.tv.append("----------------------\n")
                }

            }
        }
    }
}