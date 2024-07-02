<?php
    header('Content-Type:application/json; charset=utf-8');

    $name = $_GET['name'];
    $message= $_GET['msg'];

    // 안드로이드로 응답해주기.. json으로 응답해줘야함.
    $aaa= array(); //빈 배열
    $aaa['name']= $name;   // 연관배열
    $aaa['msg']= $message; // 연관배열

    // 응답 : 연관배열 --> json 
    echo json_encode($aaa);

?>