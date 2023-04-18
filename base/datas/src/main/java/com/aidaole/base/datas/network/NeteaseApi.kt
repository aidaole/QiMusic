package com.aidaole.base.datas.network

import android.content.Context
import com.aidaole.base.datas.entities.*
import kotlinx.coroutines.flow.Flow

interface NeteaseApi {
    companion object {
        var BASE_URL = "http://10.101.80.59:3000"
    }

    fun getQrImg(): QrCheckParams?

    fun getQrScannedCode(qrKey: String): RespCheckLoginQr?

    fun getUserInfo(context: Context): RespUserInfo?

    fun loadTopPlayList(): Flow<RespPlayList?>

    fun loadHotPlaylistTags(): Flow<HotPlayListTags?>

    fun getPlaylistSongs(playlistId: Long): PlayListSongs?

    fun loadTopPlaylistSongs(): Flow<PlayListSongs?>

    fun getMusic(id: Int): String?
}