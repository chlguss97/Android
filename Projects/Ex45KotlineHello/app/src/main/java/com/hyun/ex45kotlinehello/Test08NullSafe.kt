package com.hyun.ex45kotlinehello

fun main(){
    // 프로그램을 개발하다 보면 가장 많이 발생하는 에러 [ NPE } - null 참조에 의한 에러.
    // null 참조로 인한 앱의 다운 현상을 코딩과정에서 방지하기 위해..
    // 명시적으로 null 을 가질 수 있는 변수와 그렇지 않은 변수를 구분하는 문법

    //var s1:String = null //error -- non nullable variable
    var s2:String? = null //ok nullable variable
    println(s2)

    //nullable 변수를 사용할 때 특이한 멤버접근 연산자들
    var s3:String= "Hello"
    var s4:String?= "Nice"

    println("글자수: "+ s3.length)
    //println("글자수: "+ s4.length) //error -- s4가 null일 수도 있어서.

    // 사용하려면.. null 이 아닌지 체크해봐야겠죠
    if(s4!=null){
        // 이 영역은 s4가 null이 아닌 것이 확실함
        println("글자수: " +s4.length)
    }

    //위 if문을 매번 사용하기 좀 번거롭고 가독성도 떨어짐
    //그래서 위 if 체크작업을 대체하는 연산자가 등장함
    //1) ?. -- null safe 연산자
    println("글자수: " + s4?.length) // null이 아니면 멤버에 접근, null이면?? 그냥 null값을 줌 .

    var s5:String?= null
    println("글자수: "+s5?.length) //null이면?? 그냥 null값을 줌 .

    //근데 혹시 객체가 null 일때 그냥 null 값을 출력하니.. 이게 좀 .. 별로
    //var lenhth:Int = s4.length //error -- Int 타입에는 null을 저장할 수 없어서
    var len:Int?= s4?.length // 해결방법 - len 변수도 nullable variable...
    // 이런식이면.. leng가 null이 되기에.. len을 사용하는 곳에서 파생적으로 문제가 발생될 수 있음.
    //for(i in 0 .. len){ //error

    // }

    //위 방법이 싫다면 .. 해결.. 만약 null이면.. 특정 다른 값으로 대체하기.. 삼항연산자와 비슷한 문법
    //2) ?: 연산자 -- 엘비스 연산자 ?: 앞에 값이 null이면 뒤에 것이 값이 됨
    var len2:Int = s4?.length ?: -1
    println("글자수: $len2")

    var len3:Int = s5?.length ?:-1
    println("글자수: $len3")

    // null 안정성이고 뭐고 .. 그냥 자바처럼 null 에러나면.. 다운되도 상관없으니
    // nullable 변수의 멤버를 그냥 사용하겠다고 주장하는 연산자
    //3) !! 연산자
    var s6:String? = "Hello"
    println("글자수: "+ s6!!.length) // 위에서 주장하면 다음부터는 그냥 사용..

    var len5:Int = s6!!.length


    //4) as? 연산자 -- 안전한 형변환
    //val m:mmm= TTT() //error -- 당연히 클래스가 다르면 참조 불가능

    //형변환 연산자 as를 이용하여 억지로 형변환 해보면?
    //val m:MMM = TTT() as MMM //error
    //println(m.a)

    // 형변환을 시도하되 혹시 불가능하면 예외대신에 null을 주도록..
    val m:MMM? = TTT() as?MMM
    println(m?.a)

}

class MMM{
    var a=10
}

class TTT{
    var a=20
}
