package com.hyun.tp06adapterview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    ArrayList<Item> items;
    Context context;

    public MyAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item,parent,false);

        VH holder= new VH(itemView);
        return holder;


    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        Item item= items.get(position);

        holder.iv.setImageResource(item.img);
        holder.name.setText(item.name);
        holder.iv.setImageResource(item.img);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView name;
        TextView lv;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            name= itemView.findViewById(R.id.name);
            lv= itemView.findViewById(R.id.lv);

        }
    }



    }



