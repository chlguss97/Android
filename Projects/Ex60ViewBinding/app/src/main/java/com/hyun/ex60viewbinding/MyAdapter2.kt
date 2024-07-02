package com.hyun.ex60viewbinding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hyun.ex60viewbinding.databinding.RecyclerItemBinding

class MyAdapter2 constructor(val context:Context, val itemList: List<Item>) : Adapter<MyAdapter2.VH>() {

    inner class VH(val binding: RecyclerItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: RecyclerItemBinding= RecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int = itemList.size  //함수의 단순화

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.iv.setImageResource(itemList[position].imgId)
        holder.binding.tv.text= itemList[position].title

        //아이템뷰 클릭이벤트 처리
        holder.binding.root.setOnClickListener { Toast.makeText(context, "${itemList[position].title}", Toast.LENGTH_SHORT).show() }

    }

}