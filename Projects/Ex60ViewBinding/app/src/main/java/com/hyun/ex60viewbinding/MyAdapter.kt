package com.hyun.ex60viewbinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hyun.ex60viewbinding.databinding.RecyclerItemBinding

class MyAdapter constructor(val context:Context, val itemList:MutableList<Item>) : Adapter<MyAdapter.VH>(){

    //아이템 뷰 1개의 자식뷰들의 참조변수를 저장하고 있는 ViewHolder 클래스
    inner class VH constructor(itemView:View) : ViewHolder(itemView){
        //자식뷰 참조변수를 모두 만들지 말고.. 바인딩 객체 참조변수 1개만 보유
        lateinit var binding: RecyclerItemBinding
        init {
            //이미 뷰객체가 만들어져 있다면..그냥 바인딩 객체와 연결만 하면...자식뷰 참조값을 모두 보유하게 됨
            binding= RecyclerItemBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return VH(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.iv.setImageResource(itemList[position].imgId)
        holder.binding.tv.text= itemList[position].title
    }

}