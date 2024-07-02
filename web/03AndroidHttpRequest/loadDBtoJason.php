<?php
    header('Content-Type:text/plain; charset=utf-8');

    //DB 데이터 읽어오기
    $db= mysqli_connect("localhost","qwer2024","a1s2d3f4!","qwer2024");
    mysqli_query($db, "set names utf8");

    $sql= "SELECT * FROM board2";
    $result= mysqli_query($db, $sql);
    // $result는 검색조건에 따른 결과표 객체.

    // 총 레코드(row:한줄)의 개수
    $rowNum= mysqli_num_rows($result);

    // 그 row개수만큼 한줄씩 데이터를 배열로 읽어와서 echo
    $rows= array(); //빈 배열 준비
    for($i=0; $i<$rowNum; $i++){
        $row= mysqli_fetch_array($result, MYSQLI_ASSOC); //연관배열로 한줄 읽어오기
        $rows[$i]= $row;   
    }

    //$row는 연관배열을 json문자열로 변환하는 기능함수가 php에 존재함
    echo json_encode($rows); 

    mysqli_close($db);
?>