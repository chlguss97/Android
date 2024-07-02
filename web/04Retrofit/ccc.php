<?php
    header('Content-Type:application/json; charset=utf-8');

    $name= $_POST['name'];
    $message= $_POST['msg'];

    $aaa= array();
    $aaa['name']= $name;
    $aaa['msg']= $message;

    echo json_encode($aaa);
?>