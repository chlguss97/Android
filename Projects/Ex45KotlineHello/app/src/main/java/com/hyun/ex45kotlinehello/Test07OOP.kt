package com.hyun.ex45kotlinehello

fun main(){
    // 1. 이너클래스
    //var obj:AAA.BBB= AAA.BBB() //error - 이너클래스는 아웃터객체만이 만들 수 있음.

    // 2. 추상클래스 - 객체를 생성할 수 없는 class ~~ 상속용 클래스
    //var ani:Animal = Animal() //error
    var anis:Array<Animal> = arrayOf(Dog(), Cat(), Dog())
    anis[0].say()
    anis[1].say()
    anis[2].say()
    println()

    // 3. 인터페이스 - 추상메소드만 가지는 목적의 class
    //val unit:Unitable= Unitable() //error
    val u1:Unitable = Marine()
    val u2:Unitable = Tank()

    u1.move()
    u1.attack()

    u2.move()
    u2.attack()


    // 4. 익명클래스 - 객체를 생성하면서 클래스를 설계하는 기법
    val u3:Unitable= object:Unitable{
        override fun move() {
            println("익명클래스의 이동")
        }

        override fun attack() {
            println("익명클래스의 공격")
        }
    }

    u3.move()
    u3.attack()
    println()

    //5. 동반 객체 [ companion object ] - Java의 static 키워드를 대체함
    //println(Sample.a) //error
    println(Sample.title) //클래스명. 으로 사용
    Sample.showTitle()
    println()

    //6. 늦은 초기화 기법 - 멤버변수의 초기화를 나중에 할 수 있도록 하는 기법
    //6.1) lateinit : var 변수에 사용
    var n:Nice= Nice()
    n.onCreate()
    n.show()

    //6.2) by lazy : val 변수에 사용
    n.output()
}

//6. 늦은 초기화
class Nice{

    //6.1) lateinit
    //var name:String //ERROR - 초기화를 안하면 에러
    lateinit var name:String // 나중에 초기화 한다고 명시!!

    fun onCreate(){
        name= "sam" // 이때 초기화를 진행
    }

    fun show(){
        println("name: $name")
    }

    // * lateinit 사용의 주의할 점 **
    // var 에만 사용이 가능함
    //lateinit val age:Int //error
    // 기초타입(Boolean ~ Double 8개)에는 사용할 수 없음
    //lateinit var age:Int //error

    //6.2) by lazy - 기초 타입에서 사용이 가능함
    val age:Int by lazy { 10 } //객체가 생성될때 10이 대입되지 않고..age가 처음 사용될 때 {}영역의 값이 대입됨

    fun output(){
        println("age: $age") //이 때 위 age변수의 값이 10으로 초기화됨
    }

    // var 변수에는 사용불가
    //var address:String by lazy { "seoul"}  //error


}


//5. 동반 객체 - 객체를 생성하지 않고 사용하는 멤버와 비슷한 기능
class Sample{
    var a:Int= 10  //인스턴스 변수 - 객체를 생성하지 않으면 사용이 불가능한 변수

    //이 Sample 클래스 설계도면에 이미 만들어져 있는 객체
    companion object{
        var title:String= "Good"

        fun showTitle(){
            println("제목: $title")
        }
    }
}


//3. 인터페이스 - 추상메소드만 보유하는 목적의 class
interface Unitable{
    //유닛이라면 가져야할 기능들의 이름만 정해놓는 설계도면
    fun move()   // 추상메소드 - abstract 키워드는 생략해도 됨
    fun attack() // 추상메소드
}

//인터페이스를 구현하는 클래스를 설계
class Marine : Unitable{
    override fun move() {
        println("걸어서 이동")
    }

    override fun attack() {
        println("총알발사!!")
    }
}

class Tank : Unitable{
    override fun move() {
        println("바퀴로 이동")
    }

    override fun attack() {
        println("자주포 발사")
    }
}

//2. 추상클래스 - abstract 키워드는 상속을 목적으로 하는 만큼 open 키워드의 의미도 포함함
abstract class Animal{
    var a:Int=10
    abstract fun say() //추상 메소드
}

class Dog : Animal(){
    override fun say(){
        println("멍멍!")
    }
}

class Cat : Animal(){
    override fun say(){
        println("야옹")
    }
}

//1. 클래스안에 inner class 만들어보기
class AAA{
    var a:Int= 0
    fun show(){
        println("AAA class show..")
        println()

        //inner class 객체 생성
        var obj:BBB= BBB()
        obj.show()
    }

    //이너 클래스 - 특징. 1.아웃터클래스의 멤버를 내것인양 사용이 가능함, 2. 이너클래스는 아웃터클래스에서만 객체생성이 가능함
    //자바와 다르게 inner class가 되려면..명시적으로 inner 키워드를 붙여야 함
    inner class BBB{
        var b:Int= 10
        fun show(){
            println("BBB class show..")
            println("아웃터 클래스의 a변수 : $a")
        }
    }

}