package com.aidaole.base.datas.network

import android.content.Context
import com.aidaole.base.datas.UserInfoManager
import com.aidaole.base.datas.entities.*
import com.aidaole.base.datas.network.NeteaseApi.Companion.BASE_URL
import com.aidaole.base.utils.logi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient


class NeteaseApiImpl(
    private val client: OkHttpClient,
    private val gson: Gson
) : NeteaseApi {
    companion object {
        private const val TAG = "NeteaseApiImpl"
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