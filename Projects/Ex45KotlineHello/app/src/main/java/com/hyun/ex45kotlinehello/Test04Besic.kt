package com.hyun.ex45kotlinehello

fun main(){
    //6. 함수 Funtion - 기능 : 코드가 써있는 영역.. 언제든 호출하여 재사용 가능한 코드 영역
    //6.1 함수 호출
    show()
    //6.2 파라미터 전달 함수 호출
    output(10,"Hello")
    //6.3 리턴값이 있는 함수 호출 및 리탄된값 받기
    var n= sum(5,3)
    print("sum함수의 결과: $n")
    println()
    //6.4 명시적인 return이 없는 함수도 리턴타입은 존재함
    var x = display()
    println(x)
    println()
    //------------------------------------------------------------------------

    //6.5.1
    val data = getData()
    println(data)
    //6.5.2
    val data2= getData2()
    println(data2)
    //6.5.3
    val data3= getData3(15)
    println(data3)
    //6.5.4
    val data4= getData4(5)
    println(data4)
    println()
    //------------------------------------------------------
    //6.6.1
    aaa()
    //6.6.2 익명함수를 가지고 있는 변수명이 함수명을 대체함
    bbb()
    //6.6.3
    ccc()
    //6.6.4
    ddd()
    //6.6.5
    eee()
    //6.6.6
    fff("android")
    //6.6.7
    ggg("ios")
    //6.6.8
    hhh("web")
    //6.6.9
    iii("Nice")
    //6.6.10
    jjj("sam", 20)

    //6.6.11
    println(kkk()) //6.6.11 .. 함수 호출후 리턴된 값을 곧바로 출력
    //6.6.12
    println(lll())
    //6.6.13
    println(mmm())
    //6.6.14
    val num= nnn(5,3)
    println(num)

    // 익명함수는 '고차함수'로 이용할 때 많이 사용함
    // 고차함수: 함수 파라미터로 다른 함수를 받을 수 있는 함수를 고차함수라고 부름
    // 이를 이해하려면.. 변수가 함수를 저장할 수 있다는 것을 알아야하고 ..
    // 변수의 값을 다른 변수에 대입할 수 있다는 것도 알아야함.
    sss()
    ttt()
    uuu(100, sss)
    uuu(200, fun(){
        println("익명함수를 파라미터로 전달...")
    })
    uuu(300,{ println("스파르타") })

    //6.8
    xxx(10,20)
    xxx() //전달안받으면 기본값
    xxx(b=30)// a값은 안주고.. b값만 주고 싶어.. [파라미터 대상을 지정할 수 있음]
    xxx(b=50,a=20) //순서를 바꿔서 전달도 가능함.

}// main function....

//6.8 함수 파라미터의 default 값
fun xxx(a:Int=1000, b:Int=2000){
    println("a: $a  b:$b")
}

//6.7 고차함수
var uuu= fun(a:Int,b:()->Unit){
    println("a: $a")
    b()//전달 받은 함수를 파라미터명으로 호출
}

var sss= fun() {
    println("sss")
}

var ttt= sss // sss가 저장하고 있는 익명함수를 ttt변수에 복사해줌

//6.6 익명함수 -- 아마 조금 어려울 수 있음.

//6.6.14 파라미터와 리턴타입이 모두 있는경우
val nnn:(Int, Int) -> Int ={
    a,b-> a+b
}

//6.6.13 혹시 여러줄이면? -- 마지막 값이 리턴값
val mmm:() ->Int ={
    100
    println("mmm")
    200

}

//6.6.12 아래 익명함수를 축약형으로.. return 키워드도 생략해야만 함
val lll={
     20
}

//6.6.11 리턴타입이 있는 익명함수
val kkk= fun():Int{
    return 10
}

//6.6.10 파라미터가 여러개 이면..
val jjj:(String, Int) -> Unit ={
    name, age -> println("이름: $name  나이: $age")
}

//6.6.9 축약형의 자동 파라미터 이름인 it 이 맘에 안들면..
val iii:(String)->Unit= {
    s -> println("글자수 : ${s.length}")
}


//6.6.8 익명 함수 축약형에 자료형을 명시하기
val hhh: (String)->Unit ={ //자동으로 만들어지는 it 참조변수가 파라미터임
    println("글자수 : ${it.length}")

}

//6.6.7 익명함수 축약형
val ggg= {
    s:String-> println("글자수 : ${s.length}")
}

//6.6.6 파라미터를 전달받는 익명함수
val fff= fun(s:String){
    println("글자수: ${s.length}")
}

//6.6.5 이래 축약형의 변수 자료형을 자동 추론..
val eee= { println("eee") }

//6.6.4 익명함수를 축약형으로..
val ddd:() ->Unit ={
    println("ddd")
}

//6.6.3 익명함수를 가지는 변수의 자료형은? 즉, 함수의 자료형..은 람다표기법 활용 [ (파라미터) -> 리턴타입 ]
var ccc:()->Unit = fun(){
    println("ccc")
}

//6.6.2 익명함수 - 이름이 없는 함수
//fun(){} //error [당연히 이름이 없기에 에러]
//이름 없는 함수를 변수에 대입해 놓으면.. 변수가 함수를 가지고 있음
var bbb= fun(){
    println("bbb")
}

//6.6.1 기본적인 함수
fun aaa(){
    println("aaa")
}

//6.5 코틀린에서 함수 정의의 특이한 점.. 함수선언의 단순화

//6.5.4 아래 함수를 단순화..
fun getData4(num:Int):String = if(num<10) "good" else "bad"

//6.5.3 좀 더 복잡한 리턴 코드가 있는 함수 선언
fun getData3(num:Int):String{
    if(num<10) return "good"
    else return "bad"
}


//6.5.2 return 갑슬 할당연산자로 바꾸어 함수 선언
fun getData2()= "Hello"

//6.5.1 기본적인 return 있는 함수
fun getData():String{
    return "hello"
}

//6.4 리탄값이 없는 함수도 리턴타입은 존재함 : Unit 타입
fun display(){
    println("display~~~")
}


//6.3 리턴값이 있는 함수 - 리턴타입을 명시하는 위치가 다름 .. 함수명 옆에 : 하고 자료형
fun sum(a:Int,b:Int):Int{
    return a+b
}



//6.2 파라미터를 받는 함수 - 매개변수를 지정할 때 var, val 키워드 사용안함.
    fun output(a:Int, b:String){
        println(a)
        println(b)
    }


//6.1 함수를 정의하는 문법 fun 키워드
  fun  show(){
    println("show function")
    println()

}