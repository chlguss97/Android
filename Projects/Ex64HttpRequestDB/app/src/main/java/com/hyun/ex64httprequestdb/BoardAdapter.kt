package com.hyun.ex64httprequestdb

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hyun.ex64httprequestdb.databinding.RecyclerItemBinding

class BoardAdapter (val context: Context,  val itemList: List<Item>) : Adapter<BoardAdapter.VH>(){

    inner class VH(val binding:RecyclerItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater= LayoutInflater.from(context)
        val binding: RecyclerItemBinding= RecyclerItemBinding.inflate(layoutInflater,parent,false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvName.text= itemList[position].name
        holder.binding.tvMsg.text= itemList[position].msg
        holder.binding.tvDate.text= itemList[position].date

    }

}
