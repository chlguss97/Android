package com.mrhi2024.mvc.model

import android.content.Context

//데이터를 제어하는 기능 2개를 가진 클래스
class ItemModel constructor(val context:Context){

    //1] 데이터를 sharedpreferences 에 저장하는 기능 메소드
    fun saveData(name:String, email:String){
        val pref= context.getSharedPreferences("data", Context.MODE_PRIVATE)
        pref.edit().apply {
            putString("name", name)
            putString("email", email)
            commit()
        }
    }

    //2] 데이터를 불러와서 controller에게 내보내는 기능 메소드
    fun loadData():Item{
        val pref= context.getSharedPreferences("data", Context.MODE_PRIVATE)
        var name:String = pref.getString("name", "") as String
        var email:String = pref.getString("email", "")!!

        return Item(name, email)
    }
}