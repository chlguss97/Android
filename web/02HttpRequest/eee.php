<?php
    header('Content-Type:text/html; charset=utf-8');

    // MySQL 접속하기
    $db= mysqli_connect('localhost','qwer2024','a1s2d3f4!','qwer2024');

    // 한글깨짐 방지
    mysqli_query($db,"set names utf8");

    // board 테이블의 데이터들을 모두 읽어오기 - 쿼리문
    $sql="SELECT * FROM board";
    // 쿼리문 실행
    $result=mysqli_query($db, $sql); //SELECT 조건에 따른 검색결과표 객체 리턴해줌
    //$result는 검색결과표를 가진 객체 or false
    if($result){

        // 총 레코드(한줄:row)의 수
        $rowNum= mysqli_num_rows($result);
        
        // 반복하여 각 줄의 데이터들을 뽑아오기
        for($i=0; $i<$rowNum; $i++){
            //한줄 데이터 받기..배열로 받기[칸이 여러개라서..]
            $row= mysqli_fetch_array($result,MYSQLI_ASSOC); //MYSQL_ASSOC 옵션 - 연관배열로 리턴해줌

            //한줄 안에 있는 각 값들을 뽑아오기
            $no= $row['no'];
            $title=$row['title'];
            $message=$row['msg'];
            $image=$row['image'];
            $date=$row['date'];

            echo "<h3>$no : $title</h3>";
            echo "<p>$message</p>";
            echo "<p>$date</p>";

            if($image!=null) echo "<img src='$image' width='30%'>";

            echo "<hr>";
            
        }//for..

        }else{
        echo "DB검색에 실패했습니다.";
    }

    //DB와 연결 종료
    mysqli_close($db);



?>