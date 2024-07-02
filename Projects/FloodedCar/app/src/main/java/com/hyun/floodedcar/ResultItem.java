package com.hyun.floodedcar;

public class ResultItem {





    String numOfRows; // 한 페이지 결과수
    String pageNo; // 페이지 번호
    String totalCount; // 전체 결과 수
    String acdnOccrDtm; // 사고발생일시
    String acdnKindNm; // 사고 종류명
    String  nowVhclNo; // 현재차량 번호

    String resultMsg; // 결과 메시지
    String resultCode; // 결과 코드
    String dtaWrtDt; // 작성일자






    public ResultItem() {

    }


    public ResultItem(String numOfRows, String pageNo, String totalCount, String acdnOccrDtm, String acdnKindNm, String nowVhclNoo, String resultCode, String resultMsg, String dtaWrtDt){
        this.numOfRows= numOfRows;
        this.pageNo=pageNo;
        this.totalCount=totalCount;
        this.acdnOccrDtm=acdnOccrDtm;
        this.acdnKindNm=acdnKindNm;
        this.nowVhclNo=nowVhclNoo;
        this.resultCode= resultCode;
        this.resultMsg= resultMsg;
        this.dtaWrtDt= dtaWrtDt;

    }



}
