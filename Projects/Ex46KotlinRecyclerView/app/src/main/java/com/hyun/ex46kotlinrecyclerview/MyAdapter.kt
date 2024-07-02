package com.hyun.ex46kotlinrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide


class MyAdapter constructor(var context:Context, var itemList:MutableList<Item>) : Adapter<MyAdapter.VH>() {
    inner class VH constructor(itemView: View) : ViewHolder(itemView){
        val tvTitle:TextView = itemView.findViewById(R.id.tv_title)
        val tvMsg:TextView= itemView.findViewById(R.id.tv_msg)
        val iv:ImageView= itemView.findViewById(R.id.iv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater:LayoutInflater = LayoutInflater.from(context)
        val itemView:View= inflater.inflate(R.layout.recycler_item, parent, false)
        return VH(itemView)
    }

    //함수의 단순화
    override fun getItemCount(): Int= itemList.size


    override fun onBindViewHolder(holder: VH, position: Int) {
       //val item:Item= itemList.get(position)
        val item:Item= itemList[position] // 마치 배열처럼.. 권장

        holder.tvTitle.setText(item.title)
        holder.tvMsg.text= item.message //권장

        //image load library 사용해보기 - Glide
        Glide.with(context).load(item.imgId).into(holder.iv)
    }

}