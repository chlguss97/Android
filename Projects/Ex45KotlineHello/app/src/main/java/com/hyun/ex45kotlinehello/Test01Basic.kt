package com.hyun.ex45kotlinehello

// 코틀린 언어 기초문법

// 문법적 주요 특징
// A. 문장의 끝을 나타내는 ; 을 사용하지 않음. 써도 에러는 아니지만 무시됨.
// B. 변수를 만들 때 자료형을 먼저 명시하지 않고 var, val 키워드를 사용함.
// C. new 키워드 없이 객체를 생성함. new String --> String()
// D. 안전하게 null을 다룰 수 있는 문법을 제공함.
// E. 코틀란은 함수형 언어임. 즉, 함수를 객체처럼 생각해서 변수에 저장하고 파라미터로 넘겨주는 등의 작업이 가능함.

// #. 프로그램의 시작함수인 main함수가 반드시 있어야 함.
// #. 함수를 정의할 때 리턴타입 위치에 'fun' 키워드(funtion:함수) 사용

fun main(){

    // *. .kt 코트린 파일만 별도로 실행하려면 마우스 우클릭으로 해당파일 run 메뉴를 실행 - 결과는 하단 [run] 탭에서 실행됨.

    //1. 화면(console) 출력 : print(), println() 함수
    print(10)
    print(3.14)
    print("Nice")

    //자동 줄바꿈
    println()
    println("Hello kotlin")
    println(10)
    println(3.14)
    println('G')
    println(true)
    println(5+3)
    println("5+3")

    //변수명을 전달하면 변수안에 있는 값이 출력
    var a:Int = 10
    println(a)
    var b:String = "Hello"
    println(b)
    println()
    //////////////////////////////////////////////////////////////////////////////////////////////


    //2.자료형과 변수
    // * 코틀린 자료형의 종류
    // 1) 기초 타입 : Boolean, Byte, Char, Short, Int, Long, Float, Double [ 기본적으로 코틀린은 모든 변수가 참조형임.]
    // 2) 참조 타입 : String, Any(Java의 Object와 비슷), Unit ... 그외 Kotlin APIs, java APIs

    //* 변수의 2가지 종류 : var, val [ 문법: var 변수명:자료형, val 변수명:자료형 ]

    // 2.1) var [ 값 변경이 가능한 변수]
    var num:Int = 10
    println(num)

    var num2:Double = 3.14
    println(num2)

    //권장하지 않지만 지역변수는 변수를 만들때 값을 초기화 하지 않아도 됨
    var num3:Float
    num3=5.23f
    println(num3)

    // 변수이므로 변수가 가지고 있는 값을 변경할 수 있음.
    num= 20
    num2= 20.5
    num3= 10.88F
    println(num)
    println(num2)
    println(num3)

    //당연히 자료형이 고정되어 있기에 다른 자료형의 값을 대입할 수는 없음
    //num=3.14 //ERROR [ Int에 Double을 대입 ]
    //num2=50 // ERROR [ Double에 Int를 대입 ]

    // 다른 자료형을 강제로 대입하고 싶다면 (형변환) 연산자.. 코틀린은 이 연산자 없음
    // 대신 .toXXX() 라는 메소드를 이용함
    num=3.14.toInt()
    println(num)

    num2=50.toDouble()
    println(num2)

    // 문자열..  String 객체
    var s:String= "Hello"
    println(s)

    //var s2:String = String("Hello") //ERROR - 단순 "문자열" 객체를 생성할때는 String()생성자를 사용할 수 없음.

    // 문자열을 정수로 변환하기
    var str:String="123"
    println(str + 5)

    var m:Int = str.toInt()
    println(m + 5)

    var str2:String="5.64"
    var m2:Double = str2.toDouble()
    println(m2+ 1.24)
    println()

    // 2.2) val [ 값 변경이 불가능한 변수 - 읽기전용 변수]
    val n1:Int = 100
    //n1= 200 //error
    println(n1)

    val n2:Boolean = true
    //n2= false// error
    println(n2)
    println()
    //# var, val 키워드 사용 팁 : 데이터를 가지는 변수는 var[ex. name, age, title, ...], 객체를 참조하는 변수는 val[ex.Textview, ImageView, NotificationManager,....]

    // 2.3 자료형을 생략하며 변수 선언이 가능함 - 값을 대입하면 자동으로 자료형이 추론됨
    var aa= 10//Int
    println(aa)

    var bb=3.14 //Double
    println(bb)

    var cc= 3.14f //Float
    println(cc)

    var dd= true  // Boolean
    println(dd)

    var ee= 'A' // Char
    println(ee)

    var ff= "Hello" // String
    println(ff)

    // 주의! 대입할 때 자료형이 결정된 것임. 자료형이 없는 것이 아님. 정적 타입 언어
    //aa=3.14 //error

    //* 정수 숫자를 표현할때 특이한 표기법
    var a3:Int = 5_000_000
    println(a3)
    println()

    // 2.4 코틀린만의 자료형 타입
    // Any 타입 [ 최상위 자료형]
    var v: Any= 10
    println(v)

    v=3.14
    println(v)

    v= "Hello"
    println(v)
    println()
    // 초보자가 보기에는 만능 자료형으로 보이지만.. 자료타입을 예측학 수 없어서 자주 사용하진 않음. 업캐스팅용으로 사용함
    //** null 허용 자료형 이라는 개념. [추후. 별도 추가 수업 예정] - 대략적인 모습만
    //var ss:String= null //ERROR - 기본적인 자료형은 null 값을 가질 수 없음
    //var nn:Int= null //ERROR - 기본적인 자료형은 null 값을 가질 수 없음

    // 만약 null 값을 가질 수 있는 변수를 만들려면..
    var ss:String? = null // 자료형 뒤에 ? 접미어를 붙이면.. null일 수도 있는 변수가 됨
    var nn:Int? = null //nullable 변수라고 부름
    println(ss)
    println(nn)

    //nullable 자료형으로 만드 참조변수의 멤버값을 사용할때는..
    var sss:String?= "Hello"
    //println("글자수: "+ sss.length) //error - String?는 null일 수도 있으니.. .연사자가 null일 수도 있어서..NPE 에러가 발생할 수 있음.
    println("글자수: "+ sss?.length) //sss가 null이면 null이 출력되고.. 그렇지 않으면 length가 출력됨.
    println()

/////////////////////////////////////////////////////////////////////////////////////

    //** 화면 출력의 format 만들기 ***************************
    println("Hello"+ "Kotlin")

    //숫자와 문자열을 더하면?
    //println(10+"Hello") // ERROR -- Int를 자동으로 String으로 변환해주지 않음.
    // 문자열이 먼저 있으면..
    println("Hello"+ 10) // 자동 형변환 해줌
    println()

    // 예를 들어.. 2개의 정수 값을 저장하는 변수들의 덧셈코드..
    var nn1:Int = 50
    var nn2:Int = 30

    // 덧셈계산을 출력하는 코드
    //println(nn1 +"+"+ nn2+"="+(nn1+nn2)) //숫자가 첫번째에 있으면 문자열이 자동으
    //println(nn1.toString() +"+"+ nn2+"="+(nn1+nn2))
    //println(""+nn1 +"+"+ nn2+"="+(nn1+nn2))

    // 위 + 결합연산자를 이용하는 방식은 작성도 짜증이고.. 가독성도 좋지 못함.
    // 이를 개선하기 위해 등장한 [문자열 템플릿] 문법
    println(" $nn1 + $nn2 = ${nn1+nn2}")

    var name="sam"
    var age=20
    println("이름: $name , 나이: $age" )

}