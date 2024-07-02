package com.hyun.ex35activityrecyclerview;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item= items.get(position);

        holder.iv.setImageResource(item.imgId);
        holder.tvTitle.setText(item.title);
        holder.tvMessage.setText(item.message);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvTitle, tvMessage;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            tvTitle= itemView.findViewById(R.id.tv_title);
            tvMessage= itemView.findViewById(R.id.tv_message);

            //아이템뷰를 클릭하는 것에 반응하기
            itemView.setOnClickListener(v -> {
                //클릭한 아이템뷰의 위치번호를 얻어오기 0, 1, 2, .....
                int position= getLayoutPosition();
                Item item= items.get(position);

                //클릭한 아이템의 정보를 상세하게 보여 줄 화면(Activity)으로 전환
                Intent intent= new Intent(context, DetailActivity.class);
                //상세화면으로 전달할 데이터들..추가
                intent.putExtra("title", item.title);
                intent.putExtra("msg", item.message);
                intent.putExtra("imgId", item.imgId);

                //화면전환할때 전환효과 주기
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View, String>(iv,"ss"));
                context.startActivity(intent, options.toBundle());

                //Toast.makeText(context, position+":"+item.title, Toast.LENGTH_SHORT).show();
            });

        }
    }
}
