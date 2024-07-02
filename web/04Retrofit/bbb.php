<?php
    header('Content-Type:application/json; charset=utf-8');

    //@Body로 보낸 json문자열을 $_POST라는 배열에 자동 저장되지 않음.
    // json으로 넘어온 데이터는 별도의 임시공간[php://input]에 파일로 보관되어 있음.
    // 그 파일을 읽어서 $_POST라는 배열변수에 대입하기
    $data= file_get_contents('php://input');

    // $data가 json문자열임.
    $_POST= json_decode($data, true); //json --> 연관배열 

    // 연관배열에 있는 개별 데이터 얻기
    $name= $_POST['name'];
    $message= $_POST['msg'];

    // android 로 응답... json문자열로..
    $aaa= array(); //빈 배열
    $aaa['name']= $name;
    $aaa['msg']= $message;

    echo json_encode($aaa);  //연관배열 --> json string
?>