package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetService {
    @GET("/")
    fun  //配置Get请求、URL路径
            getRepos(
        @Query("apikey") apikey: String,
        @Query("t") t: String
    ): Call<Detail>//指定返回Call<T>对象，这里的T指定网络解析数据后返回的类型
}