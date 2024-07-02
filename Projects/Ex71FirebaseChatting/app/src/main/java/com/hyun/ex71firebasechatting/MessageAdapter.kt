package com.hyun.ex71firebasechatting

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.hyun.ex71firebasechatting.databinding.MyMessageboxBinding
import com.hyun.ex71firebasechatting.databinding.OtherMessageboxBinding

class MessageAdapter(val context: Context,val messageItem: List<MessageItem>): Adapter<ViewHolder>() {

    // 아이템 뷰 시안 디자인  xml 문서가 2종류이기에 VH도 2종류가 필요
    inner class VH1(val binding: MyMessageboxBinding) : ViewHolder(binding.root)
    inner class VH2(val binding: OtherMessageboxBinding) : ViewHolder(binding.root)

    // 리사이클러의 아이템뷰 모양을 경우에 따라 다르게 구현하고 싶다면.. viewTyoe을 이용해야합니다.
    // 현재 번째 아이템의 뷰 타입을 결정하기 위한 콜백메소드가 존재함.
    override fun getItemViewType(position: Int): Int {
        return if( G.nickname == messageItem[position].nickname) 0 else 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(context)

        return if(viewType==0) VH1( MyMessageboxBinding.inflate(layoutInflater,parent,false))
        else VH2(OtherMessageboxBinding.inflate(layoutInflater,parent,false))

    }

    override fun getItemCount(): Int {
        return  messageItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: MessageItem = messageItem[position]

        // holder가 VH1 or VH2
        if( item.nickname== G.nickname){
            val vh= holder as VH1
            vh.binding.tvName.text= item.nickname
            vh.binding.tvMsg.text= item.message
            vh.binding.tvTime.text= item.time
            Glide.with(context).load(item.profileUrl).into(vh.binding.iv)

        }else{
            val vh= holder as VH2
            vh.binding.tvName.text= item.nickname
            vh.binding.tvMsg.text= item.message
            vh.binding.tvTime.text= item.time
            Glide.with(context).load(item.profileUrl).into(vh.binding.iv)

        }
    }

}