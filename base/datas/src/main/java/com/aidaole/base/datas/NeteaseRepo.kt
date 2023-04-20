package com.aidaole.base.datas

import com.aidaole.base.datas.entities.HotPlayListTags
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.entities.RespSongs
import com.aidaole.base.datas.network.RetrofitNeteaseApi
import com.aidaole.base.datas.network.retrofit.calladapter.Resp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NeteaseRepo @Inject constructor(
    private val retrofitNeteaseApi: RetrofitNeteaseApi
) {
    fun loadPlaylistHot(): Flow<HotPlayListTags?> = flow {
        val resp = retrofitNeteaseApi.playlistHot().run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun loadPlaylistHighQuality(
        limit: Int = 14,
        order: String = "new"
    ): Flow<RespPlayList?> = flow {
        val resp = retrofitNeteaseApi.playlistHighQuality(limit, order).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun loadTopPlaylistSongs(): Flow<RespSongs?> = flow {
        val playlistId = retrofitNeteaseApi.playlistHighQuality().run()?.body()?.playlists?.get(1)?.id ?: 0
        val resp = retrofitNeteaseApi.playlistTrackAll(playlistId, 0, 10).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun loadPlaylistTrackAll(
        playlistId: Long,
        offset: Int = 0,
        limit: Int = 20
    ): Flow<MutableList<RespSongs.Song>?> = flow {
        val resp = retrofitNeteaseApi.playlistTrackAll(playlistId, offset, limit).run()
        emit(if (resp.isSuccessful) resp.body()?.songs else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun loadSongUrl(
        id: Int,
        level: String = "standard",
        timestamp: Long = System.currentTimeMillis()
    ): Flow<Resp<String>> = flow {
        emit(retrofitNeteaseApi.songUrl(id, level, timestamp))
    }

    fun loadSongDetail(ids: String): Flow<RespSongs?> = flow {
        val resp = retrofitNeteaseApi.songDetail(ids).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }
}