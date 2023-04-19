package com.aidaole.base.datas

import com.aidaole.base.datas.entities.HotPlayListTags
import com.aidaole.base.datas.entities.PlayListSongs
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.network.RetrofitNeteaseApi
import com.aidaole.base.datas.network.retrofit.calladapter.Resp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NeteaseRepo @Inject constructor(
    private val retrofitNeteaseApi: RetrofitNeteaseApi
) {
    fun loadPlaylistHot(): Flow<HotPlayListTags?> = flow {
        val resp = retrofitNeteaseApi.playlistHot().run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }.flowOn(Dispatchers.IO)

    fun loadPlaylistHighQuality(
        limit: Int = 14,
        order: String = "new"
    ): Flow<RespPlayList?> = flow {
        val resp = retrofitNeteaseApi.playlistHighQuality(limit, order).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }.flowOn(Dispatchers.IO)

    fun loadTopPlaylistSongs(): Flow<PlayListSongs?> = flow {
        val playlistId = retrofitNeteaseApi.playlistHighQuality().run()?.body()?.playlists?.get(0)?.id ?: 0
        val resp = retrofitNeteaseApi.playlistTrackAll(playlistId).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }.flowOn(Dispatchers.IO)

    fun loadPlaylistTrackAll(
        playlistId: Long,
        offset: Int = 0,
        limit: Int = 20
    ): Flow<PlayListSongs?> = flow {
        val resp = retrofitNeteaseApi.playlistTrackAll(playlistId, offset, limit).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }

    fun loadSongUrl(
        id: Int,
        level: String = "standard",
        timestamp: Long = System.currentTimeMillis()
    ): Flow<Resp<String>> = flow {
        emit(retrofitNeteaseApi.songUrl(id, level, timestamp))
    }

    fun loadSongDetail(ids: String): Flow<String> = flow {
        val resp = retrofitNeteaseApi.songDetail(ids).run()
        (if (resp.isSuccessful) resp.body() else "")?.let { emit(it) }
    }
}