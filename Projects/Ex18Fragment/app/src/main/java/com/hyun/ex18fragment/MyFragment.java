package com.hyun.ex18fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    TextView tv;
    Button btn;
    Button btn2;

//프레그머트가 화면을 보여워야할 View 객체를 생성하여 리턴해주는 콜백메소드 - 라이프사이클


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // layout폴더 안에 있는 fragment_my.xml 문서를 읽어와서 뷰 객체로 만들어주는 작업..
        View view=inflater.inflate(R.layout.fragment_my, container,false);
        return view;

    }

    //프레그먼트가 보여줄 뷰객체를 만들어낸 후.. 리스너 연결작업 등을 수행하는 작업영역 메소드
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    tv= view.findViewById(R.id.tv);
    btn= view.findViewById(R.id.btn);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tv.setText("Nice to meet you..");
        }
    });
    btn2= view.findViewById(R.id.btn2);
    btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //MainActivity에 있는 TextView를 제어하기
            //먼저 이 프레그먼트를 보여주는 액티비티 객체를 참조하는 메소드
            MainActivity ma= (MainActivity)getActivity();
            ma.tv.setText("으헤헤헤...");
        }
    });


    }
}

