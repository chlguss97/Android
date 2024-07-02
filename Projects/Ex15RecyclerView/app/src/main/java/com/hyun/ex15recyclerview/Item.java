package com.hyun.ex15recyclerview;

public class Item {
    String name;  //이름
    String msg;   //메세지

    //생성자 메소드 - new 할때 자동으로 호출되는 콜백 메소드
    public Item(String name, String msg){
        //매개변수로 받은 값을 멤버변수에 대입
        this.name= name;
        this.msg= msg;
    }

}
