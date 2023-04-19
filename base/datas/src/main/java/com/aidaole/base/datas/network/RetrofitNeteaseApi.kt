package com.aidaole.base.datas.network

import com.aidaole.base.datas.entities.HotPlayListTags
import com.aidaole.base.datas.entities.PlayListSongs
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.network.retrofit.calladapter.Resp
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitNeteaseApi {

    @GET("/top/playlist/highquality")
    fun playlistHighQuality(
        @Query("limit") limit: Int = 14,
        @Query("order") order: String = "new"
    ): Resp<RespPlayList?>

    @GET("/playlist/hot")
    fun playlistHot(): Resp<HotPlayListTags?>

    @GET("/playlist/track/all")
    fun playlistTrackAll(
        @Query("id") id: Long,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): Resp<PlayListSongs?>

    @GET("/song/url/v1")
    fun songUrl(
        @Query("id") id: Int,
        @Query("level") level: String = "standard",
        @Query("timestamp") timestamp: Long = System.currentTimeMillis()
    ): Resp<String>

    @GET("/song/detail")
    fun songDetail(@Query("ids") ids: String): Resp<String>
}