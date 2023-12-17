package com.aidaole.base.datas

import android.content.Context
import com.aidaole.base.datas.entities.HotPlayListTags
import com.aidaole.base.datas.entities.LoginStatus.Data.Account
import com.aidaole.base.datas.entities.QrCheckParams
import com.aidaole.base.datas.entities.RespCheckLoginQr
import com.aidaole.base.datas.entities.RespGetCaptcha
import com.aidaole.base.datas.entities.RespPhonePasswordLogin
import com.aidaole.base.datas.entities.RespPlayList
import com.aidaole.base.datas.entities.RespSongs
import com.aidaole.base.datas.network.RetrofitNeteaseApi
import com.aidaole.base.ext.logi
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

    fun loadPlaylistHot(): Flow<StateValue<out HotPlayListTags?>> = flow {
        val resp = retrofitNeteaseApi.playlistHot().run()
        emit(if (resp.isSuccessful) StateValue.Succ(resp.body()) else StateValue.Fail(null))
    }.flowOn(Dispatchers.IO).catch { emit(StateValue.Exception(t = it)) }

    fun loadPlaylistHighQuality(
        limit: Int = 14, order: String = "new"
    ): Flow<StateValue<out RespPlayList?>> = flow {
        val resp = retrofitNeteaseApi.playlistHighQuality(limit, order).run()
        emit(if (resp.isSuccessful) StateValue.Succ(resp.body()) else StateValue.Fail())
    }.flowOn(Dispatchers.IO).catch { emit(StateValue.Exception(t = it)) }

    fun loadTopPlaylistSongs(): Flow<StateValue<out RespSongs?>> = flow {
        val playlistId = retrofitNeteaseApi.playlistHighQuality().run()?.body()?.playlists?.get(1)?.id ?: 0
        val resp = retrofitNeteaseApi.playlistTrackAll(playlistId, 0, 10).run()
        emit(if (resp.isSuccessful) StateValue.Succ(resp.body()) else StateValue.Fail(null))
    }.flowOn(Dispatchers.IO).catch { emit(StateValue.Exception(t = it)) }

    fun loadPlaylistTrackAll(
        playlistId: Long, offset: Int = 0, limit: Int = 20
    ): Flow<MutableList<RespSongs.Song>?> = flow {
        val resp = retrofitNeteaseApi.playlistTrackAll(playlistId, offset, limit).run()
        emit(if (resp.isSuccessful) resp.body()?.songs else null)
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun loadSongUrl(
        id: Int, level: String = "standard", timestamp: Long = System.currentTimeMillis()
    ): Flow<String?> = flow {
        val resp = retrofitNeteaseApi.songUrl(id, level, timestamp).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun loadSongDetail(
        ids: String
    ): Flow<RespSongs?> = flow {
        val resp = retrofitNeteaseApi.songDetail(ids).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun updateUserInfo(context: Context) {
        val resp = retrofitNeteaseApi.getUserInfo().run()
        if (resp.isSuccessful) {
            UserInfoManager.writeUserInfoToSp(context, resp.body())
        }
    }

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
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun getQrScannedCode(
        qrKey: String
    ): Flow<RespCheckLoginQr?> = flow {
        val resp = retrofitNeteaseApi.getQrScannedCode(qrKey).run()
        emit(if (resp.isSuccessful) resp.body() else null)
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun getSongUrl(
        ids: String
    ): Flow<String?> = flow<String?> {
        val resp = retrofitNeteaseApi.songUrl(ids).run()
        val body = resp.body()
        "getSongUrl-> $body".logi(TAG)
        if (resp.isSuccessful) {
            emit(if (resp.isSuccessful) body?.data?.get(0)?.url else null)
        }
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun checkUserLoginStatus(): Flow<Account?> = flow {
        val resp = retrofitNeteaseApi.checkUserLoginStatus().run()
        val body = resp.body()
        "checkUserLoginStatus-> $body".logi(TAG)
        if (resp.isSuccessful) {
            emit(if (resp.isSuccessful) body?.data?.account else null)
        }
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun logout(): Flow<String?> = flow {
        val resp = retrofitNeteaseApi.logout().run()
        val body = resp.body()
        "logout-> $body".logi(TAG)
        if (resp.isSuccessful) {
            emit(if (resp.isSuccessful) body else null)
        }
    }
        .flowOn(Dispatchers.IO)
        .catch { emit(null) }

    fun doPhonePasswordLogin(phone: String, md5Password: String): Flow<RespPhonePasswordLogin?> = flow {
        val resp = retrofitNeteaseApi.phonePasswordLogin(phone, md5Password).run()
        if (resp.isSuccessful) {
            "${resp.body()}".logi(TAG)
            emit(resp.body())
        } else {
            emit(null)
        }
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun getCaptchaCode(phone: String): Flow<RespGetCaptcha?> = flow {
        val resp = retrofitNeteaseApi.getChapchaCode(phone).run()
        if (resp.isSuccessful) {
            "${resp.body()}".logi(TAG)
            emit(RespGetCaptcha())
        } else {
            emit(null)
        }
    }.flowOn(Dispatchers.IO).catch { emit(null) }

    fun doCaptchaLogin(phone: String, captchaCode: String): Flow<RespPhonePasswordLogin?> = flow {
        val resp = retrofitNeteaseApi.doCaptchaLogin(phone, captchaCode).run()
        if (resp.isSuccessful) {
            emit(resp.body())
        } else {
            emit(null)
        }
    }.flowOn(Dispatchers.IO).catch { emit(null) }

}
