package com.hyun.ex45kotlinehello


//보조 생성자를 사용하여 상속받아보기...
class Professor : Person{

    var subject:String? = null //nullable variable

    //보조 생성자에서 부모클래스의 생성자를 명시적으로 호출.. super
    constructor(name:String, age:Int, subject:String): super(name,age){
        //멤버변수에 매개변수의 값을 대입
        this.subject= subject
        println("creat Professor instance")
    }

    override fun show() {
        //super.show()
        println("이름: $name  나이: $age  연구과제: $subject")
    }

}