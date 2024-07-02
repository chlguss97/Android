package com.mrhi2024.mvp.presenter

import com.mrhi2024.mvp.model.ItemModel
import com.mrhi2024.mvp.view.MainActivity

class MainPresenter : MainContract.Presenter {

    var view: MainContract.View?= null
    var model: ItemModel? = null

    // 멤버변수들의 초기화를 수행하는 메소드
    fun initial(view: MainContract.View){
        this.view= view
        model= ItemModel(view.getContext())
    }


    override fun clickedSave(name:String, email:String) {
        model?.saveData(name, email)
    }

    override fun clickedLoad() {
        val item= model?.loadData()
        item?.let { view?.showData(it) }
    }


}