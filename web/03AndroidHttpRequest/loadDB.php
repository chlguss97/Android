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
    for($i=0; $i<$rowNum; $i++){
        $row= mysqli_fetch_array($result, MYSQLI_ASSOC); //연관배열로 한줄 읽어오기
        
        echo $row['no'] . "," . $row['name'] . ",". $row['msg'] . "," . $row['date'] . "&";
    }

    mysqli_close($db);
?>