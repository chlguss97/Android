package com.hyun.ex15recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    //생성자메소드
    public MyAdapter(Context context, ArrayList<Item> items){
        this.context= context;
        this.items= items;
    }

    //이 아답터가 만들 아이템뷰 객체 1개를 만들어내는 메소드
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //res/layout폴더에 설계한 아이템뷰 1개의 디자인 시안.xml 모양 대로 뷰객체를 만드는 작업코딩

        //layout폴더안에 있는 xml 레이아웃 모양대로 뷰 객체를 생성해주는 능력을 가진
        //객체(LayoutInflater)를 Context로 부터 소환하여 의뢰하기..
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item, parent, false);

        VH vh= new VH(itemView);
        return vh;
    }

    //아이템뷰 안에 자식뷰들에 현재번째 아이템값을 설정해주는 작업 코딩
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //두번째 파라미터 position : 현재 연결한 데이터의 번호
        Item item= items.get(position);

        //첫번째 파라미터 holder : 아이템뷰안에 있는 자식뷰들의 참조변수를 저장하고 있는 객체
        VH vh= (VH)holder;
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.msg);
    }

    //아답터가 만들 아이템뷰의 총 개수를 리턴해주는 기능메소드 - 대량의 데이터 리스트의 사이즈를 리턴
    @Override
    public int getItemCount() {
        return items.size();
    }

    //이너클래스
    //만들어진 아이템뷰 안에 있는 자식뷰들(TextView 2개) 의 참조변수를 가지는 클래스 설계
    class VH extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName= itemView.findViewById(R.id.tv_name);
            tvMsg= itemView.findViewById(R.id.tv_msg);
        }
    }


}//Adapter class
