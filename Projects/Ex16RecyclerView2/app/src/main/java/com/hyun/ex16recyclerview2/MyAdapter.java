package com.hyun.ex16recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

//1) Adapter 클래스 상속 - extends
//2) ViewHolder 이너 클래스 설계 -- class VH
//3) Adapter 클래스 상속하는 곳에서 제네릭<>에 VH클래스명을 지정해주기
//4) Adapter 라면 반드시 구현해야 할 3개의 필수 메소드를 오버라이드
//5) Context, ArrayList 참조변수 2개를 멤버변수로 만들고 생성자로 전달받기
//6) 아답터에서 만들어야 할 3개의 메소드 역할별 코드를 구현하기
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    //constructor
    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // xml 디자인 시안대로 아이템뷰객체를 생성하고 ViewHolder를 만들어 리턴해주기
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item, parent, false);

        VH holder= new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        // 만들어진 아이템뷰안에 값을 설정하는 곳

        Item item= items.get(position); //현재번째 아이템 얻어오기

        holder.tvName.setText(item.name);
        holder.tvRole.setText(item.role);
        holder.iv.setImageResource(item.imgId);
    }

    @Override
    public int getItemCount() {
        // 아답터가 만들 총 아이템뷰의 개수를 리턴해주기
        return items.size();
    }

    //inner class -- 아이템뷰 안에 있는 자식뷰들의 참조변수를 저장하고 있는 클래스
    class VH extends RecyclerView.ViewHolder {

        //아이템뷰 안에 있는 자식뷰들의 참조변수들
        ImageView iv;
        TextView tvName;
        TextView tvRole;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            tvName= itemView.findViewById(R.id.tv_name);
            tvRole= itemView.findViewById(R.id.tv_role);
        }
    }//VH class....................................................
}
