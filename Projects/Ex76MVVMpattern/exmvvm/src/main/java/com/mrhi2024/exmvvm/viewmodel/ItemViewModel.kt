package com.mrhi2024.exmvvm.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import com.mrhi2024.exmvvm.model.Item
import com.mrhi2024.exmvvm.model.ItemModel

class ItemViewModel constructor(context:Context) {

    // view 와 연결할 model 역할 클래스 객체 참조변수
    var itemModel: ItemModel = ItemModel(context)

    // 값변경이 관찰되는 멤버변수
    var model: ObservableField<Item> = ObservableField()

    init {
        model.set(Item("",""))
    }

    // view의 editText의 글씨가 변경될때마다 그 값을 저장하는 변수
    private var name:String = ""
    private var email:String= ""

    fun changeName(name:String){
        this.name= name
    }

    fun changeEmail(email:String){
        this.email= email
    }


    // view의  버튼 클릭이벤트 2개 -----------------
    fun clickedSave(){
        itemModel.saveData(name, email)
    }

    fun clickedLoad(){
        val item= itemModel.loadData()
        model.set(item)
    }


}