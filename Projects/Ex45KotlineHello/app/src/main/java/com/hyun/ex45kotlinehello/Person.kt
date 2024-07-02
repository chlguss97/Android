package com.hyun.ex45kotlinehello

//상속해주기 위해 open 키워드
open class Person constructor(var name:String, var age:Int) {
    init {
        println("create Person instance")
    }

    // 상속 받는 클래스에서 기능 개선 허용
    open fun show(){
        println("이름: $name   age: $age")
    }

}