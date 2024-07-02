package com.hyun.ex45kotlinehello

// 프로그램의  시작점에 해당하는 특별한 함수(function)
fun main() {
    //5. 배열 Array & 컬렉션 Collection
    //5.1 요소개수의 변경이 불가능한 배열 : Array
    //var aaa:Array<Int> = arrayOf(10,20,30)
    var aaa = arrayOf(10, 20, 30) // 자동으로 자료형이 추론됨
    //요소값 출력
    println(aaa[0])
    println(aaa[1])
    println(aaa[2])
    //println(aaa[3])
    println(aaa) // 참조변수를 출력하면? 자동으로 .toString() 발동함
    println()

    //요소값 변경
    aaa[0] = 100
    println(aaa[0])
    println()

    //특이한 점
    println(aaa.get(0)) //리스트에 있던 get()메소드를 배열도 보유함
    aaa.set(1, 200) // index, value)
    println(aaa.get(1))

    //배열 요소의 개수 (길이 - 자바에서는 length)
    println("배열의 길이: ${aaa.size}")
    println()

    //반복문으로 요소값 차례로 접근하기
    for (i in 0 until aaa.size) {
        println(aaa[i])

    }
    println()


    //자바에서의 확장된 for문 - foreach 와 유사한 반복문
    for (v in aaa) { //각각의 요소 참조변수 v
        println(v)
    }
    println()

    // foreach 문법으로 index번호를 얻고 싶다면
    for (i in aaa.indices) {
        println(i)
    }
    println()


    // foreach 문법으로 index와 요소값을 모두 얻고 싶다면
    for ((index, value) in aaa.withIndex()) {
        println("${index} : ${value}")
    }
    println()


    // for문법안에 배열을 넣는게 아니라.. 배열객체 스스로 요소값에 대해 foreach 하는 기능 메소드가 존재함
    aaa.forEach {
        // 이 영역이 요소의 개수만큼 반복 수행됨 - 이 영역안에서 요소값을 it으로 자동 참조하고 있음.
        println(it)
    }
    println()

    // 자료형을 자동추론하기에 서로다른 자료형의 값을 요소로 가질 수 있음
    var bbb = arrayOf(10, "Hello", true)//자동 추론 : Array<Any>
    println(bbb[0])
    println(bbb[1])
    println(bbb[2])
    println()

    // Any타입을 쓰면 좋아보이지만.. 자료형을 모르기에 간단한 산술연산도 번거로움
    //println(bbb[0]+5) //ERROR [ Any + 5으로 인식함 ]
    println(bbb[0] as Int + 5) //코틀린의 참조타입 형변환 as 연산자
    println(bbb[1] as String + "aaa")
    println()

    // 그래서 보통 배열을 사용할때는 타입을 명시하여 같은 자료형만 저장하는 것을 권장
    var ccc = arrayOf<Int>(10, 20, 30) //다른 자료형을 넣으면 error
    //<>제네릭 표기법이 거슬린다면...
    var ddd = intArrayOf(10, 20, 30)

    // 배얄 참조변수만 먼저 만들고 나중에 배결객체를 대입하려면..
    var eee: IntArray
    eee = intArrayOf(1, 2, 3)
    // ## XxxArray는 Boolean부터 Double까지 기초타입만 존재함. 즉, StringArray도 없음.

    // 배열요소값의 시작이 null값을 가진 5칸 짜리 배열만들기
    var fff = arrayOfNulls<Double>(5)
    for (e in fff){
        println(e)
    }
        println()

    // null값을 가지는 배열을 만들때.. 명시적으로 자료형을 지정할때 고려할 점..
    var ggg:Array<Int?> = arrayOfNulls<Int>(3)





    //5.2 요소개수의 변경이 유동적인 컬렉션 : Collection [ List, Set, Map ]
    // 코틀린의 Collection 들은 모두 요소의 추가/삭제 및 값 변경이 가능한 종류와 불가능한 종류로 나뉨
    //5.2.1 요소개수 추가/삭제 및 값 변경이 불가능한 컬렉션 : listOf, setOf, mapOf
    //5.2.2 요소개수 추가/삭제 및 값 변경이 가능한 컬렉션 : mutableListOf, mutableSetOf, mutableMapOf

    //5.2.1 변경 불가능한 대량의 데이터들
    //1) List
    var list:List<Int> = listOf(10,20,30)
    for(i in 0..2 ){
        //println(list.get(i))
        println(list[i]) //배열처럼 [] 사용을 권장함
    }
    println()


    // ArrayList와 다르게 요소값 추가/삭제/변경 모두 불가능
    //list.add(100)
    //list.remove(0)
    //list.set(1,200)

    //2) Set
    val set:Set<Double> = setOf(3.14, 5.55, 2.22, 1.26, 5.55) // 중복값 허용x
    for (e in set) println(e)
    println()

    //1) Map
    //3.1) Pair() 객체를 이용한 키 - 벨류 쌍 지정
    val map:Map<String, String> = mapOf(Pair("title","Hello"), Pair("msg","nive to meet you"))
    println("요소개수: ${map.size}")
    for((key,value ) in map){
        println("$key : $value ") }

    println()


    //3.2) to 연산자를 이용한 키-벨류 쌍 지정
    val map2:Map<String, String> = mapOf("id" to "mrhi", "pw" to "1234")
    for((k,v) in map2 ) println("$k : $v")
    println()


    //5.2.2 요소개수 추가/삭제 및 변경이 가능한 mutable 컬렉션
    //1) MutableList
    val aaaa:MutableList<Int> = mutableListOf(10,20,30)
    println("요소개수 : ${aaaa.size}")
    aaaa.add(40)
    aaaa.add(0,50)// 특정 위치에 추가가능
    println("요소개수 : ${aaaa.size}")
    aaaa.set(0,100)
    aaaa[1]=200 //권장
    for (e in aaaa ) println(e)
    println()


    //2) MutableSet
    val bbbb:MutableSet<Double> = mutableSetOf<Double>()
    println("요소 개수 : ${bbbb.size}")
    bbbb.add(5.55)
    bbbb.add(3.14)
    bbbb.add(5.55)// 중복데이터를 무시
    println("요소 개수 : ${bbbb.size}")
    bbbb.forEach{ print(it) }//컬렉션의 요소값 각각을 실행하는 기능
    println()


    //1) MutableMap
    val cccc:MutableMap<String, String> = mutableMapOf("name" to "sam",Pair("title","Hello"))
    println("요소개수 : ${cccc.size}")
    cccc.put("addr", "seoul")
    println("요소개수 : ${cccc.size}")
    cccc.put("addr", "busan") //중복된 key - 값이 변경됨
    println("요소개수 : ${cccc.size}")
    for ((k,v) in cccc) println("$k : $v")
    println()

    // * 별외. Java언어의 ArrayList 사용에 대한 향수가 있다면
    val arrList:ArrayList<Any> = arrayListOf(10,"Hello", true)
    // 사용 방법이 자바와 같음
    val hashset:HashSet<Any> = hashSetOf()
    val hashMap:HashMap<String, Any> = hashMapOf()

   // -----------------------------------------------------------------------

   //5.3 2차원 배열!!
    val arrays:Array<Array<Any>> = arrayOf( arrayOf(10,20,30), arrayOf("aa","bb"), arrayOf(true,false))
    println(arrays[0][0])
    println(arrays[0][1])
    println(arrays[0][2])

    println(arrays[1][0])
    println(arrays[1][1])

    //반복문으로 접근하기
    for (array in arrays){
        for(e in array){
            print("$e  ")
        }
        println()
    }
    println()

    val array2= mutableListOf<MutableList<Int>>(mutableListOf(10,20,30), mutableListOf(100,200,300))
    println(array2.size)

    array2.add(mutableListOf(1000,2000))
    println(array2.size)
    for(list in array2){
        for(e in list) print("${e}, ")
        println()
    }
    println()

}
