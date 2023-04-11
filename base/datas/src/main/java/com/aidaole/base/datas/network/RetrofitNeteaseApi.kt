package com.aidaole.base.datas.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitNeteaseApi {

    @GET("/song/url/v1")
    fun getMusic(
        @Query("id") id: Int,
        @Query("level") level: String = "standard",
        @Query("timestamp") timestamp: Long = System.currentTimeMillis()
    ): Call<String>
}