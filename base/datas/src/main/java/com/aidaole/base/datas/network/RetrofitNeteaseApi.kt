package com.aidaole.base.datas.network

import android.app.appsearch.ReportSystemUsageRequest
import com.aidaole.base.datas.entities.*
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
    ): Resp<RespSongs?>

    @GET("/song/url/v1")
    fun songUrl(
        @Query("id") id: Int,
        @Query("level") level: String = "standard",
        @Query("timestamp") timestamp: Long = System.currentTimeMillis()
    ): Resp<String>

    @GET("/song/detail")
    fun songDetail(@Query("ids") ids: String): Resp<RespSongs?>

    // login相关
    @GET("/login/qr/key")
    fun getLoginQrKey(): Resp<RespQrKey>

    @GET("/login/qr/create")
    fun getLoginQrImg(
        @Query("key") qrKey: String,
        @Query("qrimg") qrimg: Boolean = true
    ): Resp<RespQrImg>

    @GET("/login/qr/check")
    fun getQrScannedCode(
        @Query("key") qrKey: String
    ): Resp<RespCheckLoginQr?>
    // login相关

    @GET("/user/account")
    fun getUserInfo(): Resp<String?>

    @GET("/song/url/v1")
    fun songUrl(
        @Query("id") songIds: String,
        @Query("level") level: String = "standard"
    ): Resp<RespSongUrl?>

    @GET("/login/status")
    fun checkUserLoginStatus(): Resp<LoginStatus?>

    @GET("/logout")
    fun logout(): Resp<String?>
}