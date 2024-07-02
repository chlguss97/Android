<?php
    header('Content-Type:text/html; charset=utf-8');

    //사용자로부터 전달된 글씨데이터와 파일 데이터 받기
    $title= $_POST['title'];
    $message= $_POST['msg'];

    $file= $_FILES['img1'];
    //파일의 세부정보 중 사용할 것만..
    $fileName= $file['name'];     //원본파일명
    $tmpName= $file['tmp_name'];  //임시저장된 파일 위치

    //영구히 저장될 파일의 위치와 파일명
    $dstName= "./uploaded/" . date('YmdHis') . $fileName;

    //임시저장소($tmpName)의 파일을 원하는 위치($dstName)로 이동
    $moveResult= move_uploaded_file($tmpName, $dstName);
    if($moveResult) echo "upload success<br>";
    else echo "upload fail<br>";

    //전송된 데이터와 파일정보가 올바른지 확인
    // echo $title . "<br>";
    // echo $message . "<br>";
    // echo $fileName . "<br>";
    // echo $tmpName . "<br>";
    // echo $dstName . "<br>";

    // 받은 데이터들을 서버에 체계적으로 저장하기 위해 Database를 이용
    // dothome 호스팅 업체는 MySQL DBMS가 이미 설치되어 있음.
    // 그리고 php언어는 MySQL 작업은 편하게 할 수 있는 기능함수를 이미 보유한 상태임
    
    // MySQL DB에 접속하기 - 접속에 성공하면 DBMS를 제어하는 객체를 리턴해 줌
    $db= mysqli_connect('localhost','qwer2024','a1s2d3f4!','qwer2024'); //DB서버주소,DB접속아이디,DB접속비밀번호,DB명

    // DB안에 한글데이터 깨지지 않도록 하는 쿼리문..
    mysqli_query($db, "set names utf8");

    // 게시글 저장 날짜..
    $now= date('Y-m-d H:i:s');
    // 사용자로부터 전달받은 데이터들($title, $message, $dstName, $now)
    // Database에 값을 insert 해주는 SQL 쿼리문 작성
    $sql= "INSERT INTO board(title, msg, image, date) VALUES('$title','$message','$dstName','$now')";
    $result= mysqli_query($db, $sql); //요청의 결과를 true/false로 리턴해줌
    if($result) echo "insert success";
    else echo "insert fail";

    // DBMS와의 연결을 닫기
    mysqli_close($db);


?>