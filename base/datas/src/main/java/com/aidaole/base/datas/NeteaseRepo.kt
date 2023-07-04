package com.aidaole.base.datas

import android.content.Context
import com.aidaole.base.datas.entities.*
import com.aidaole.base.datas.network.RetrofitNeteaseApi
import com.aidaole.base.utils.logi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NeteaseRepo @Inject constructor(
    private val retrofitNeteaseApi: RetrofitNeteaseApi
) {
    companion object {
        private const val TAG = "NeteaseRepo"
    }

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

    fun loadTopPlaylistSongs(): Flow<StateValue<out RespSongs?>> = flow {
        val playlistId = retrofitNeteaseApi.playlistHighQuality().run()?.body()?.playlists?.get(1)?.id ?: 0
        val resp = retrofitNeteaseApi.playlistTrackAll(playlistId, 0, 10).run()
        emit(if (resp.isSuccessful) StateValue.Succ(resp.body()) else StateValue.Fail(null))
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(StateValue.Fail(null)) }

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
    ): Flow<String?> = flow {
        val resp = retrofitNeteaseApi.songUrl(id, level, timestamp).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun loadSongDetail(
        ids: String
    ): Flow<RespSongs?> = flow {
        val resp = retrofitNeteaseApi.songDetail(ids).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun updateUserInfo(context: Context): Flow<String?> = flow {
        val resp = retrofitNeteaseApi.getUserInfo().run()
        if (resp.isSuccessful) {
            UserInfoManager.writeUserInfoToSp(context, resp.body())
        }
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun getQrImg(): Flow<QrCheckParams?> = flow {
        val qrKeyResp = retrofitNeteaseApi.getLoginQrKey().run()
        if (!qrKeyResp.isSuccessful) {
            "getQrImg-> 请求qrKey失败, code:${qrKeyResp.code()}".logi(TAG)
            emit(null)
            return@flow
        }
        val qrKey = qrKeyResp.body()?.data?.unikey
        if (qrKey.isNullOrBlank()) {
            "getQrImg-> qrkey为空".logi(TAG)
            emit(null)
            return@flow
        }
        "getQrImg-> 1. 请求qrkey成功: $qrKey".logi(TAG)

        val qrImgBase64Resp = retrofitNeteaseApi.getLoginQrImg(qrKey).run()
        if (!qrImgBase64Resp.isSuccessful) {
            "getQrImg-> 请求ImgBase64失败".logi(TAG)
            emit(null)
            return@flow
        }
        val qrImg = qrImgBase64Resp.body()?.data?.qrimg
        if (qrImg.isNullOrBlank()) {
            "getQrImg-> qrImg为空".logi(TAG)
            emit(null)
            return@flow
        }
        "getQrImg-> 2. 请求qrImg成功".logi(TAG)

        emit(QrCheckParams(qrImg, qrKey))
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun getQrScannedCode(
        qrKey: String
    ): Flow<RespCheckLoginQr?> = flow {
        val resp = retrofitNeteaseApi.getQrScannedCode(qrKey).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }
}