package com.hyun.ex65jsonparsing

// [이름, 나이, 키, 주소] 정보를 저장하는 data class
data class Person(var name:String, var age:Int, var height:Double, var address:Address)

//[국적, 도시] 정보를 저장하는 data class
data class Address(var nation:String, var city:String)
