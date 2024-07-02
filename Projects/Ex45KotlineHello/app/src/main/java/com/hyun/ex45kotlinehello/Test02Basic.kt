package com.hyun.ex45kotlinehello

// 프로그램의 시작은 main 함수

fun main(){
    //3. 연산자 - Java와 거의 같음.
    // 산술, 비교, 논리, 증감, 복합대입 연산자들은 모두 같음


    // 다른점만 수업
    println( 10+3.14 ) // 숫자타입 간에는 자동 형변환 됨. [ 작은 것 --> 큰것 ]

    // 숫자타입이 아닌것과의 산술연산은 자동 형변환 안됨.
    //println(10+ "aaa") //ERROR
    //println(10+true) //ERROR
    //println(10+'A') //ERROR - 한문자를 ASCII 번호로 보지 않음

    // 새로운 연산자 is ~ 자료형을 체크해주는 연산자 [ Java언어의 instanceof 와 비슷한 연산자]
    var obj:Any

    obj=10.5
    if(obj is Int) println("${obj}는 Int형입니다")
    if(obj is Double) println("${obj}는 Double형입니다")

    // 개발자가 설계한 class 객체인지를 체크할 때도 활용
    class Person{
        var name:String="sam"
        var age:Int=20
    }

    //Person객체 생성 및 참조변수로 참조하기
    //Person p = new Person();
    var p:Person = Person()
    println(p.name+","+p.age)

    var p2:Any= Person() //업 캐스팅
    if(p2 is Person){
    println("${p2.name} , ${p2.age}") // 명시적 형변환 없이도 자식 고유 멤버 사용 가능함
    }

    // 비트 연산자의 기호는 없어졌음.
    //println( 7|3 ) //ERROR
    println(7 or 3)
    //println(7 & 3) //ERROR
    println(7 and 3)

    //4. 제어문..
    // 코틀린의 제어문법: if, when, while, for

    //4.1 if문과 삼항연산자의 결합같은 느낌
    //var str:String = (10>5)?"Hello" : "Nice" //ERROR - 삼항연산자가 없음
    var str:String = if(10>5) "Hello" else "Nice"
    // if문의 결과를 대입할 수 있다는 특징이 있음..
    // 그래서 코틀린에서는 if제어문 이라고 부르지 않고 [if표현식] 이라고 부름

    // 혹시  if문의 실행문이 2개 이상이면.. {}가 필요함
    str= if(10>5){
        "aaaa"
        "zzzz"// 마지막 값이 결과 값이 됨
    }else{
        "bbbb"
        "xxxx"
        println("거짓 영역")
        "mmmm"
    }
    println(str)

    var k:Int = if(10>5){
        println("aaa")
        println("111")
        10
    }else{
        println("bbb")
        println("222")
        20
    }
    println()

    //4.2 swich 문법이 없어지고 when 문법이 이를 대체함.
    var h:Any? = null

    var n2:Int= 30
    h=100
    //switch //ERROR

    when(h) {
        10-> println("aaa")
        20-> println("bbb")
        "Hello" -> println("문자열이네..")
        //true -> println("논리값!") //error
        n2 -> println("n2와 같을때")
        40, 50 -> println(" 40 or 50 인 값 ")
        else ->{
            println(" 위 조건에 해당 안됨 ")
            println(" 여러줄 하려면 중괄호")
        }
    }
    println()

    //when도 if 와 마찬가지로 표현식임. 그렇기에 마지막 값을 대입할 수 있음.
    h=200
    var result:String= when(h){
        10->"Hello"
        20->"Nice"
        else -> "BAD"
    }
    println(result)

    // when의 조건에 is 연산자 사용가능함
    when(h){
        is Int -> println("Int형 타입입니다")
        is Double-> println("Double 타입입니다")
    }

    // when을 특정 수식으로 제어하고 싶을때.. : when()에서 () 생략해야함
    h=90
    when{
        //h>=90 && h<=100 -> println("A학점")
        h in 90..100 -> println("A학점")
        h>=80 -> println("B학점")
        h>=70 -> println("C학점")
        h>=60 -> println("D학점")
        else -> println("F학점")
    }

    println()

    //4.3 while 문은 똑같음.. 그래서 별도 수업 없음..

    //4.4 for 문법은 완전히 다르게 사용함.
    //for(var i=0; i<5; i++){} //ERROR

    // 0부터 4까지 5번 실행되는 반복문
    for(i in 0..4){
        println(i)
    }
    println()


    for(i in 3..10){
        println(i)
    }
    println()

    //제어변수의 이름을 마음대로.. 사용가능
    for(a in -5..5){
        println(a)
    }
    println()

    //실제 반복문의 사용은 배열의 개수만큼.. 하는 경우가 많음.
    //int[] aaa= new int[]{10,20,30}
    var aaa= arrayOf(10,20,30)// 배열만들기

    //for(var i=0; 1<aaa.size; i++){}
    //for(i in 0..aaa.size){ //0 ~ 4
        //println(aaa[i])
    //}
    //println()

    //반복문의 마지막 숫자는 제외
    for(i in 0 until 5){ //0~4
        println(i)}

    println()

    for(i in 0 until aaa.size){ //0~4
        println(aaa[i])}

    println()

    //2씩 증가하기
    for(i in 0..10 step 2){
        println(i)
    }
    println()

    // 값의 감소
    for(i in 9 downTo 0 ){
        println(i)}

    println()

    for(i in 9 downTo 0 step 2) println(i)


    // ** break 기타 제어문 사용할때 특이점
    for(n in 0..9){
        if(n==3) break
        print("$n   ")
    }
    println()

    for(y in 0..5){
        print("$y : ")
        for( x in 0..10){
            if(x==6) break //안쪽 반복문만 멈춤
            print("$x   ")
        }

        println()
    }
    println()


    //Label
    KKK@ for(y in 0..5){
        print("$y : ")
        for( x in 0..10){
            if(x==6) break@KKK //멈출 대상을 지정할 수 있음 Label 문법을 이용
            print("$x   ")
        }

        println()
    }
    println()


}
