package com.hyun.ex45kotlinehello

fun main(){

    // # 상속
    var obj:Second=Second()
    obj.a=100
    obj.b=200
    obj.show()
    println("----------")

    //## 업 캐스팅, 다운 캐스팅
    var f:First = Second() // 부모--> 자식 : 업 캐스팅
    f.show() // 실제 참조하는 객체의 오버라이드 된 show가 호출됨
    //자식객체의 고유멤버를 접근 불가
    //f.b=200 //error
    //f.aaa()//error

    //그럼에도 f가 참조하는 객체의 Second기능을 사용하고자 한다면
    var s:Second = f as Second // as 연산자 - 형변환 연산자 [ 다운캐스팅 ]
    s.aaa()
    //---------------------------------------------------
    //상속 마무리 실습
    // 어떤 대학의 회원 정보를 저장.. 회원 종류가 4종류
    // 일   반 : 이름, 나이
    // 학   생 : 이름, 나이, 전공
    // 교   수 : 이름, 나이, 연구과제
    // 근로학생 : 이름, 나이, 전공, 업무

    var p:Person = Person("sam",20)
    p.show()
    println()

    var stu:Student = Student("robin", 25, "android")
    stu.show()

    var pro:Professor = Professor("kim", 50,"mobile optimization")
    pro.show()
    println()

    var alba:AlbaStudent = AlbaStudent("son", 27, "ios", "pc management")
    alba.show()
    println()

}//main function

//상속 해줄 클래스 - 부모클래스 [ 코틀린의 class는 기본적으로 final class 임 ]
//상속 해주려면 open 키워드 필요
open class First {
    var a:Int=10

    // 코틀린의 메소드는 기본적으로 final method 임.
    // 오버라이드를 허용하려면. open 키워드
    open fun show(){
        println("a: $a")
    }

}


// 상속 받을 클래스 - 자식클래스
class Second : First(){// 상속받는 클래스의 생성자를 호출한다는 표현이 명시적으로 필요함
    var b:Int=20
    //상속받은 show() 메소드 기능이 맘에 안들어.. 오버라이드
    // override 키워드가 반드시 있어야 함.
    override fun show(){
        //println("a:$a")
        //부모의 멤버변수 출력은 부모의 기능메소드를 활용
        super.show()
        println("b:$b")
    }

    //자식클래스의 고유기능메소드
    fun aaa(){
        println("Second aaa method")

    }

}