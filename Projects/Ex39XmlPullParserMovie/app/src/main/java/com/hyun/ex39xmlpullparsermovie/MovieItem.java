package com.hyun.ex39xmlpullparsermovie;

public class MovieItem {
    String rank;      //순위
    String movieNm;   //영화제목
    String openDt;    //개봉일
    String audiAcc;   //누적관객수

    public MovieItem() {
    }

    public MovieItem(String rank, String movieNm, String openDt, String audiAcc) {
        this.rank = rank;
        this.movieNm = movieNm;
        this.openDt = openDt;
        this.audiAcc = audiAcc;
    }
}
