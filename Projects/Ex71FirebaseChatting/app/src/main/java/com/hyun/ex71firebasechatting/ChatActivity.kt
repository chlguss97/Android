package com.hyun.ex71firebasechatting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.hyun.ex71firebasechatting.databinding.ActivityChatBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatActivity : AppCompatActivity() {

    private val binding by lazy { ActivityChatBinding.inflate(layoutInflater) }

    //채팅방 이름
    val roomName:String="ROOM #1" //채팅방 이름

    //메세지 아이템을 가지고 있는 리스트
    val messageItems: MutableList<MessageItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바의 제목과 서브타이틀 지정[채팅방명, 닉네임]
        binding.toolbar.title= roomName
        binding.toolbar.subtitle= G.nickname

        //[send] 버튼 클릭처리

        binding.btnSend.setOnClickListener { clickSend() }

        // 리사이클러에 아답터설정
        binding.recyclerView.adapter= MessageAdapter(this, messageItems)

        // "채팅방명"의 컬렉션 정보가 변경되는 것을 듣는 리스너를 적용함으로 실시간 반응하기
        val chatRef: CollectionReference=Firebase.firestore.collection(roomName)
        chatRef.addSnapshotListener { value, error ->
            // 첫번째 파라미터 value : QuerySnapshot ~~ DocumentSnapshot들을 가지고 있는 스냅샷

            // 변경된 Document만 찾아달라고 요청
            value?.documentChanges?.forEach {
                //변경된 Document의 데이터를 촬영한 Snapshot 얻어오기
                val snapshot:DocumentSnapshot = it.document

                // 필드값들을 한방에 MessageItem 객체로 받기
                val item:MessageItem?= snapshot.toObject(MessageItem::class.java)

                // 아이템을 아이템 리스트에 추가 -- 리사이클러뷰에 보여주기위해.. 아답터 갱신
                item?.apply {
                    messageItems.add(this)
                    binding.recyclerView.adapter!!.notifyItemInserted(messageItems.size-1)
                    //리사이클러 뷰의 스크롤 위치를 마지막 위치로 이동..
                    binding.recyclerView.scrollToPosition(messageItems.size-1)
                }

            }//forEach
            //Toast.makeText(this, "${messageItems.size}", Toast.LENGTH_SHORT).show()
        }



    }

    private fun clickSend() {

        //firebase DB에 저장할 데이터들( 닉네임, 메세지, 시간, 프로필이미지URL)
        val nickname= G.nickname
        val message= binding.et.text.toString()
        val time= SimpleDateFormat("HH:mm", Locale.KOREA).format(Date())
        val profileUrl= G.profileUrl

        // firebase에 저장힐 때 한방에 데이터들을 묶어서 저장 [MessageItem 객체로 만들어서..]
        val item: MessageItem= MessageItem(nickname, message, time, profileUrl)

        //"채팅방명"으로 된 Collection 참조객체를 소환
        val chatRef: CollectionReference= Firebase.firestore.collection(roomName)

        // 메세지 저장
        val n="MSG_"+ SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(Date())
        chatRef.document(n).set(item)

        //다음 메세지 입력을 편하게 하기 위해.. EditText에 있는 글씨 지우ㅜ기
        binding.et.setText("")


        //소프트 키보드 안 보이도록..
        val imm= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}