<?php

    //이 php에서 응답하는 결과콘텐츠가 어떤 형식인지 지정. 한글깨짐 방지
    header("Content-Type:text/html; charset=utf-8"); 

    //index.html 이 보낸 데이터 받기
    //사용자가 GET방식으로 보낸 값들을 $_GET[] 이라는 슈퍼전역변수에 저장됨
    $title= $_GET['title'];
    $message=$_GET['msg'];

    //잘 받았는지 확인하기 이해 출력..응답response
    echo "제목: $title <br>";
    echo "메세지: $message";




?>