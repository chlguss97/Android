package com.hyun.ex45kotlinehello

fun main(){
    //1. 클래스 선언 및 객체 생성
    var obj:MyClass = MyClass() //new 키워드가 없음
    obj.show()

    //1.1 별도의 .kt 파일에 클래스를 선언하고 객체 생성하기
    var obj2:MyKotlinClass = MyKotlinClass()
    obj2.show()

    //2. 생성자 constructor [ 많이 당황스러울 것임 ]
    // 코틀린의 생성자는 2가지 종류가 있음 [ 주 생성자, 보조 생성자 ]
    //2.1 주 생성자
    var s:Simple = Simple()

    //2.1.2 주 생성자에 값을 전달해보기..
    //var s2:Simple = Simple(10) //오버로딩 안됨
    var s2:Simple2 = Simple2(10, 20, 30)
    s2.show()

    //2.2 보조 생성자 - 주 생성자의 오버로딩을 목적으로 만들어진 생성자
    var s3:Simple3 = Simple3()
    var s33:Simple3 = Simple3(100)

    //2.3 주생성자 + 보조생성자
    var s4:Simple4 = Simple4()
    var s44:Simple4 = Simple4(100)

    //2.4 주 생성자의 constructor 키워드 생략 가능함
    Simple5() // 참조변수 없이 객체만 생성

}//main function

//2.4 주 생성자의 constructor 키워드 생략 가능함
class Simple5(){
    init {
        println("Simple5 주 생성자!!")
    }
}

//2.3 주 생성자 + 보조 생성자(오버로딩 용)
class Simple4 constructor(){ //주 생성자
    init {
        println("Simple4 객체 생성")
    }
    //보조 생성자 - 오버로딩 용
    constructor(num:Int):this(){ // this()생성자 호출로 주 생성자를 명시적으로 호출해야 함.
        println("Simple4 보조생성자 num: $num")
    }
}



//2.2 보조 생성자
class Simple3{
    //보조 생성자
    constructor(){
        println("Simple3 객체 생성")
    }

    //보조 생성자 오버로딩
    constructor(n:Int){
        println("Simple3 객체 생성, Int")
        println("n: $n")
    }

    //보조 생성자는 주생성자처럼 멤버변수면서 파라미터를 만들 수 없음
//    constructor(var age:Int){ //error
//    }
}

//2.1.2 주 생성자에 파라미터 전달하는 실습 - 생성자의 역할~ 멤버변수의 값을 초기화하는 목적..
class Simple2 constructor(num:Int, age:Int, var num2:Int){ // 주 생성자 파라미터에 var, val 키워드를 명시하면...멤버변수면서 파라미터임.

    //멤버변수
    var age:Int= 0

    init {
        println("Simple2 객체 생성")
        println("num: $num")  // init 영역에서는 주 생성자의 파라미터를 인식함
        //멤버변수에 전달받은 파라미터를 대입
        this.age= age
        println("num2: $num2")
    }

    fun show(){
        //println("주 생성자의 파라미터 num : $num") //error
        println("클래스의 멤버변수 age : $age") //OK
        println("주 생성자의 멤버변수면서 파라미터 num2 : $num2") //OK
    }

}

//2.1 주 생성자 실습 - 주 생성자의 오버로딩.. 안됨!!
class Simple constructor(){
    //주 생성자가 호출될 때 실행될 코드를 작성하는 영역..
    init { //초기화 영역
        println("Simple 객체 생성")
        println()
    }
}



//1. 클래스 선언
class MyClass{
    //1] 멤버변수 - property 프로퍼티 --> 코틀린의 멤버변수는 반드시 초기화 해야 함
    var a:Int= 10

    //2] 멤버함수 - method 메소드
    fun show(){
        println("show : $a")
        println()
    }
}




