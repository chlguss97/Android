<?php
    header('Content-Type:text/plain; charset=utf-8');

    // "img1" 이라는 식별자로 보내진 파일의 정보들 받기
    $file= $_FILES['img1'];

    // 파일정보 중에서 필요한 것만 얻어오기
    $srcName= $file['name'];     //원본파일명
    $size= $file['size'];        //파일사이즈
    $type= $file['type'];        //파일타입
    $tmpName= $file['tmp_name']; //임시저장소의 파일위치

    // 전달이 잘 되었는지 확인..
    echo "파일명 : $srcName\n";
    echo "사이즈 : $size\n";
    echo "타입 : $type\n";
    echo "임시저장소 : $tmpName\n";

    // 실제 파일데이터는 임시저장소에 있기에 영구적으로 서버에 저장하려면 이동해야 함
    $dstName= "./upload/IMG_" . date('YmdHis') . $srcName;
    $result= move_uploaded_file($tmpName, $dstName);
    if($result) echo "move success";
    else echo "move fail";

?>