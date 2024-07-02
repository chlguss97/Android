package com.hyun.ex70firebasefirestoredb

//[파마미터의 디폴트값]이라는 기술을 이용 - 오버로딩 대체
data class User(var name:String="", var age:Int=0, var address:String="")

  //이렇게 파라미터 없는 생성자를 만들어야함. .하지만 지저분함
//    constructor(): this("",0,""){
//
//    }

