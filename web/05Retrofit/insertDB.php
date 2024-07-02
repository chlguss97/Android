<?php
    header('Content-Type:text/plain; charset=utf-8');

    //@PartMap으로 전달된 POST방식의 데이터들 받기
    $name= $_POST['name'];
    $title= $_POST['title'];
    $msg= $_POST['msg'];
    $price= $_POST['price'];

    //@Part로 전달된 이미지 파일정보
    $file= $_FILES['img1'];
    $srcName= $file['name'];    //원본파일명
    $tmpName= $file['tmp_name'];//임시저장소 위치

    //이미지 파일을 영구적으로 저장하기 위해 임시저장소에서 이동..
    $dstName= "./upload/IMG_" . date('YmdHis') . $srcName;
    move_uploaded_file($tmpName, $dstName);

    //게시글 작성 시간
    $now= date('Y-m-d H:i:s');

    //제목이나 메세지 문자열 중에 특수문자가 포함되어 있을 수도 있음.
    //특수문자 앞에 슬래시를 붙이기
    $title= addslashes($title);
    $msg= addslashes($msg);


    // 데이터들을 MySQL DB에 저장하기 [테이블명 : market]
    $db= mysqli_connect("localhost","qwer2024","a1s2d3f4!","qwer2024");
    mysqli_query($db,"set names utf8");

    // 데이터들 market테이블에 삽입하기 [$name, $title, $msg, $price, $dstName, $now]
    $sql= "INSERT INTO market(name, title, msg, price, file, date) VALUES('$name','$title','$msg','$price','$dstName','$now')";
    $result= mysqli_query($db, $sql);

    if($result) echo "게시글 업로드 되었습니다.";
    else echo "게시글 업로드에 실패했습니다. 다시 시도해 주세요.";

    mysqli_close($db);
?>