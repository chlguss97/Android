package com.hyun.ex60viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyun.ex60viewbinding.databinding.FragmentMyBinding
import java.util.zip.Inflater

class MyFragment: Fragment() {

    // 뷰들을 대신 참조해주는 바인드 객체 참조변수
    lateinit var binding: FragmentMyBinding

    //프레그먼트가 보여줄 뷰객체를 만들어서 리턴해주는 콜백 메소드 - 라이프 사이클
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 원래는 프레그먼트가 직접 뷰객체를 생성했었음. xml의 뷰들을 대신 참조해주는 바인딩 객체를 이용
        binding= FragmentMyBinding.inflate(inflater,container, false)
        return binding.root


    }

    //뷰가 만들어지고난 후..
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btn.setOnClickListener { binding.tv.text="크크크크킄" }
    }
}