package com.aidaole.base.datas.network

import com.aidaole.base.datas.network.retrofit.calladapter.Resp
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitNeteaseApi {

    @GET("/top/playlist/highquality")
    fun topPlaylistHighquality(
        @Query("limit") limit: Int = 14,
        @Query("order") order: String = "new"
    ): Flow<String>

    @GET("/song/url/v1")
    fun getMusicUrl(
        @Query("id") id: Int,
        @Query("level") level: String = "standard",
        @Query("timestamp") timestamp: Long = System.currentTimeMillis()
    ): Call<String>

    @GET("/song/detail")
    suspend fun getMusicDetail(@Query("ids") ids: String): String
}