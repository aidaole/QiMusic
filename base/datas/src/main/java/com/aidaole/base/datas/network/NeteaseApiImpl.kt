package com.aidaole.base.datas.network

import android.content.Context
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.datas.entities.*
import com.aidaole.base.utils.logi
import com.google.gson.Gson
import okhttp3.OkHttpClient

class NeteaseApiImpl(
    private val client: OkHttpClient,
    private val gson: Gson
) : NeteaseApi {
    companion object {
        private const val TAG = "NeteaseApiImpl"
        const val BASE_URL = "http://10.101.81.229:3000"
    }

    override fun getQrImg(): QrCheckParams? {
        val qrKey = getLoginQrKey() ?: return null
        "1. getQrImg-> qrKey: $qrKey".logi(TAG)
        val qrImgBase64 = getLoginQrImg(qrKey) ?: return null
        "2. getQrImg-> img base64: $qrImgBase64 ".logi(TAG)
        return QrCheckParams(qrImgBase64, qrKey)
    }

    override fun getQrScannedCode(qrKey: String): RespCheckLoginQr? {
        val request =
            createRequest("$BASE_URL/login/qr/check?key=$qrKey").build()
        client.newCall(request).execute().use {
            if (it.isSuccessful) {
                val result = gson.fromJson(it.body?.string(), RespCheckLoginQr::class.java)
                "checkQrScanedCode-> $result".logi(TAG)
                return result
            } else {
                "checkQrScanedCode-> failed: ${it.code}".logi(TAG)
            }
        }
        return null
    }

    override fun getUserInfo(context: Context): RespUserInfo? {
        val request = createRequest("$BASE_URL/user/account").build()
        return client.newCall(request).execute().use {
            if (it.isSuccessful) {
                val result = it.body?.string()
                "getUserInfo-> $result".logi(TAG)
                return@use result?.toJsonObject(gson, RespUserInfo::class.java).also {
                    UserInfoManager.writeUserInfoToSp(context, result)
                }
            } else {
                return@use null
            }
        }
    }

    override fun loadTopPlayList(id: Int): RespPlayList? {
        val request =
            createRequest("$BASE_URL/top/playlist/highquality?limit=10&order=new").build()
        client.newCall(request).execute().use {
            if (it.isSuccessful) {
                val respContent = it.body?.string()
                "loadTopPlayList-> $respContent".logi(TAG)
                return gson.fromJson(respContent, RespPlayList::class.java)
            }
            return null
        }
    }

    private fun getLoginQrKey(): String? {
        val request = createRequest("$BASE_URL/login/qr/key").build()
        client.newCall(request).execute().use {
            if (it.isSuccessful) {
                val respContent = gson.fromJson(it.body?.string(), RespQrKey::class.java)
                if (respContent.code == 200) {
                    return respContent.data.unikey
                }
            }
        }
        return null
    }

    private fun getLoginQrImg(qrKey: String): String? {
        val request =
            createRequest("$BASE_URL/login/qr/create?key=$qrKey&qrimg=true").build()
        client.newCall(request).execute().use {
            if (it.isSuccessful) {
                val respContent = gson.fromJson(it.body?.string(), RespQrImg::class.java)
                if (respContent.code == 200) {
                    return respContent.data.qrimg
                }
            }
        }
        return null
    }
}