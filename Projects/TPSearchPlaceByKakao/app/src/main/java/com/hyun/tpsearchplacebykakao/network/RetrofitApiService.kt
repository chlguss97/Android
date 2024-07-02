package com.hyun.tpsearchplacebykakao.network

import com.hyun.tpsearchplacebykakao.data.KakaoSearchPlaceResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitApiService {

    // 카카오 로컬검색 api.. 요청해주는 코드 만들어줘. 우선 응답 type: String
    @Headers("Authorization: KakaoAK 3585efcfbb24d679f168bc4e30cd49d5")
    @GET("/v2/local/search/keyword.json")
    fun  searchPlaceToString( @Query("query") query:String, @Query("x")longitude:String, @Query("y")latitude:String ) : Call<String>

    // 카카오 로컬검색 api.. 요청해주는 코드 만들어줘. 우선 응답 type: KakaoSearchPlaceResponse
    @Headers("Authorization: KakaoAK 3585efcfbb24d679f168bc4e30cd49d5")
    @GET("/v2/local/search/keyword.json?sort=distance")
    fun  searchPlace( @Query("query") query:String, @Query("x")longitude:String, @Query("y")latitude:String ) : Call<KakaoSearchPlaceResponce>


    // 네아로 회원정보 프로필 api.. 요청
    @GET("/v1/nid/me")
    fun getNidUserInfo( @Header("Authorization") authorizaion: String) : Call<String>


}