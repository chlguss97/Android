package com.hyun.ex17viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPageAdapter extends RecyclerView.Adapter<MyPageAdapter.VH> {

    Context context;
    ArrayList<PageItem> pageList;

    //constructor
    public MyPageAdapter(Context context, ArrayList<PageItem> pageList) {
        this.context = context;
        this.pageList = pageList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //디자인 시안 모양으로 액자(뷰객체)를 만들어내는 작업 영역

        // layout폴더 안에 있는 page.xml 문서를 읽어와서 뷰 객체로 만들어주는
        // 기능이 있는 객체(LayoutInflater)를 운영체제 대리인(context)으로 부터 소환하기
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.page, parent, false);

        //만들어진 itemView 안에 있는 자식뷰들의 참조변수를 저장하는 객체를 생성
        VH aa= new VH(itemView);
        return aa;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        //만들어진 액자(뷰객체)안에 값을 설정하는 작업 영역

        //현재 연결할 데이터를 얻어오기
        PageItem item= pageList.get(position);

        holder.iv.setImageResource(item.imgId);
        holder.tv.setText(item.title);
    }

    @Override
    public int getItemCount() {
        //아답터가 만들 총 아이템뷰의 개수를 지정하는 영역
        return pageList.size();
    }

    //아이템뷰 1개 안에 있는 자식뷰들의 참조변수를 저장하는 클래스 설계 -- inner class
    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            tv= itemView.findViewById(R.id.tv);

        }

    }
}
