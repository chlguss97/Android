package com.hyun.ex46kotlinrecyclerview

// 데이터 3개를 저장하는 용도의 클래스 - 일반 class와 다르게 .equals()를 사용하면 객체 주소가 아니라 값들을 비교해줌
data class Item constructor(var title:String, var message:String, var imgId:Int) //{}안에 쓸게 없으면 .. 생략 가능

