<?php
    header('Content-Type:text/plain; charset=utf-8');

    //사용자로부터 받은 데이터
    $name= $_POST['name'];
    $message= $_POST['msg'];
    $now= date('Y-m-d H:i:s'); //"2024-02-26 11:20:34"

    // 사용자 데이터를 DB의 board2테이블 저장
    //1. DB서버에 접속
    $db= mysqli_connect("localhost","qwer2024","a1s2d3f4!","qwer2024");//DB서버주소, DB접속 아이디, DB접속 비밀번호, DB명

    //2. 한글깨짐 방지 쿼리문 실행
    mysqli_query($db,"set names utf8");

    //3. 원하는 쿼리문 작성
    $sql= "INSERT INTO board2(name, msg, date) VALUES('$name','$message','$now')";

    //4. 쿼리문 실행
    $result= mysqli_query($db, $sql);

    if($result) echo "insert success : $name";
    else echo "insert fail : $name";

    //5. DB연결 닫기
    mysqli_close($db);

?>