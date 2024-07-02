package com.hyun.ex45kotlinehello

import androidx.appcompat.app.AlertDialog

fun main(){
    // Scope function : apply, run, also, let

    // 어떤 객체의 멤버 사용을 여러개 해야 할 때 ..
    var crew:Crew = Crew()
    crew.name="sam"
    crew.age=20
    crew.address="seoul"
    crew.show()

    //멤버 4개를 사용할 떄 마다 객체명.xxx 라고 쓰는게 번거롭고.. 실수의 가능도 존재함
    //이를 위해 등장한 scope function
    val crew2:Crew = Crew()
    crew2.apply {
        //이 영역 안에서는 this 가 crew 참조변수임
        this.name="robin"
        age=25
        address="busan"
        show()
    }

    val crew3:Crew? = Crew()
    crew3?.apply {
        name="hong"
        age= 23
        address= "aa"
        show()

        // scope function 4가지 .. 실습
        // 1) {}영역안에서 객체참저를 this로 하는 것들 : apply, run
        // 1.1) {}의 리턴값이 객체 본인인 것 : apply
        val crew4:Crew= Crew()
        var crew5 =crew4.apply {
            name="kim"
            age=40
            address= "newyork"
            //별도의 return 키워드가 없지만 무조건 return this 인 것임.
        }
        crew5.show()

        // 1.2) {}의 리턴값이 마지막 실행문의 값인 것 : run
        val crew6:Crew= Crew()
        var len:Int?=crew6.run {
            name="park"
            age=45
            address= "tokyo"
            //마지막 값이 run 함수의 리턴값
            name?.length
        }
        println("이름의 글자수 : $len")

        //사용 예) 앱 개발할때 AlertDialog..



        // 2) {}영역안에서 객체참조를 it 으로 하는 것들 : also, let
        // 2.1) {}의 리턴값이 객체 본인인 것 : also
        val crew7:Crew= Crew().also {
            it.name="lee"
            it.age= 40
            it.address= "incheon"
            //무조건 crew 본인 객체 참조값이 리턴됨
        }


        // 2.2) {}의 리턴값이 마지막 실행문의 값인 것 : let
        val len2:Int = Crew().let {
            it.name="rosa"
            it.name!!.length
        }
        println()

    }

}

class Crew{
    var name:String?= null
    var age:Int? = null
    var address:String? = null

    fun show(){
        println("$name : $age , $address")
    }
}