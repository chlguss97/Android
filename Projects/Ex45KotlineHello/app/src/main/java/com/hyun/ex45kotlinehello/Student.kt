package com.hyun.ex45kotlinehello

// Person 클래스를 상속했기에 .. 이미 name ,age 멤버변수를 보유한 상태임
// 그러니  Student 클래스의 주 생성자에서는 매개변수로만 name, age값을 받아야함
open class Student constructor(name:String, age:Int, var major:String ) : Person(name, age){
    init {
        println("create Student instance")
    }


    // override 키워드는 open 키워드의 능력이 포함되어 있음
    override fun show(){
        //super.show()
        println("이름: $name 나이: $age  전공: $major")
    }


}