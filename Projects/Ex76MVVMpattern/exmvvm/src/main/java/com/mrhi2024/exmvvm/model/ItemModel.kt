package com.mrhi2024.exmvvm.model

import android.content.Context

// 데이터를 제어하는 기능을 가진 클래스
class ItemModel constructor(val context:Context){

    //1] 데이터를 전달받아 SharedPreferences 에 저장하는 기능
    fun saveData(name:String, email:String){
        val pref= context.getSharedPreferences("data", Context.MODE_PRIVATE)
        pref.edit().apply{
            putString("name", name)
            putString("email", email)
            commit()
        }
    }

    //2] SharedPreferences 에서 데이터를 읽어와서 내보내는 기능
    fun loadData():Item{
        val pref= context.getSharedPreferences("data", Context.MODE_PRIVATE)
        var name= pref.getString("name", "") as String
        var email= pref.getString("email", "")!!

        return Item(name, email)
    }

}