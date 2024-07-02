<?php
    header('Content-Type:application/json; charset=utf-8');

    $db= mysqli_connect("localhost","qwer2024","a1s2d3f4!","qwer2024");
    mysqli_query($db,"set names utf8");

    $sql= "SELECT * FROM market";
    $result= mysqli_query($db, $sql);

    //결과표($result)의 총 레코드(한줄:row) 개수
    $rowNum= mysqli_num_rows($result);

    //여러줄을 읽어야 하므로 반복문..
    $rows= array();  // 빈 배열
    for($i=0; $i<$rowNum; $i++){
        $row= mysqli_fetch_array($result, MYSQLI_ASSOC); //연관배열로..
        $rows[$i]= $row;
    }

    //2차원 배열 --> json array
    echo json_encode($rows);

    mysqli_close($db);
?>