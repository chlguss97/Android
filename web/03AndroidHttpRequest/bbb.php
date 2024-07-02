<?php

    header('Content-Type:text/plain; charset=utf-8');

    //Android로 부터 POST방식으로 보내온 데이터 받기
    $title = $_POST['title'];
    $message = $_POST['msg'];

    //잘 받았는지 Android로 다시 응답(echo) 해주기
    echo "$title : $message ";
    
    


?>