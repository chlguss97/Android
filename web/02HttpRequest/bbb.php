<?php
    header("Content-Type:text/html; charset=utf-8");

    //사용자가 POST방식으로 전달한 데이터들은 $_POST[] 이라는 슈퍼전역변수에 전달됨
    $id= $_POST['id'];
    $password= $_POST['pw'];

    //사용자[web browser]에게 출력(응답:Response)
    echo "<h3>$id</h3>";
    echo "<h5>$password</h5>";

?>